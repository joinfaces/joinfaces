/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.servlet.annotation.HandlesTypes;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * Factory of classes with jsf types handled by servlet context initializer.
 * @author Marcelo Fernandes
 */
public class JsfClassFactory {

	private JsfClassFactoryConfiguration jsfAnnotatedClassFactoryConfiguration;

	private Collection<URL> urls;

	private Map<Class<? extends Annotation>, Set<Class<?>>> annotatedClassMap;

	private Map<Class<?>, Set<Class<?>>> otherClassMap;

	public JsfClassFactory(JsfClassFactoryConfiguration jsfAnnotatedClassFactoryConfiguration) {
		this.jsfAnnotatedClassFactoryConfiguration = jsfAnnotatedClassFactoryConfiguration;
	}

	/**
	 * Ignore ViewScoped, SessionScoped, RequestScoped and NoneScoped annotations
	 * because spring will take care of them.
	 * @return set of annotations to exclude from handlesType
	 */
	private Set<Class<? extends Annotation>> annotationsToExclude() {
		Set<Class<? extends Annotation>> result = new HashSet<Class<? extends Annotation>>();
		if (this.jsfAnnotatedClassFactoryConfiguration.isExcludeScopedAnnotations()) {
			result.add(ViewScoped.class);
			result.add(SessionScoped.class);
			result.add(RequestScoped.class);
			result.add(NoneScoped.class);
		}
		return result;
	}

	/**
	 * Compute types handled ( set of annotation classes and set of other classes )
	 * handled by servlet container initializer.
	 * @return types to be handled by jsf implementation
	 */
	private TypesHandled handlesTypes() {
		TypesHandled result = new TypesHandled();

		HandlesTypes ht = this.jsfAnnotatedClassFactoryConfiguration.getHandlesTypes();
		if (ht != null) {
			Set<Class<? extends Annotation>> annotationsToExclude = annotationsToExclude();

			for (Class<?> type : ht.value()) {
				if (type.isAnnotation()) {
					Class<? extends Annotation> annotation = (Class<? extends Annotation>) type;
					if (!annotationsToExclude.contains(annotation)) {
						result.getAnnotationTypes().add(annotation);
					}
				}
				else {
					result.getOtherTypes().add(type);
				}
			}
		}

		return result;
	}

	private void add(Collection<URL> urls, Collection<String> strings, Collection<URL> newURLs) {
		for (URL url: newURLs) {
			add(urls, strings, url);
		}
	}

	private void add(Collection<URL> urls, Collection<String> strings, URL url) {
		String string = url.toString();
		if (!strings.contains(string)) {
			urls.add(url);
			strings.add(string);
		}
	}

	private boolean isClassesFolder(String file) {
		return file.endsWith("/classes/")
			|| file.endsWith("/classes!/")
			|| new File(file).isDirectory();
	}

	/**
	 * Compute urls to scan for annotations.
	 * @return collection of urls
	 */
	public Collection<URL> getURLs() {
		if (this.urls == null) {
			// stores collections of urls to be scanned
			Collection<URL> result = new ArrayList<URL>();
			Collection<String> strings = new HashSet<String>();

			// get only urls of libraries that contains jsf types
			add(result, strings, ClasspathHelper.forResource("META-INF/faces-config.xml", this.getClass().getClassLoader()));

			// add jsf library with anotherFacesConfig
			String anotherFacesConfig = this.jsfAnnotatedClassFactoryConfiguration.getAnotherConfig();
			if (anotherFacesConfig != null) {
				add(result, strings, ClasspathHelper.forResource(anotherFacesConfig, this.getClass().getClassLoader()));
			}

			// add project classes folder
			for (URL url : ClasspathHelper.forManifest()) {
				String file = url.getFile();
				// check if adding classes folder
				if (isClassesFolder(file)) {
					add(result, strings, url);
				}
			}
			this.urls = result;
		}
		return this.urls;
	}

	/**
	 * Compute annotated types to be handled by servlet container initializer.
	 * Search libraries with anotherFacesConfig, project classes and resources
	 * folder too.
	 * @return classes annotated by types handled by servlet container initializer.
	 */
	public Map<Class<? extends Annotation>, Set<Class<?>>> getAnnotatedClassMap() {
		if (this.annotatedClassMap == null) {
			computeClasses();
		}

		return this.annotatedClassMap;
	}

	/**
	 * Compute other types to be handled by servlet container initializer.
	 * Search libraries with anotherFacesConfig, project classes and resources
	 * folder too.
	 * @return classes annotated by types handled by servlet container initializer.
	 */
	public Map<Class<?>, Set<Class<?>>> getOtherClassMap() {
		if (this.otherClassMap == null) {
			computeClasses();
		}

		return this.otherClassMap;
	}

	private void computeClasses() {
		this.annotatedClassMap = new HashMap<Class<? extends Annotation>, Set<Class<?>>>();
		this.otherClassMap = new HashMap<Class<?>, Set<Class<?>>>();

		TypesHandled handlesTypes = handlesTypes();
		// check if any type is handled
		if (!handlesTypes.isEmpty()) {
			// create reflections
			Reflections reflections = new Reflections(new ConfigurationBuilder().setExpandSuperTypes(false).setUrls(getURLs()));

			// add types annotated for each type to be handled
			for (Class<? extends Annotation> annotationType : handlesTypes.getAnnotationTypes()) {
				this.annotatedClassMap.put(annotationType, reflections.getTypesAnnotatedWith(annotationType));
			}
			// add subtype of other types to be handled
			for (Class<?> otherType : handlesTypes.getOtherTypes()) {
				this.otherClassMap.put(otherType, (Set<Class<?>>) reflections.getSubTypesOf(otherType));
			}
		}
	}

	/**
	 * Compute all classes to be handled by servlet container initializer.
	 * @return set of classes.
	 */
	public Set<Class<?>> getAllClasses() {
		Set<Class<?>> result = new HashSet<Class<?>>();
		result.addAll(getAnnotatedClasses());
		result.addAll(getOtherClasses());

		return result;
	}

	/**
	 * Compute all annotated classes to be handled by servlet container initializer.
	 * @return set of annotated classes.
	 */
	public Set<Class<?>> getAnnotatedClasses() {
		return collectValues(getAnnotatedClassMap().values());
	}

	/**
	 * Compute all other classes to be handled by servlet container initializer.
	 * @return set of other classes.
	 */
	public Set<Class<?>> getOtherClasses() {
		return collectValues(getOtherClassMap().values());
	}

	private Set<Class<?>> collectValues(Collection<Set<Class<?>>> sets) {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (Set<Class<?>> values : sets) {
			classes.addAll(values);
		}
		return classes;
	}
}
