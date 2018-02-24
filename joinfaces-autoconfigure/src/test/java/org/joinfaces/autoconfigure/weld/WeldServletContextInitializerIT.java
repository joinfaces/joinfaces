/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.weld;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class WeldServletContextInitializerIT {

	@Test
	public void testOnStartup() throws ServletException {
		WeldServletContextInitializer weldServletContextInitializer = new WeldServletContextInitializer();
		weldServletContextInitializer.onStartup(new MockServletContext());
		Object field = ReflectionTestUtils.getField(weldServletContextInitializer, "enhancedListener");
		assertThat(field).isNotNull();
		weldServletContextInitializer.onStartup(new MockServletContext());
		Object field2 = ReflectionTestUtils.getField(weldServletContextInitializer, "enhancedListener");
		assertThat(field).isEqualTo(field2);
	}

}
