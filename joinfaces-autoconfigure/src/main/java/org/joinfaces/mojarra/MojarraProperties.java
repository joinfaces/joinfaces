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

package org.joinfaces.mojarra;

import javax.el.ExpressionFactory;

import com.sun.faces.spi.InjectionProvider;
import com.sun.faces.spi.SerializationProvider;
import lombok.Getter;
import lombok.Setter;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of Mojarra.
 * Taken from
 * http://balusc.omnifaces.org/2015/09/what-mojarra-context-parameters-are.html
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.mojarra")
public class MojarraProperties implements ServletContextInitParameterConfigurationProperties {

	static final String PREFIX = "com.sun.faces.";

	/**
	 * Maximum time, in seconds, that client state will be considered valid by
	 * the default StateManager/ResponseStateManager implementations. If the
	 * time between requests exceeds the configured time, a
	 * javax.faces.application.ViewExpiredException. will be thrown. It is
	 * important to note that if this feature is enabled, and client requests
	 * are recieved with view state produced from a previous version, the
	 * ViewExpiredException will be thrown immediately.
	 */
	@ServletContextInitParameter(PREFIX + "clientStateTimeout")
	private Integer clientStateTimeout;

	/**
	 * The size, in bytes, of the buffer that is used to write client state. It
	 * should be noted, that the buffer used is split - half is for raw bytes,
	 * the other half is for the Base64 encoded characters of said bytes. So,
	 * for example, if the default, 8192, is used, then 4096 of that is used for
	 * the bytes, and the other 4096 is used for the Base64 encoded characters.
	 */
	@ServletContextInitParameter(PREFIX + "clientStateWriteBufferSize")
	private Integer clientStateWriteBufferSize;

	/**
	 * When true, the view is compressed after it is serialized and before
	 * base64 encoded. Works with client state saving only. As of 1.2_09, this
	 * option also impacts server side state saving when
	 * com.sun.faces.serializeServerState is set to true (this has a large
	 * impact of the size of the state in the session when using this option, at
	 * the expense of more CPU of course).
	 */
	@ServletContextInitParameter(PREFIX + "compressViewState")
	private Boolean compressViewState;

	/**
	 * Since Mojarra 2.2, the client state is by default always encrypted. When
	 * true, the client state encryption is disabled.
	 * See also com.sun.faces.ClientStateSavingPassword - recommendations for actual
	 * password?
	 */
	@ServletContextInitParameter(PREFIX + "disableClientStateEncryption")
	private Boolean disableClientStateEncryption;

	/**
	 * When true, the client state will as test be unserialized before writing
	 * and any serialization exceptions will be logged aInteger with a debug
	 * path to the cause of the serialization error.
	 */
	@ServletContextInitParameter(PREFIX + "enableClientStateDebugging")
	private Boolean enableClientStateDebugging;

	/**
	 * If true, generate random server view state ids. If false, create server
	 * view state ids sequentially. This resembles closely the JSF 1.x behavior,
	 * but this is more sensitive to CSRF.
	 */
	@ServletContextInitParameter(PREFIX + "generateUniqueServerStateIds")
	private Boolean generateUniqueServerStateIds;

	/**
	 * Defines the maximum number of serialized views stored in the session.
	 * Works with server state saving only (note the implementation
	 * unintentionally has swapped the meaning, as we cannot change this without
	 * breaking what people have become used to we have updated this here). See
	 * com.sun.faces.numberOfViewsInSession vs
	 * com.sun.faces.numberOfLogicalViews for detail.
	 */
	@ServletContextInitParameter(PREFIX + "numberOfLogicalViews")
	private Integer numberOfLogicalViews;

	/**
	 * Definies the maximum number of serialized logical views per view. Works
	 * with server state saving only (note the implementation unintentionally
	 * has swapped the meaning, as we cannot change this without breaking what
	 * people have become used to we have updated this here) See
	 * com.sun.faces.numberOfViewsInSession vs
	 * com.sun.faces.numberOfLogicalViews for detail.
	 */
	@ServletContextInitParameter(PREFIX + "numberOfViewsInSession")
	private Integer numberOfViewsInSession;

