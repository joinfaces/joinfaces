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

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

/**
 * Primefaces {@link LazyDataModel} implementation which wraps a {@link JpaRepository Spring Data JPA Repository}.
 *
 * @param <T>  the domain type the repository manages
 * @param <ID> the type of the id of the entity the repository manages
 * @param <R>  the type of the repository interface
 * @author Lars Grefer
 */
public class SpringDataJpaLazyDataModel<T, ID, R extends JpaRepository<T, ID> & JpaSpecificationExecutor<T>> extends SpringDataLazyDataModel<T, ID, R> {

	@Getter
	@Setter
	private EntityManager entityManager;

	public SpringDataJpaLazyDataModel(R repository) {
		super(repository);
	}

	@Override
	public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

		Pageable pageable = getPageable(first, pageSize, sortBy);

		Specification<T> specification = getSpecification(filterBy);

		Page<T> page = repository.findAll(specification, pageable);

		registerResult(page);

		return page.getContent();
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		Specification<T> specification = getSpecification(filterBy);
		return (int) repository.count(specification);
	}

	@Nullable
	protected Specification<T> getSpecification(Map<String, FilterMeta> filterBy) {
		if (CollectionUtils.isEmpty(filterBy)) {
			return null;
		}

		return filterBy.values().stream()
			.map(this::getSpecification)
			.reduce((a, b) -> Specification.where(a).and(b))
			.orElse(null);
	}

	protected Specification<T> getSpecification(FilterMeta filterMeta) {
		return new FilterMetaSpecification<>(filterMeta);
	}

	@Override
	public ID getPrimaryKey(T entity) {
		if (entity instanceof Persistable<?>) {
			return ((Persistable<ID>) entity).getId();
		}

		if (entityManager != null) {
			return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
		}

		return super.getPrimaryKey(entity);
	}

	@RequiredArgsConstructor
	public static class FilterMetaSpecification<T> implements Specification<T> {

		private final FilterMeta filterMeta;

		@SuppressWarnings("rawtypes")
		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			Object filterValue = filterMeta.getFilterValue();

			Path<?> path = getPath(root, filterMeta.getField());

			switch (filterMeta.getMatchMode()) {
				case STARTS_WITH -> {
					return criteriaBuilder.like((Path<String>) path, filterValue + "%");
				}
				case NOT_STARTS_WITH -> {
					return criteriaBuilder.notLike((Path<String>) path, filterValue + "%");
				}
				case ENDS_WITH -> {
					return criteriaBuilder.like((Path<String>) path, "%" + filterValue);
				}
				case NOT_ENDS_WITH -> {
					return criteriaBuilder.notLike((Path<String>) path, "%" + filterValue);
				}
				case CONTAINS -> {
					return criteriaBuilder.like((Path<String>) path, "%" + filterValue + "%");
				}
				case NOT_CONTAINS -> {
					return criteriaBuilder.notLike((Path<String>) path, "%" + filterValue + "%");
				}
				case EXACT, EQUALS -> {
					return criteriaBuilder.equal(path, filterValue);
				}
				case NOT_EXACT, NOT_EQUALS -> {
					return criteriaBuilder.notEqual(path, filterValue);
				}
				case LESS_THAN -> {
					return criteriaBuilder.lessThan((Path<Comparable>) path, (Comparable) filterValue);
				}
				case LESS_THAN_EQUALS -> {
					return criteriaBuilder.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) filterValue);
				}
				case GREATER_THAN -> {
					return criteriaBuilder.greaterThan((Path<Comparable>) path, (Comparable) filterValue);
				}
				case GREATER_THAN_EQUALS -> {
					return criteriaBuilder.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) filterValue);
				}
				default ->
					throw new IllegalArgumentException("MatchMode " + filterMeta.getMatchMode() + " is not supported");
			}

		}

		protected <P> Path<P> getPath(Root<T> root, String key) {
			Path<?> path = root;
			for (String part : key.split("\\.")) {
				path = path.get(part);
			}
			return (Path<P>) path;
		}
	}
}
