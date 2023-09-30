/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.primefaces;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.util.CollectionUtils;

/**
 * Primefaces {@link LazyDataModel} implementation which wraps a {@link Repository Spring Data Repository}.
 *
 * @param <T>  the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 * @param <R>  the type of the repository interface
 * @author Lars Grefer
 */
public class SpringDataLazyDataModel<T, ID, R extends CrudRepository<T, ID> & PagingAndSortingRepository<T, ID>> extends LazyDataModel<T> {

	@Getter
	@Setter
	EntityInformation<T, ID> entityInformation;

	@Getter
	@Setter
	Converter<T> entityConverter;

	@Getter
	@Setter
	Class<ID> idClass;

	@Setter
	Function<String, ID> primaryKeyDeserializer;
	@Setter
	Function<ID, String> primaryKeySerializer = Objects::toString;

	final R repository;

	public SpringDataLazyDataModel(R repository) {
		this.repository = repository;
	}

	@Override
	public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

		Pageable pageable = getPageable(first, pageSize, sortBy);

		Page<T> page = repository.findAll(pageable);

		registerResult(page);

		return page.getContent();
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		return (int) repository.count();
	}

	protected void registerResult(Page<T> page) {
		setPageSize(page.getSize());
		setRowCount((int) page.getTotalElements());
	}

	protected Pageable getPageable(int first, int pageSize, Map<String, SortMeta> sortBy) {
		Sort sort = getSort(sortBy);
		return getPageable(first, pageSize, sort);
	}

	protected Pageable getPageable(int first, int pageSize, Sort sort) {
		int pageNum = first / pageSize;
		return PageRequest.of(pageNum, pageSize, sort);
	}

	protected Sort getSort(Map<String, SortMeta> sortBy) {
		if (CollectionUtils.isEmpty(sortBy)) {
			return Sort.unsorted();
		}

		List<Sort.Order> orders = sortBy.values().stream()
				.sorted(Comparator.comparing(SortMeta::getOrder))
				.map(sortMeta -> {
					Sort.Order order = Sort.Order.by(sortMeta.getField());

					Sort.Direction direction = getDirection(sortMeta);
					if (direction != null) {
						order = order.with(direction);
					}

					if (!sortMeta.isCaseSensitiveSort()) {
						order = order.ignoreCase();
					}

					return order;
				})
				.collect(Collectors.toList());

		return Sort.by(orders);
	}

	static Sort.Direction getDirection(SortMeta sortMeta) {
		return getDirection(sortMeta.getOrder());
	}

	private static Sort.Direction getDirection(SortOrder order) {
		return switch (order) {
			case ASCENDING -> Sort.Direction.ASC;
			case DESCENDING -> Sort.Direction.DESC;
			default -> null;
		};
	}

	@Override
	public String getRowKey(T object) {
		if (entityConverter != null) {
			return entityConverter.getAsString(FacesContext.getCurrentInstance(), null, object);
		}
		ID primaryKey = getPrimaryKey(object);
		idClass = (Class<ID>) primaryKey.getClass();
		return primaryKeySerializer.apply(primaryKey);
	}

	@Override
	public T getRowData(String rowKey) {
		if (entityConverter != null) {
			return entityConverter.getAsObject(FacesContext.getCurrentInstance(), null, rowKey);
		}
		ID id;

		if (String.class.isAssignableFrom(idClass)) {
			id = (ID) rowKey;
		}
		else if (Long.class.isAssignableFrom(idClass)) {
			id = (ID) Long.valueOf(rowKey);
		}
		else if (Integer.class.isAssignableFrom(idClass)) {
			id = (ID) Integer.valueOf(rowKey);
		}
		else if (UUID.class.isAssignableFrom(idClass)) {
			id = (ID) UUID.fromString(rowKey);
		}
		else {
			id = primaryKeyDeserializer.apply(rowKey);
		}
		return repository.findById(id).orElse(null);
	}

	protected ID getPrimaryKey(T entity) {
		if (entity instanceof Persistable<?>) {
			return ((Persistable<ID>) entity).getId();
		}

		if (entityInformation != null) {
			return entityInformation.getId(entity);
		}

		throw new IllegalStateException("Can't extract PK of " + entity);
	}

}