	/**
	 * If enabled the component state (not the tree) will be serialized before
	 * being stored in the session. This may be desirable for applications that
	 * may have issues with view state being sensitive to model changes after
	 * state saving which are reflected back in view state. This has since JSF
	 * 2.2 been replaced by javax.faces.SERIALIZE_SERVER_STATE.
	 */
	@ServletContextInitParameter(PREFIX + "serializeServerState")
	private Boolean serializeServerState;

	/**
	 * Per the renderkit doc specification, the state information for the view
	 * will be written out prior to closing the form tag. However, it may be
	 * desirable to have the state information written out after the opening
	 * form tag. If this is the case, specifiy this parameter in the web.xml
	 * with a value of false.
	 */
	@ServletContextInitParameter(PREFIX + "writeStateAtFormEnd")
	private Boolean writeStateAtFormEnd;

	/**
	 * If true, allow children of h:inputText and h:outputText to be rendered.
	 * In 1.2, they would always be rendered before the value of tag. As of 2.0,
	 * children of UIInput and UIOutput components will not be rendered by the
	 * default renderer implementations. Set this option to true if this
	 * behavior is required, but note that doing so may cause issues when using
	 * Ajax. See issue 1154 for details.
	 */
	@ServletContextInitParameter(PREFIX + "allowTextChildren")
	private Boolean allowTextChildren;

	/**
	 * If false, don't use autocomplete="off" on view state hidden input field.
	 * This attribute is by default always rendered in order to fix a Firefox
	 * related bug, but it is invalid on a hidden input field as per w3 HTML
	 * validator. So, when false, then beware of the Firefox bug which may
	 * trigger "unexpected" ViewExpiredException. See issue 1129 for details.
	 */
	@ServletContextInitParameter(PREFIX + "autoCompleteOffOnViewState")
	private Boolean autoCompleteOffOnViewState;

	/**
	 * If true, then the JavaScript rendered by h:commandLink will be compressed
	 * to reduce the amount of whitespace transmitted in the response. If false
	 * then the JavaScript will be rendered to the client in a well formatted
	 * manner.
	 */
	@ServletContextInitParameter(PREFIX + "compressJavaScript")
	private Boolean compressJavaScript;

	/**
	 * By default any characters above a certain range will be escaped as either
	 * an HTML entity or a decimal reference. This behavior is not always
	 * desirable. To allow more flexibility how content is rendered to a client,
	 * this option was introduced. Valid configuration values are: false, true,
	 * and auto with false being the default. When the option value is false,
	 * Mojarra will continue to escaped no matter the response encoding type. If
	 * the configuration value is true, Then no escaping will occur assuming
	 * that the response encoding can properly handle all characters. If the
	 * configuration option is auto then the response encoding will be checked.
	 * If the encoding is of the UTF family of encodings no unicode or html
	 * entity encoding will occur, however, if the response stream encoding is
	 * ISO-8859-1 then the ISO characters above a certain range will be encoded
	 * as HTML entities and any characters above that range will be written as
	 * decimal references.
	 */
	@ServletContextInitParameter(PREFIX + "disableUnicodeEscaping")
	private String disableUnicodeEscaping;

	/**
	 * If true, then component ID uniqueness won't be checked if ProjectStage is
	 * Production to enhance performance. See issue 2414 for details.
	 */
	@ServletContextInitParameter(PREFIX + "disableIdUniquenessCheck")
	private Boolean disableIdUniquenessCheck;

	/**
	 * If true, inlined JavaScript rendered by the HTML ResponseWriter
	 * implementation will be rendered so that the script is hidden from older
	 * browser implementations which does not recognize <script/> elements.
	 */
	@ServletContextInitParameter(PREFIX + "enabledJSStyleHiding")
	private Boolean enabledJsStyleHiding;

