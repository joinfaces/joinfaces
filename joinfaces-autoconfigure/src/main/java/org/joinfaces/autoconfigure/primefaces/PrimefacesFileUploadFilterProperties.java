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

package org.joinfaces.autoconfigure.primefaces;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Data
@ConfigurationProperties("joinfaces.primefaces.file-upload-filter")
public class PrimefacesFileUploadFilterProperties {

	/**
	 * Name for the Primefaces FileUpload Filter.
	 */
	private String name = "PrimeFaces FileUpload Filter";

	/**
	 * Order for the PrimeFaces FileUpload Filter.
	 */
	private int order = OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER;

	/**
	 * Maximum file size in bytes to keep uploaded files in memory.
	 * If a file exceeds this limit, it’ll be temporarily written to disk.
	 */
	@DataSizeUnit(DataUnit.BYTES)
	private DataSize thresholdSize;

	/**
	 * Disk repository path to keep temporary files that exceeds the threshold size.
	 * By default it is System.getProperty("java.io.tmpdir")
	 */
	private String uploadDirectory;
}
