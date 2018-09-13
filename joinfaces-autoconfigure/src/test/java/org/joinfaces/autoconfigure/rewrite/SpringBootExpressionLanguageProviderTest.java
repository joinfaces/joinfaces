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

package org.joinfaces.autoconfigure.rewrite;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringBootExpressionLanguageProviderTest {

	private SpringBootExpressionLanguageProvider springBootExpressionLanguageProvider;

	@Before
	public void setUp() {
		this.springBootExpressionLanguageProvider = new SpringBootExpressionLanguageProvider();
	}

	@SuppressWarnings("ConstantConditions")
	@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
	@Test(expected = IllegalStateException.class)
	public void testNoApplicationContextEvaluateMethodExpression() {
		new ApplicationContextProvider().setApplicationContext(null);

		this.springBootExpressionLanguageProvider.evaluateMethodExpression("");
	}

	@SuppressWarnings("ConstantConditions")
	@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
	@Test(expected = UnsupportedOperationException.class)
	public void testNoApplicationContextEvaluateMethodExpression2() {
		new ApplicationContextProvider().setApplicationContext(null);

		this.springBootExpressionLanguageProvider.evaluateMethodExpression("", "");
	}

	@SuppressWarnings("ConstantConditions")
	@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
	@Test(expected = IllegalStateException.class)
	public void testNoApplicationContextRetrieveValue() {
		new ApplicationContextProvider().setApplicationContext(null);

		this.springBootExpressionLanguageProvider.retrieveValue("");
	}

	@SuppressWarnings("ConstantConditions")
	@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
	@Test(expected = IllegalStateException.class)
	public void testNoApplicationContextSubmitValue() {
		new ApplicationContextProvider().setApplicationContext(null);

		this.springBootExpressionLanguageProvider.submitValue("", "");
	}

	@SuppressWarnings("ConstantConditions")
	@SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION")
	@Test
	public void testNoApplicationPriority() {
		new ApplicationContextProvider().setApplicationContext(null);

		assertThat(this.springBootExpressionLanguageProvider.priority())
				.isEqualTo(19);
	}
}
