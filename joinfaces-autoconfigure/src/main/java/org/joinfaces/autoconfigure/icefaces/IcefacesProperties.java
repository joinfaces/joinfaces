/*
 * Copyright 2016-2018 the original author or authors.
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

package org.joinfaces.autoconfigure.icefaces;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.convert.DurationUnit;

/**
 * Configuration properties for ICEfaces.
 *
 * @author Lars Grefer
 * @see <a href="http://www.icesoft.org/wiki/display/ICE/Configuration+Parameters">http://www.icesoft.org/wiki/display/ICE/Configuration+Parameters</a>
 */
@Data
@ConfigurationProperties("joinfaces.icefaces")
public class IcefacesProperties {

	/**
	 * The default value of true for org.icefaces.fetchPendingNotificationsOnLoad indicates that whenever the blocking connection switches over to another window each window will issue a request for the updates that were potentially issued during the switch.
	 * In use cases where is reasonable to have the request for updates disabled setting the value to false can decrease the network traffic in applications that use many windows.
	 *
	 * @since ICEfaces 4.1
	 */
	@ServletContextInitParameter("org.icefaces.fetchPendingNotificationsOnLoad")
	private Boolean fetchPendingNotificationsOnLoad;

	/**
	 * This parameter can be used to enable (or disable) successful update of the HTML head element.
	 * The parameter is usually set in applications where the CSS or Javascript resource references change while interacting with the rendered page.
	 * Since ICEfaces 4.0 this feature is enabled by default because most of the times the JSF built-in update handling for the page header does not work.
	 *
	 * @since ICEfaces 3.1
	 */
	@ServletContextInitParameter("org.icefaces.generateHeadUpdate")
	private Boolean generateHeadUpdate;

	/**
	 * A value, in milliseconds, indicating the interval of how much earlier the warning that the session is about to expire is to be issued.
	 * For details on how to setup a callback that will be invoked on before session expiry see ice.onBeforeSessionExpiry API.
	 *
	 * @since ICEfaces 3.3
	 */
	@DurationUnit(ChronoUnit.MILLIS)
	@ServletContextInitParameter("org.icefaces.warnBeforeSessionExpiryInterval")
	private Duration warnBeforeSessionExpiryInterval;

	/**
	 * Setting org.icefaces.autoid to true causes the majority of standard JSF components to write their IDs to the page.
	 * This allows ICEfaces to apply page updates at a fine granularity.
	 * With this parameter set to false, page updates can only be applied for components that have manually specified IDs.
	 * This parameter is provided for fallback to completely standard behavior; it is not generally intended to be set to false for application use.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.autoid")
	private Boolean autoid;

	/**
	 * Setting org.icefaces.blockUIOnSubmit to true tells ICEfaces that the user interface (UI) should block any subsequent requests until the current request has been processed.
	 * This is provided to help prevent double-submits in applications that require this feature.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.blockUIOnSubmit")
	private Boolean blockUiOnSubmit;

	/**
	 * Applications or components that use ice.onElementUpdate javascript registration function can choose to use server or client side implementation for the callback matching of the elements that were updated or removed from the document.
	 * The client side implementation can be quite slow in Internet Explorer when large updates are received.
	 * Choosing the server side implementation is generally recommended because it is equally fast in all browsers at the expense of some memory and required processing power on the server.
	 *
	 * @since ICEfaces 3.4
	 */
	@ServletContextInitParameter("org.icefaces.clientSideElementUpdateDetermination")
	private Boolean clientSideElementUpdateDetermination;

	/**
	 * Setting org.icefaces.coalesceResources to true indicates to the ICEfaces core framework that the resources required by the rendered components are to be coalesced.
	 * The coalescing is applied to Javascript and CSS resources resulting into two separate resources, one for the coalesced Javascript and the other for the coalesced CSS. By reducing the numbers of requests made by the browser the page load time will improve significantly.
	 * <p>
	 * The feature uses the Resource dependencies metadata to coalesce the resources in the correct order.
	 * Any request issued after the first GET request (for the user session) that changes the type and number of required resources will trigger only the loading of the newly required resources.
	 * This behaviour avoids the continuous reloading of the coalesced resource, every time the set of required resources has changed.
	 * <p>
	 * mandatoryResourceConfiguration can be used to pre-define the resources that are needed right away (on session start).
	 * This way the application can be configured to deterministically coalesce the resources that are known to be used most of the time.
	 * <p>
	 * The default value for the parameter used to enable/disable the coalescing is false.
	 *
	 * @since ICEfaces 3.3
	 */
	@ServletContextInitParameter("org.icefaces.coalesceResources")
	private Boolean coalesceResources;

