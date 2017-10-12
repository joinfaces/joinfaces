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

package org.joinfaces.mojarra;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;

import com.sun.faces.facelets.compiler.UIText;
import org.joinfaces.JsfClassFactory;
import org.junit.Test;

import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
public class JsfClassFactoryIT {

	@Test
	public void testJavaxFacesHtmlPanelGroupWithMojarra() {
		MojarraJsfClassFactoryConfiguration configuration = new MojarraJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMojarraUITextWithMojarra() {
		MojarraJsfClassFactoryConfiguration configuration = new MojarraJsfClassFactoryConfiguration();

		Set<Class<?>> classes = new JsfClassFactory(configuration).getOtherClasses();
		assertThat(classes).contains(UIText.class);
	}

}
