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

package org.joinfaces.autoconfigure.myfaces;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.convert.FacesConverter;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.myfaces.spi.AnnotationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.verify;

public class JoinFacesAnnotationProviderTest {

	@BeforeEach
	public void init() {
		JoinFacesAnnotationProvider.setAnnotatedClasses(null);
	}

	@Test
	public void getAnnotatedClasses_set() {
		Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClasses = new HashMap<>();
		annotatedClasses.put(FacesConverter.class, new HashSet<>());
		JoinFacesAnnotationProvider.setAnnotatedClasses(annotatedClasses);

		assertThat(new JoinFacesAnnotationProvider(null).getAnnotatedClasses(null).get(FacesConverter.class))
				.isNotNull();
	}

	@Test
	@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
	public void getAnnotatedClasses_notSet() {
		AnnotationProvider annotationProvider = mock(AnnotationProvider.class);
		JoinFacesAnnotationProvider joinFacesAnnotationProvider = new JoinFacesAnnotationProvider(annotationProvider);

		joinFacesAnnotationProvider.getAnnotatedClasses(mock(ExternalContext.class));

		verify(annotationProvider).getAnnotatedClasses(any());
	}

}