	/**
	 * Setting org.icefaces.compressDOM to true indicates to the ICEfaces core framework that the server-side DOM representation of the current view should be serialized and compressed between requests.
	 * The default value of false leaves the DOM representation intact between requests.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.compressDOM")
	private Boolean compressDom;

	/**
	 * By default, ICEfaces will not gzip compress any resource responses.
	 * Setting org.icefaces.compressedResources to true instructs ICEfaces to compress certain resources - namely compat component resources that are served via the CompatResourceServlet and ACE or application resources registered using one of the applicable methods in the ResourceRegistry (e.g. addSessionResource).
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.compressResources")
	private Boolean compressResources;

	/**
	 * Setting org.icefaces.connectionLostRedirectURI to a valid URI tells ICEfaces that when the Ajax Push connection is lost, that the client is to be redirected to the specified page.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.connectionLostRedirectURI")
	private String connectionLostRedirectUri;

	/**
	 * Setting org.icefaces.deltaSubmit to true tells ICEfaces that form submission should be handled in a special way to minimize what is sent across the wire.
	 * The parameters of a form post are compared to the previous submission and only those that have changed are sent to the server.
	 * The rest of the form parameters are reconstructed on the server from the cached DOM and then processed normally.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.deltaSubmit")
	private Boolean deltaSubmit;

	/**
	 * This parameter can be used to disable the popups rendered by default when a network error, session expiry or server error occurs.
	 * The parameter is usually set when an application or component developer wants to have rendered only the defined custom indicators.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.disableDefaultErrorPopups")
	private Boolean disableDefaultErrorPopups;

	@NestedConfigurationProperty
	private final FileEntry fileEntry = new FileEntry();

	/**
	 * By default, when ICEfaces is available, focus retention will ensure that the focused element in the page preserves its focus when the partial update modified the element.
	 * By setting the org.icefaces.focusManaged parameter to false, this feature can be disabled.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.focusManaged")
	private Boolean focusManaged;

	@NestedConfigurationProperty
	private final Ace ace = new Ace();

	/**
	 * By default, when using Ajax Push, the feature is activated in a lazy fashion.
	 * This means that the blocking connection used for doing Ajax Push is not activated until the first push is requested.
	 * Setting org.icefaces.lazyPush to false tells ICEfaces to automatically activate ICEpush for each session and each page in the entire application.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.lazyPush")
	private Boolean lazyPush;

	/**
	 * By default, when using window-scoped beans, the window management is activated in a lazy fashion.
	 * This means that if there are no window scoped beans or view scoped beans annotated with @WindowDisposed then no dispose-window request is sent upon window closure (detection of window closing is not enabled).
	 * Setting org.icefaces.lazyWindowScope to false tells ICEfaces to always send a dispose-window request upon window closure regardless of the window scope map being empty or not.
	 * This context parameter is application-wide.
	 *
	 * @since ICEfaces 3.0
	 */
	@ServletContextInitParameter("org.icefaces.lazyWindowScope")
	private Boolean lazyWindowScope;

	/**
	 * The org.icefaces.mandatoryResourceConfiguration context-parameter may be set to one of three values: none, all, or a space delimited list of components.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter(value = "org.icefaces.mandatoryResourceConfiguration", listSeparator = " ")
	private List<String> mandatoryResourceConfiguration;

	/**
	 * By default, when ICEfaces is available, FacesMessages are persisted across different partial submits from the same page.
	 * This is done to better support forms that use incremental validation via singleSubmit tag.
	 *
	 * @since ICEfaces 3.0
	 */
	@ServletContextInitParameter("org.icefaces.messagePersistence")
	private Boolean messagePersistence;

	/**
	 * Setting org.icefaces.publicContextPath to a valid path tells ICEfaces to replace the natural context path (usually, the web-application name) of all resources, form URLs, local bookmarks and local redirects to the specified path.
	 * This is useful when the application is served publicly through a reverse proxy or load balancer and one wants to avoid defining complex URL rewriting rules.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.publicContextPath")
	private String publicContextPath;

	@NestedConfigurationProperty
	private final Render render = new Render();

	/**
	 * This parameter can be used to replay the navigation rules when the page is reloaded.
	 * The parameter is usually set in applications where it is desirable to avoid the content being reset to the first page (before non-redirect navigation was triggered) when reloading.
	 *
	 * @since ICEfaces 3.0
	 */
	@ServletContextInitParameter("org.icefaces.replayNavigationOnReload")
	private Boolean replayNavigationOnReload;

	/**
	 * Setting org.icefaces.sessionExpiredRedirectURI to a valid URI tells ICEfaces that when the user session expires, that the client is to be redirected to the specified page.
	 * When using ICEpush, or {@code <icecore:refresh>}, the user will be redirected automatically on session expiry.
	 * Otherwise, they will be redirected on the next request after session expiry.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.sessionExpiredRedirectURI")
	private String sessionExpiredRedirectUri;

	/**
	 * Setting org.icefaces.sessionTimeoutRedirectURII to a valid URI tells ICEfaces that when the user session expires due to user inactivity, that the client is to be redirected to the specified page.
	 * When using ICEpush, or {@code <icecore:refresh>}, the user will be redirected automatically on session expiry.
	 * Otherwise, they will be redirected on the next request after session expiry.
	 *
	 * @since ICEfaces EE 4.1.0.GA, EE 3.3.0.GA_P04, 4.2
	 */
	@ServletContextInitParameter("org.icefaces.sessionTimeoutRedirectURI")
	private String sessionTimeoutRedirectUri;