	/**
	 * If false, attribute values with javascript: or script: will not be
	 * rendered within attribute values to prevent potential XSS attacks.
	 */
	@ServletContextInitParameter(PREFIX + "enableScriptsInAttributeValues")
	private Boolean enableScriptsInAttributeValues;

	/**
	 * If true, the view state hidden field will be rendered with both the id
	 * and name attributes having the value of "javax.faces.ViewState". This is
	 * what the spec requires, however, if there are multiple forms within a
	 * view and the response content-type is XHTML, the result will be XHTML
	 * that will fail validation due to multiple ID attributes with the same
	 * value: javax.faces.ViewState. Setting this parameter to false will result
	 * in the ID attribute not being rendered. Keep in mind however, that doing
	 * this may break integration with AJAX frameworks that get the state field
	 * via ID. See issue 433 for details.
	 */
	@ServletContextInitParameter(PREFIX + "enableViewStateIdRendering")
	private Boolean enableViewStateIdRendering;

	/**
	 * For the case where a browser supports XHTML and HTML without a quality.
	 * When enabled and this case occurs, then XHTML will be set as the content
	 * type. This setting is not recommended and should be only used to fix
	 * broken applications designed with XHTML output in mind instead of HTML(5)
	 * output.
	 */
	@ServletContextInitParameter(PREFIX + "preferXHTML")
	private Boolean preferXhtml;

	/**
	 * This parameter specifies the size, in bytes, of the buffer that is used
	 * to write all generated JSP content excluding state. Note that this is
	 * ignored when Facelets is used. For Facelets, use
	 * javax.faces.FACELETS_BUFFER_SIZE instead.
	 */
	@ServletContextInitParameter(PREFIX + "responseBufferSize")
	private Integer responseBufferSize;

	/**
	 * If true, cache the modification time of the resource and use the cached
	 * time to tell if the resource needs to be refreshed.
	 */
	@ServletContextInitParameter(PREFIX + "cacheResourceModificationTimestamp")
	private Boolean cacheResourceModificationTimestamp;

	/**
	 * Specify mime types that should be gzip compressed. Mime types can be
	 * specified by their exact name (i.e. text/css) or a wildcard can be used
	 * after the slash (i.e. text/*). The resource will not be compressed on
	 * each request, instead when building the cache, the resource will be
	 * compressed to a temporary directory and those bytes will be served
	 * instead.
	 */
	@ServletContextInitParameter(PREFIX + "compressableMimeTypes")
	private String compressableMimeTypes;

	/**
	 * This affects the value of the Expires response header that will be sent
	 * for a resource. The logic is basically Date.getTime() +
	 * valueOf(defaultResourceManxAge). Increase this value to increase the
	 * amount of time that a Resource is valid. The value is in milliseconds (so
	 * the default value of 604800000 is 7 days).
	 */
	@ServletContextInitParameter(PREFIX + "defaultResourceMaxAge")
	private Integer defaultResourceMaxAge;

	/**
	 * See issue 3684 for details.
	 */
	@ServletContextInitParameter(PREFIX + "enableFaceletsResourceResolverCompositeComponents")
	private Boolean enableFaceletsResourceResolverCompositeComponents;

	/**
	 * If enabled, the runtime will check for the existence of a resource
	 * library before checking for the resource itself. If not found, an
	 * appropriate error message will be included in the log and in the view if
	 * ProjectStage is Development.
	 */
	@ServletContextInitParameter(PREFIX + "enableMissingResourceLibraryDetection")
	private Boolean enableMissingResourceLibraryDetection;

	/**
	 * When javax.faces.PROJECT_STATE is Production, UnitTest, or SystemTest
	 * resource paths will be cached to reduce the overhead of resource path
	 * compuation. By default, updates (i.e. new files, new directories, new
	 * versions, etc.) will be checked for every 5 minutes. If a change is
	 * detected, the cache will be cleared and rebuilt. If the value of this
	 * option is -1, the cache will never be cleared and new resources will not
	 * be picked up. The value is in minutes.
	 */
	@ServletContextInitParameter(PREFIX + "resourceUpdateCheckPeriod")
	private Integer resourceUpdateCheckPeriod;

