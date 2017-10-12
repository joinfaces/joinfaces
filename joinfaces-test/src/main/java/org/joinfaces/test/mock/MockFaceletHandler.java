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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletHandler;

import lombok.Getter;

/**
 * Facelet Handler Mock.
 *
 * @author Marcelo Romulo Fernandes
 */
public class MockFaceletHandler implements FaceletHandler {

	@Getter
	private boolean applied = false;

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		this.applied = true;
	}
}
