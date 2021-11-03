/*
 * Copyright 2016-2016 the original author or authors.
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

package org.joinfaces.autoconfigure.tomcat;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.TrackedWebResource;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;

@Getter
public class MockWebResourceRoot implements WebResourceRoot {

	@Setter
	private Context context;

	private int createWebResourceSetCalls = 0;

	private static final String NOT_SUPPORTED_YET = "Not supported yet.";

	private List<WebResourceSet> jarResources;

	@Override
	public WebResource getResource(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResource[] getResources(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResource getClassLoaderResource(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResource[] getClassLoaderResources(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public String[] list(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public Set<String> listWebAppPaths(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResource[] listResources(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean mkdir(String path) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean write(String path, InputStream is, boolean overwrite) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void createWebResourceSet(ResourceSetType type, String webAppMount, URL url, String internalPath) {
		this.createWebResourceSetCalls++;
	}

	@Override
	public void createWebResourceSet(ResourceSetType type, String webAppMount, String base, String archivePath, String internalPath) {
		this.createWebResourceSetCalls++;
	}

	@Override
	public void addPreResources(WebResourceSet webResourceSet) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResourceSet[] getPreResources() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void addJarResources(WebResourceSet webResourceSet) {
		if (this.jarResources == null) {
			this.jarResources = new ArrayList<>();
		}
		this.jarResources.add(webResourceSet);
	}

	@Override
	public WebResourceSet[] getJarResources() {
		return this.jarResources.toArray(new WebResourceSet[this.jarResources.size()]);
	}

	@Override
	public void addPostResources(WebResourceSet webResourceSet) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public WebResourceSet[] getPostResources() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setAllowLinking(boolean allowLinking) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean getAllowLinking() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setCachingAllowed(boolean cachingAllowed) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean isCachingAllowed() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setCacheTtl(long ttl) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public long getCacheTtl() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setCacheMaxSize(long cacheMaxSize) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public long getCacheMaxSize() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setCacheObjectMaxSize(int cacheObjectMaxSize) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public int getCacheObjectMaxSize() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void setTrackLockedFiles(boolean trackLockedFiles) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public boolean getTrackLockedFiles() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void backgroundProcess() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void registerTrackedResource(TrackedWebResource trackedResource) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void deregisterTrackedResource(TrackedWebResource trackedResource) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public List<URL> getBaseUrls() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void gc() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public void destroy() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

	@Override
	public LifecycleState getState() {
		return LifecycleState.NEW;
	}

	@Override
	public String getStateName() {
		throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
	}

}
