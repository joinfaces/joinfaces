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

package org.joinfaces.configuration;

import org.apache.myfaces.webapp.MyFacesServlet;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.util.Constants;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.core.type.StandardAnnotationMetadata;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
public class ClassVersionConditionTest {

	private ClassVersionCondition classVersionCondition;

	@Before
	public void setUp() {
		this.classVersionCondition = new ClassVersionCondition();
	}

	@Test(expected = NullPointerException.class)
	public void testMatchOutcomeNull() {
		this.classVersionCondition.getMatchOutcome(null, null);
	}

	@Test
	public void testMatchOutcomeSuccess() {
		StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ConditionalOnPrimefacesGood.class);

		ConditionOutcome matchOutcome = this.classVersionCondition.getMatchOutcome(null, annotationMetadata);

		assertThat(matchOutcome).isNotNull();
		assertThat(matchOutcome.isMatch()).isTrue();
	}

	@Test
	public void testMatchOutcomeFail() {
		StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ConditionalOnPrimefacesBad.class);

		ConditionOutcome matchOutcome = this.classVersionCondition.getMatchOutcome(null, annotationMetadata);

		assertThat(matchOutcome).isNotNull();
		assertThat(matchOutcome.isMatch()).isFalse();
	}

	@Test
	public void testMatchOutcomeMyFacesGood() {
		StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ConditionalOnMyFacesGood.class);

		ConditionOutcome matchOutcome = this.classVersionCondition.getMatchOutcome(null, annotationMetadata);

		assertThat(matchOutcome).isNotNull();
		assertThat(matchOutcome.isMatch()).isTrue();
	}

	@Test
	public void testMatchOutcomeMyFacesBad() {
		StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(ConditionalOnMyFacesBad.class);

		ConditionOutcome matchOutcome = this.classVersionCondition.getMatchOutcome(null, annotationMetadata);

		assertThat(matchOutcome).isNotNull();
		assertThat(matchOutcome.isMatch()).isFalse();
	}

	@Test
	public void testNoVersion() {
		StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(NoVersion.class);

		ConditionOutcome matchOutcome = this.classVersionCondition.getMatchOutcome(null, annotationMetadata);

		assertThat(matchOutcome).isNotNull();
		assertThat(matchOutcome.isMatch()).isFalse();
	}

	@ConditionalOnClassVersion(value = Constants.ContextParams.class, versionRegex = "6.*")
	public static class ConditionalOnPrimefacesGood {
	}

	@ConditionalOnClassVersion(value = Constants.ContextParams.class, versionRegex = "5.*")
	public static class ConditionalOnPrimefacesBad {
	}

	@ConditionalOnClassVersion(value = MyFacesServlet.class, versionRegex = "2.*")
	public static class ConditionalOnMyFacesGood {
	}

	@ConditionalOnClassVersion(value = MyFacesServlet.class, versionRegex = "1.*")
	public static class ConditionalOnMyFacesBad {
	}

	@ConditionalOnClassVersion(value = ClassVersionConditionTest.class, versionRegex = ".*")
	public static class NoVersion {
	}

}