	/**
	 * If true, makes it so every session attribute is touched in for every
	 * request through the lifecycle. This makes it much harder to make mistakes
	 * that cause session replication to fail.
	 */
	@ServletContextInitParameter(PREFIX + "enableAgressiveSessionDirtying")
	private Boolean enableAgressiveSessionDirtying;

	/**
	 * If true, signal JSF that the application is deployed to a clustered
	 * environment, so that session dirtying will be explicitly performed,
	 * hereby forcing session replication. This is automatically true when
	 * <distributable /> entry is present in web.xml.
	 */
	@ServletContextInitParameter(PREFIX + "enableDistributable")
	private Boolean enableDistributable;

	/**
	 * The value of this context init parameter is a whitespace separated list
	 * of values that control which class packages are scanned for javax.faces
	 * annotations. To restrict which jars/packages are scanned, use the
	 * following entry format: jar:'jar name':'comma separated list of packages'
	 * So an example would be: jar:a.jar:com.acme.package1,com.acme.package2.
	 */
	@ServletContextInitParameter(PREFIX + "annotationScanPackages")
	private String annotationScanPackages;

	/**
	 * If true then all web configuration information (context initialization
	 * parameters and environment entries) will be written to the log. This is
	 * useful during development to confirm your application is configured as
	 * expected.
	 */
	@ServletContextInitParameter(PREFIX + "displayConfiguration")
	private Boolean displayConfiguration;

	/**
	 * When true, enable validation of standard Core TagLibs, at the expense of
	 * a slightly slower start time.
	 */
	@ServletContextInitParameter(PREFIX + "enableCoreTagLibValidator")
	private Boolean enableCoreTagLibValidator;

	/**
	 * When true, enable validation of standard Html TagLibs, at the expense of
	 * a slightly slower start time.
	 */
	@ServletContextInitParameter(PREFIX + "enableHtmlTagLibValidator")
	private Boolean enableHtmlTagLibValidator;

	/**
	 * When true, managed beans will be validated when first created. If false,
	 * managed beans will be validated when the application is started, at the
	 * expense of a slightly slower start time.
	 */
	@ServletContextInitParameter(PREFIX + "enableLazyBeanValidation")
	private Boolean enableLazyBeanValidation;

	/**
	 * When enabled, the runtime initialization and default ResourceHandler
	 * implementation will use threads to perform their functions. Set this
	 * value to false if threads aren't desired (as in the case of running
	 * within the Google Application Engine). Note that when this option is
	 * disabled, the ResourceHandler will not pick up new versions of resources
	 * when ProjectStage is development. See issue 2385 for details.
	 */
	@ServletContextInitParameter(PREFIX + "enableThreading")
	private Boolean enableThreading;

	/**
	 * There is a chance for an NPE in
	 * com.sun.faces.application.WebappLifecycleListener with some
	 * configurations. Take for example, installing JSF in a container such that
	 * JSF will be available to all web applications. The NPE will occur for an
	 * application that doesn't have the FacesServlet defined within its
	 * web.xml. The workaround for this issue is, within the global web.xml for
	 * the container (JBoss and Tomcat both have one) add either a FacesServlet
	 * definition (no mapping) or add the context init parameter,
	 * com.sun.faces.forceLoadConfiguration, with a value of true. See issue 670
	 * for details.
	 */
	@ServletContextInitParameter(PREFIX + "forceLoadConfiguration")
	private Boolean forceLoadConfiguration;

	/**
	 * When true, enable validation of faces-config.xml files, at the expense of
	 * a slightly slower start time.
	 */
	@ServletContextInitParameter(PREFIX + "validateXml")
	private Boolean validateXml;

