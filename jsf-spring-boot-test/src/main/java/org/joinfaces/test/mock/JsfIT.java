/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.test.mock;

import lombok.Getter;
import org.junit.After;
import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Abstract integration test for jsf Activate JsfMock for each test execution.
 *
 * @author Marcelo Romulo Fernandes
 */
public class JsfIT {

	@Getter
	private JsfMock jsfMock;

	@Autowired
	private ApplicationContext applicationContext;

	@Before
	public void init() {
		this.jsfMock = new JsfMock();
		this.jsfMock.init(this.applicationContext);
	}

	@After
	public void release() {
		this.jsfMock.release();
	}

}
