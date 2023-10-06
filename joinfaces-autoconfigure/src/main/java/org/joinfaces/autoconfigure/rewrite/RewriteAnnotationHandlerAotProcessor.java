/*
 * Copyright 2016-2023 the original author or authors.
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

package org.joinfaces.autoconfigure.rewrite;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import org.joinfaces.ClasspathScanUtil;
import org.joinfaces.rewrite.SpringBootAnnotationConfigProvider;
import org.ocpsoft.rewrite.annotation.spi.AnnotationHandler;

import org.springframework.aot.generate.GenerationContext;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.aot.BeanFactoryInitializationCode;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;

/**
 * AOT processor for {@link AnnotationHandler Rewrite AnnotationHandlers}.
 *
 * @author Lars Grefer
 */
public class RewriteAnnotationHandlerAotProcessor implements BeanFactoryInitializationAotProcessor {
	@Override
	public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
		if (ClassUtils.isPresent("org.ocpsoft.rewrite.annotation.spi.AnnotationHandler", RewriteAnnotationHandlerAotProcessor.class.getClassLoader())) {
			return new RewriteAnnotationProcessorContribution();
		}

		return null;
	}

	static class RewriteAnnotationProcessorContribution implements BeanFactoryInitializationAotContribution {

		@Override
		public void applyTo(GenerationContext generationContext, BeanFactoryInitializationCode beanFactoryInitializationCode) {

			SpringBootAnnotationConfigProvider rewriteAnnotationConfigProvider = new SpringBootAnnotationConfigProvider();

			List<AnnotationHandler<Annotation>> annotationHandlers = rewriteAnnotationConfigProvider.getAnnotationHandlers();

			for (AnnotationHandler<Annotation> annotationHandler : annotationHandlers) {
				generationContext.getRuntimeHints().reflection().registerType(annotationHandler.getClass(), MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);
			}

			Set<Class<Annotation>> annotationClasses = rewriteAnnotationConfigProvider.getAnnotationClasses(annotationHandlers);

			Set<Class<?>> classes = rewriteAnnotationConfigProvider.scanClasses(annotationClasses);

			ClasspathScanUtil.writeClassSet(generationContext, SpringBootAnnotationConfigProvider.PREPARED_SCAN_RESULT_PATH, classes);
		}

	}
}