	/**
	 * By default, ICEfaces does optimized serialization and submission of form elements based on the element that triggered the submission.
	 * Setting standardFormSerialization to true indicates that ICEfaces should do a normal, standard, full form submission.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.standardFormSerialization")
	private Boolean standardFormSerialization;

	/**
	 * The default value of false for org.icefaces.strictSessionTimeout indicates that ICEfaces should not interfere with container-managed control of session timeout.
	 * In this case, each user request and each Ajax Push request will extend the session.
	 * Setting org.icefaces.strictSessionTimeout to true indicates that ICEfaces should attempt to enforce the configured session timeout by ignoring any Ajax Push activity so that only user-triggered requests will extend the session.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.strictSessionTimeout")
	private Boolean strictSessionTimeout;

	/**
	 * By default, when using ICEfaces, Ajax-specified subtree diffing is always done.
	 * Setting org.icefaces.subtreeDiff to false indicates that ICEfaces will not perform a DOM diff on the rendered subtrees.
	 * Instead the rendered output of subtrees is sent directly to the browser, thus not updating the server-side DOM.
	 *
	 * @since ICEfaces 3.0
	 */
	@ServletContextInitParameter("org.icefaces.subtreeDiff")
	private Boolean subtreeDiff;

	/**
	 * The default value of "true" will cause ICEfaces to append random numbers to certain resource URLs, thereby preventing caching.
	 * This is useful for development, but can interfere with Portlet URL generation.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.uniqueResourceURLs")
	private Boolean uniqueResourceUrls;

	/**
	 * By default, ICEfaces adds a version number parameter to the URL of most resource types.
	 * The version number parameter is used to ensure that the resources can be cached by the browser and that previously cached resources are not used for newer builds or releases of the software.
	 *
	 * Normally, the version number parameter consists of the release version of the software (e.g. "3_3_0") and the build date ("130829" - Aug. 29th, 2013) = "v=3_3_0_130829".
	 *
	 * @since ICEfaces 3.3
	 */
	@ServletContextInitParameter(value = "org.icefaces.versionableTypes", listSeparator = " ")
	private List<String> versionableTypes;

	/**
	 * A value, in milliseconds, indicating how long window-scoped values should remain valid in the session after a reload or redirect occurs.
	 * This allows for postbacks that might occur quickly after a reload or redirect to successfully retrieve the relevant window-scoped values.
	 *
	 * @since ICEfaces 2.0
	 */
	@ServletContextInitParameter("org.icefaces.windowScopeExpiration")
	@DurationUnit(ChronoUnit.MILLIS)
	private Duration windowScopeExpiration;

	@Data
	public static class FileEntry {

		/**
		 * Setting org.icefaces.ace.fileEntry.requireJavascript to true causes the server side handling of ace:fileEntry file uploads to require that the browser have javascript enabled.
		 * <ul>
		 * <li>Javascript being enabled allows for ace:fileEntry to use AJAX for partial page updates.</li>
		 * <li>Integration between ace:fileEntry and Portlets is dependent on javascript being enabled.</li>
		 * <li>Automatic interoperability between ace:fileEntry and other upload components (in separate forms) depends on javascript being enabled.</li>
		 * </ul>
		 * With this parameter set to false, ace:fileEntry may not allow other upload components to function in the application, but ace:fileEntry will work without javascript being enabled in the browser, as long as the form's enctype property is set for multipart:
		 * <p>
		 * {@code <h:form enctype="multipart/form-data">}.
		 *
		 * @since ICEfaces 3.3.0 EE, 3.4.0
		 */
		@ServletContextInitParameter("org.icefaces.ace.fileEntry.requireJavascript")
		private Boolean requireJavascript;
	}

	@Data
	public static class Ace {

		/**
		 * This affects the Google Maps API version used with the ace:gMap component and its subcomponents.
		 * By default, the edge version is fetched from Google servers (which is 3.18 at the end of 2014).
		 * Google Maps allows developers to use previous, more stable versions of the API, if desired.
		 * This can be configured in ICEfaces with this context parameter, as shown below.
		 *
		 * @since ICEfaces 4.0
		 */
		@ServletContextInitParameter("org.icefaces.ace.gmapVersion")
		private String gmapVersion;
	}

	@Data
	public static class Render {

		/**
		 * Setting org.icefaces.render.auto to true causes ICEfaces to automatically apply DOM rendering to each page.
		 * If this is set to false, then DOM rendering and updating is turned off for the entire application.
		 *
		 * @since ICEfaces 2.0
		 */
		@ServletContextInitParameter("org.icefaces.render.auto")
		private Boolean auto;
	}
}
