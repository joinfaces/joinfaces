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

package org.joinfaces.weld;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.environment.deployment.WeldBeanDeploymentArchive;
import org.jboss.weld.environment.deployment.discovery.AbstractDiscoveryStrategy;
import org.jboss.weld.environment.deployment.discovery.BeanArchiveBuilder;
import org.jboss.weld.environment.deployment.discovery.FileSystemBeanArchiveHandler;
import org.jboss.weld.environment.deployment.discovery.jandex.JandexDiscoveryStrategy;
import org.jboss.weld.environment.util.Reflections;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

/**
 * ClassGraph based alternative to {@link JandexDiscoveryStrategy}.
 *
 * @author Lars Grefer
 * @see JandexDiscoveryStrategy
 */
@Slf4j
public class ClassGraphDiscoveryStrategy extends AbstractDiscoveryStrategy {

	private ScanResult scanResult;

	public ClassGraphDiscoveryStrategy() {
		this.registerHandler(new FileSystemBeanArchiveHandler());
	}

	@Override
	protected void beforeDiscovery(Collection<BeanArchiveBuilder> builders) {
		super.beforeDiscovery(builders);

		if (CollectionUtils.isEmpty(builders)) {
			return;
		}

		StopWatch stopWatch = new StopWatch("weld-bean-discovery");

		stopWatch.start("prepare");
		ClassGraph classGraph = new ClassGraph().enableAnnotationInfo();

		for (BeanArchiveBuilder builder : builders) {
			for (String clazz : builder.getClasses()) {
				classGraph.acceptClasses(clazz);
			}
		}
		stopWatch.stop();

		stopWatch.start("scan");
		this.scanResult = classGraph.scan();
		stopWatch.stop();

		log.info("Weld bean discovery took {}ms", stopWatch.getTotalTimeMillis());
		log.debug(stopWatch.prettyPrint());
	}

	@Override
	protected WeldBeanDeploymentArchive processAnnotatedDiscovery(BeanArchiveBuilder builder) {

		Iterator<String> classIterator = builder.getClassIterator();

		while (classIterator != null && classIterator.hasNext()) {
			String className = classIterator.next();
			ClassInfo classInfo = this.scanResult.getClassInfo(className);

			if (classInfo != null) {
				if (!this.hasBeanDefiningAnnotation(classInfo)) {
					classIterator.remove();
				}
			}
			else {
				log.debug("No classInfo for {}", className);
				Class<?> clazz = Reflections.loadClass(resourceLoader, className);
				if (clazz == null || !Reflections.hasBeanDefiningAnnotation(clazz, initialBeanDefiningAnnotations)) {
					classIterator.remove();
				}
			}
		}

		return builder.build();
	}

	protected boolean hasBeanDefiningAnnotation(ClassInfo classInfo) {

		for (Class<? extends Annotation> initialBeanDefiningAnnotation : initialBeanDefiningAnnotations) {
			if (classInfo.hasAnnotation(initialBeanDefiningAnnotation)) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected void afterDiscovery(Set<WeldBeanDeploymentArchive> archives) {
		super.afterDiscovery(archives);

		if (this.scanResult != null) {
			this.scanResult.close();
		}
	}

}
