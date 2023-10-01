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

package org.joinfaces.autoconfigure.faces;

import jakarta.faces.FacesWrapper;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.application.ResourceHandler;
import jakarta.faces.application.StateManager;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.search.SearchExpressionHandler;
import jakarta.faces.event.FacesListener;
import jakarta.faces.event.PhaseListener;
import jakarta.faces.event.SystemEvent;
import jakarta.faces.flow.FlowHandlerFactory;
import jakarta.faces.render.ClientBehaviorRenderer;
import jakarta.faces.render.RenderKit;
import jakarta.faces.render.Renderer;
import jakarta.faces.view.facelets.ConverterHandler;
import jakarta.faces.view.facelets.FaceletHandler;
import jakarta.faces.view.facelets.TagHandler;

import io.github.classgraph.ScanResult;
import org.joinfaces.aot.ClassGraphRuntimeHintsRegistrar;

import org.springframework.aot.hint.RuntimeHints;

public class FacesRuntimeHintsRegistrar extends ClassGraphRuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader, ScanResult scanResult) {
		hints.resources().registerPattern("*/faces-config.xml");
		hints.resources().registerPattern("*.faces-config.xml");

		hints.resources().registerPattern("*.taglib.xml");

		registerStandardClasses(scanResult.getClassesImplementing(FacesWrapper.class), hints);

		registerStandardClasses(scanResult.getSubclasses(FlowHandlerFactory.class), hints);

		registerStandardClasses(scanResult.getSubclasses(NavigationHandler.class), hints);

		registerStandardClasses(scanResult.getSubclasses(StateManager.class), hints);

		registerStandardClasses(scanResult.getSubclasses(ConverterHandler.class), hints);

		registerStandardClasses(scanResult.getSubclasses(ResourceHandler.class), hints);

		registerStandardClasses(scanResult.getSubclasses(SearchExpressionHandler.class), hints);

		registerStandardClasses(scanResult.getSubclasses(SystemEvent.class), hints);

		registerStandardClasses(scanResult.getSubclasses(ViewHandler.class), hints);

		registerStandardClasses(scanResult.getSubclasses(Renderer.class), hints);

		registerStandardClasses(scanResult.getSubclasses(ClientBehaviorRenderer.class), hints);

		registerStandardClasses(scanResult.getSubclasses(RenderKit.class), hints);

		registerStandardClasses(scanResult.getSubclasses(TagHandler.class), hints);

		registerStandardClasses(scanResult.getClassesImplementing(PhaseListener.class), hints);

		registerStandardClasses(scanResult.getClassesImplementing(FacesListener.class), hints);

		registerStandardClasses(scanResult.getClassesImplementing(FaceletHandler.class), hints);
	}

}