	/**
	 * When true, JSF makes during startup sure all that registered managed
	 * beans components, validators, etc can be instantiated by the runtime, at
	 * the expense of a slightly slower start time.
	 */
	@ServletContextInitParameter(PREFIX + "verifyObjects")
	private Boolean verifyObjects;

	/**
	 * If true, the act of calling getExternalContext().getFlash() on the
	 * FacesContext at startup or shutdown time will cause a no-op
	 * implementation of the Flash to be returned. This should prevent
	 * startup/shutdown exceptions caused by invalid/stale flash cookies. See
	 * "bugdb 17024459" for details.
	 */
	@ServletContextInitParameter(PREFIX + "enableTransitionTimeNoOpFlash")
	private Boolean enableTransitionTimeNoOpFlash;

	/**
	 * This parameter specifies a class that implements the ExpressionFactory.
	 */
	@ServletContextInitParameter(PREFIX + "expressionFactory")
	private Class<? extends ExpressionFactory> expressionFactory;

	/**
	 * If true, then the flash cookie will always be written, regardless of
	 * whether or not the flash has data. This should prevent problems on
	 * multiple successive redirects. See issue 3735 for details.
	 */
	@ServletContextInitParameter(PREFIX + "forceAlwaysWriteFlashCookie")
	private Boolean forceAlwaysWriteFlashCookie;

	/**
	 * This parameter specifies a class that implements the InjectionProvider.
	 */
	@ServletContextInitParameter(PREFIX + "injectionProvider")
	private Class<? extends InjectionProvider> injectionProvider;

	/**
	 * If true, then view state hidden field is namespaced according to
	 * NamingContainer rules. See issue 3031 for details.
	 */
	@ServletContextInitParameter(PREFIX + "namespaceParameters")
	private Boolean namespaceParameters;

	/**
	 * If true, allow EL Coercion to use JSF Custom converters.
	 */
	@ServletContextInitParameter(PREFIX + "registerConverterPropertyEditors")
	private Boolean registerConverterPropertyEditors;

	/**
	 * The servlet specification defines an optional header that can be sent by
	 * a container to communicate the version of the JSP/Servlet the response
	 * was generated by. If this is enabled, then X-Powered-By=JSF/2.2 header is
	 * included in all responses.
	 */
	@ServletContextInitParameter(PREFIX + "sendPoweredByHeader")
	private Boolean sendPoweredByHeader;

	/**
	 * This parameter specifies a class that implements the
	 * SerializationProvider SPI. This implementation represents a hook the JSF
	 * implementation will use in order to allow the use of alternate
	 * Serialization implementations.
	 */
	@ServletContextInitParameter(PREFIX + "serializationProvider")
	private Class<? extends SerializationProvider> serializationProvider;

	/**
	 * The value of this option is a fully qualfified class that extends the
	 * com.sun.faces.facelets.FaceletFactory abstract class. This option allows
	 * developers to customize the behavior or completely replace the default
	 * com.sun.faces.facelets.FaceletFactory implementation. Some rules about
	 * what is expected of custom implementations.If the custom implementation
	 * has a public single argument constructor where the argument type is
	 * com.sun.faces.facelets.FaceletFactory, the default FaceletFactory
	 * implementation will be passed to the constructor. If the single argument
	 * constructor is not present, the custom implementation will be constructed
	 * by invoking a public no-argument constructor.
	 */
	@ServletContextInitParameter(PREFIX + "faceletFactory")
	private Class<?> faceletFactory;

	/**
	 * When this context param is set to true, this would set a feature on the SAX
	 * parser to disallow DOCTYPE declarations. When set to false, this would set
	 * a feature on the SAX parser to allow DOCTYPE declarations. When this context
	 * param is not specified, whether or not DOCTYPE declarations are allowed would
	 * just depend on the SAXParserFactory implementation in use, as is the case today.
	 */
	@ServletContextInitParameter(PREFIX + "disallowDoctypeDecl")
	private Boolean disallowDoctypeDecl;
}
