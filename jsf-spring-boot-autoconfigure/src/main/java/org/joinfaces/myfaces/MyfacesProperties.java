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

package org.joinfaces.myfaces;

import java.util.Comparator;
import java.util.List;

import javax.el.ELResolver;
import javax.el.ExpressionFactory;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.Predicate;
import org.apache.myfaces.application.ResourceHandlerImpl;
import org.apache.myfaces.application.StateCache;
import org.apache.myfaces.config.FacesConfigValidator;
import org.apache.myfaces.config.annotation.CdiAnnotationProviderExtension;
import org.apache.myfaces.config.annotation.DefaultAnnotationProvider;
import org.apache.myfaces.el.unified.ResolverBuilderBase;
import org.apache.myfaces.lifecycle.ClientWindowFactoryImpl;
import org.apache.myfaces.renderkit.ErrorPageWriter;
import org.apache.myfaces.renderkit.html.HtmlResponseStateManager;
import org.apache.myfaces.resource.InternalClassLoaderResourceLoader;
import org.apache.myfaces.shared.util.serial.SerialFactory;
import org.apache.myfaces.spi.ServiceProviderFinder;
import org.apache.myfaces.view.facelets.DefaultFaceletsStateManagementStrategy;
import org.apache.myfaces.view.facelets.FaceletViewDeclarationLanguage;
import org.apache.myfaces.view.facelets.el.ContextAwareUtils;
import org.apache.myfaces.view.facelets.impl.FaceletCompositionContextImpl;
import org.apache.myfaces.view.facelets.pool.ViewPool;
import org.apache.myfaces.webapp.AbstractFacesInitializer;
import org.apache.myfaces.webapp.FacesInitializer;
import org.joinfaces.configuration.NestedProperty;
import org.joinfaces.configuration.ServletContextInitParameter;
import org.joinfaces.configuration.ServletContextInitParameterConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties of MyFaces.
 * Taken from https://myfaces.apache.org/core22/myfaces-impl/webconfig.html and
 * from
 * http://myfaces.apache.org/core22/myfaces-impl/apidocs/constant-values.html
 * @author Marcelo Fernandes
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf.myfaces")
public class MyfacesProperties implements ServletContextInitParameterConfigurationProperties {

	static final String PREFFIX = "org.apache.myfaces.";

	/**
	 * Indicate if log all web config params should be done before initialize
	 * the webapp.
	 */
	@ServletContextInitParameter(AbstractFacesInitializer.INIT_PARAM_LOG_WEB_CONTEXT_PARAMS)
	private String logWebContextParams;

	/**
	 * If value is a String instance and this param is true, pass it directly
	 * without try any change.
	 */
	@ServletContextInitParameter(PREFFIX + "ENUM_CONVERTER_ALLOW_STRING_PASSTROUGH")
	private Boolean enumConverterAllowStringPasstrough;

	/**
	 * Deprecated: use JSF 2.0 ExceptionHandler.
	 */
	@ServletContextInitParameter(PREFFIX + "ERROR_HANDLER")
	private String errorHandler;

	/**
	 * Controls the size of the cache used to 'remember' if a view exists or
	 * not.
	 */
	@ServletContextInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_SIZE")
	private Integer checkedViewidCacheSize;

	/**
	 * Enable or disable a cache used to 'remember' if a view exists or not and
	 * reduce the impact " + "of sucesive calls to
	 * ExternalContext.getResource().
	 */
	@ServletContextInitParameter(PREFFIX + "CHECKED_VIEWID_CACHE_ENABLED")
	private Boolean checkedViewidCacheEnabled;

	/**
	 * If true, rendered HTML code will be formatted, so that it is "human
	 * readable".
	 */
	@ServletContextInitParameter(PREFFIX + "PRETTY_HTML")
	private Boolean prettyHtml;

	/**
	 * This parameter tells MyFaces if javascript code should be allowed in the
	 * rendered HTML output.
	 */
	@ServletContextInitParameter(PREFFIX + "ALLOW_JAVASCRIPT")
	private Boolean allowJavascript;

	/**
	 * Set the time in seconds that check for updates of web.
	 */
	@ServletContextInitParameter(PREFFIX + "CONFIG_REFRESH_PERIOD")
	private Integer configRefreshPeriod;

	/**
	 * Set the view state using a javascript function instead a hidden input
	 * field.
	 */
	@ServletContextInitParameter(PREFFIX + "VIEWSTATE_JAVASCRIPT")
	private Boolean viewstateJavascript;

	/**
	 * Define if the input field that should store the state.
	 */
	@ServletContextInitParameter(PREFFIX + "RENDER_VIEWSTATE_ID")
	private Boolean renderViewstateId;

	/**
	 * Use "&amp;" entity instead a plain "&" character within HTML.
	 */
	@ServletContextInitParameter(PREFFIX + "STRICT_XHTML_LINKS")
	private Boolean strictXhtmlLinks;

	/**
	 * This param renders the clear javascript on button necessary only for
	 * compatibility with hidden fields feature of myfaces.
	 */
	@ServletContextInitParameter(PREFFIX + "RENDER_CLEAR_JAVASCRIPT_FOR_BUTTON")
	private Boolean renderClearJavascriptForButton;

	/**
	 * This param renders hidden fields at the end of h:form for link params
	 * when h:commandLink + f:param is used, instead use javascript to create
	 * them.
	 */
	@ServletContextInitParameter(PREFFIX + "RENDER_HIDDEN_FIELDS_FOR_LINK_PARAMS")
	private Boolean renderHiddenFieldsForLinkParams;

	/**
	 * Add a code that save the form before submit using a link.
	 */
	@ServletContextInitParameter(PREFFIX + "SAVE_FORM_SUBMIT_LINK_IE")
	private Boolean saveFormSubmitLinkIe;

	/**
	 * Define an alternate class name that will be used to initialize MyFaces,
	 * instead the default javax.
	 */
	@ServletContextInitParameter(PREFFIX + "DELEGATE_FACES_SERVLET")
	private Class<?> delegateFacesServlet;

	/**
	 * Indicate if the facelet associated to the view should be reapplied when
	 * the view is refreshed.
	 */
	@ServletContextInitParameter(FaceletViewDeclarationLanguage.REFRESH_TRANSIENT_BUILD_ON_PSS)
	private String refreshTransientBuildOnPss;

	/**
	 * Enable or disable a special mode that enable full state for parent
	 * components containing c:if, c:forEach, c:choose and ui:include with
	 * src=ELExpression.
	 */
	@ServletContextInitParameter(PREFFIX + "REFRESH_TRANSIENT_BUILD_ON_PSS_PRESERVE_STATE")
	private Boolean refreshTransientBuildOnPssPreserveState;

	/**
	 * If set to true, tag library XML files and faces config XML files using
	 * schema will be validated during application start up.
	 */
	@ServletContextInitParameter(PREFFIX + "VALIDATE_XML")
	private Boolean validateXml;

	/**
	 * Wrap content inside script with xml comment to prevent old browsers to
	 * display it.
	 */
	@ServletContextInitParameter(PREFFIX + "WRAP_SCRIPT_CONTENT_WITH_XML_COMMENT_TAG")
	private Boolean wrapScriptContentWithXmlCommentTag;

	/**
	 * If set true, render the form submit script inline, as in myfaces core 1.
	 */
	@ServletContextInitParameter(PREFFIX + "RENDER_FORM_SUBMIT_SCRIPT_INLINE")
	private Boolean renderFormSubmitScriptInline;

	/**
	 * Enable/disable DebugPhaseListener feature, with provide useful
	 * information about ValueHolder variables (submittedValue, localValue,
	 * value).
	 */
	@ServletContextInitParameter(PREFFIX + "DEBUG_PHASE_LISTENER")
	private Boolean debugPhaseListener;

	/**
	 * Detect if a target (usually head) should be update for the current view
	 * in an ajax render operation.
	 */
	@ServletContextInitParameter(PREFFIX + "STRICT_JSF_2_REFRESH_TARGET_AJAX")
	private Boolean strictJsf2RefreshTargetAjax;

	/**
	 * Change default getType() behavior for composite component EL resolver,
	 * from return null (see JSF 2_0 spec section 5_6_2_2) to use the metadata
	 * information added by composite:attribute, ensuring components working
	 * with chained EL expressions to find the right type when a getType() is
	 * called over the source EL expression.
	 */
	@ServletContextInitParameter(PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER")
	private String strictJsf2CcElResolver;

	/**
	 * Define the default content type that the default ResponseWriter
	 * generates, when no match can be derived from HTTP Accept Header.
	 */
	@ServletContextInitParameter(PREFFIX + "DEFAULT_RESPONSE_WRITER_CONTENT_TYPE_MODE")
	private String DefaultResponseWriterContentTypeMode;

	/**
	 * Enable or disable a cache used to 'remember' the generated facelets
	 * unique ids " + "and reduce the impact over memory usage.
	 */
	@ServletContextInitParameter(PREFFIX + "VIEW_UNIQUE_IDS_CACHE_ENABLED")
	private Boolean viewUniqueIdsCacheEnabled;

	/**
	 * Set the size of the cache used to store strings generated using
	 * SectionUniqueIdCounter for component ids.
	 */
	@ServletContextInitParameter(PREFFIX + "COMPONENT_UNIQUE_IDS_CACHE_SIZE")
	private Integer componentUniqueIdsCacheSize;

	/**
	 * If set false, myfaces won't support JSP and javax.faces.el. JSP are
	 * deprecated in " + "JSF 2.X, javax.faces.el in in JSF 1.2. Default value
	 * is true.
	 */
	@ServletContextInitParameter(PREFFIX + "SUPPORT_JSP_AND_FACES_EL")
	private Boolean supportJspAndFacesEl;

	/**
	 * When the application runs inside Google Application Engine container
	 * (GAE), indicate which jar files should be scanned for files
	 * (faces-config, facelets taglib or annotations).
	 */
	@ServletContextInitParameter(PREFFIX + "GAE_JSF_JAR_FILES")
	private String gaeJsfJarFiles;

	/**
	 * When the application runs inside Google Application Engine container
	 * (GAE), indicate which jar files should be scanned for annotations.
	 */
	@ServletContextInitParameter(PREFFIX + "GAE_JSF_ANNOTATIONS_JAR_FILES")
	private String gaeJsfAnnotationsJarFiles;

	/**
	 * If this param is set to true, a check will be done in Restore View Phase
	 * to check if the viewId exists or not and if it does not exists, a 404
	 * response will be thrown.
	 */
	@ServletContextInitParameter(PREFFIX + "STRICT_JSF_2_VIEW_NOT_FOUND")
	private Boolean strictJsf2ViewNotFound;

	/**
	 * Enable or disable an early flush which allows to send e.g. the HTML-Head
	 * to the client " + "while the rest gets rendered. It's a well known
	 * technique to reduce the time for loading a page.
	 */
	@ServletContextInitParameter(PREFFIX + "EARLY_FLUSH_ENABLED")
	private Boolean earlyFlushEnabled;

	/**
	 * Enable or disable CDI support for converters.
	 */
	@ServletContextInitParameter(PREFFIX + "CDI_MANAGED_CONVERTERS_ENABLED")
	private Boolean cdiManagedConvertersEnabled;

	/**
	 * Enable or disable CDI support for validators.
	 */
	@ServletContextInitParameter(PREFFIX + "CDI_MANAGED_VALIDATORS_ENABLED")
	private Boolean cdiManagedValidatorsEnabled;

	/**
	 * This param makes components like c:set, ui:param and templating
	 * components like ui:decorate, ui:composition and ui:include to behave like
	 * the ones provided originally in facelets 1_1_x.
	 */
	@ServletContextInitParameter(PREFFIX + "STRICT_JSF_2_FACELETS_COMPATIBILITY")
	private Boolean strictJsf2FaceletsCompatibility;

	/**
	 * This param makes h:form component to render the view state and other
	 * hidden fields at the beginning of the form.
	 */
	@ServletContextInitParameter(PREFFIX + "RENDER_FORM_VIEW_STATE_AT_BEGIN")
	private Boolean renderFormViewStateAtBegin;

	/**
	 * Defines whether flash scope is disabled, preventing add the Flash cookie
	 * to the response.
	 */
	@ServletContextInitParameter(PREFFIX + "FLASH_SCOPE_DISABLED")
	private Boolean flashScopeDisabled;

	/**
	 * Defines the amount (default = 20) of the latest views are stored in
	 * session.
	 */
	@ServletContextInitParameter(PREFFIX + "NUMBER_OF_VIEWS_IN_SESSION")
	private Integer numberOfViewsInSession;

	/**
	 * Indicates the amount of views (default is not active) that should be
	 * stored in session between sequential POST or POST-REDIRECT-GET if org.
	 */
	@ServletContextInitParameter(PREFFIX + "NUMBER_OF_SEQUENTIAL_VIEWS_IN_SESSION")
	private Integer numberOfSequentialViewsInSession;

	/**
	 * Indicate the max number of flash tokens stored into session.
	 */
	@ServletContextInitParameter(PREFFIX + "NUMBER_OF_FLASH_TOKENS_IN_SESSION")
	private Integer numberOfFlashTokensInSession;

	/**
	 * Indicate the max number of client window ids stored into session by faces
	 * flow.
	 */
	@ServletContextInitParameter(PREFFIX + "FACES_FLOW_CLIENT_WINDOW_IDS_IN_SESSION")
	private Integer facesFlowClientWindowIdsInSession;

	/**
	 * This parameter specifies whether or not the ImportHandler will be
	 * supported.
	 */
	@ServletContextInitParameter(PREFFIX + "SUPPORT_EL_3_IMPORT_HANDLER")
	private Boolean supportEl3ImportHandler;

	/**
	 * Set the max time in miliseconds set on the "Expires" header for a
	 * resource rendered by the default ResourceHandler.
	 */
	@ServletContextInitParameter(PREFFIX + "RESOURCE_MAX_TIME_EXPIRES")
	private Integer resourceMaxTimeExpires;

	/**
	 * Controls the size of the cache used to check if a resource exists or not.
	 */
	@ServletContextInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_SIZE")
	private Integer resourceHandlerCacheSize;

	/**
	 * Enable or disable the cache used to "remember" if a resource handled by
	 * the default ResourceHandler exists or not.
	 */
	@ServletContextInitParameter(PREFFIX + "RESOURCE_HANDLER_CACHE_ENABLED")
	private Boolean resourceHandlerCacheEnabled;

	/**
	 * Indicate if the view state is encrypted or not.
	 */
	@ServletContextInitParameter(PREFFIX + "USE_ENCRYPTION")
	private Boolean useEncryption;

	/**
	 * Defines the secret (Base64 encoded) used to initialize the secret key for
	 * encryption algorithm.
	 */
	@ServletContextInitParameter(PREFFIX + "SECRET")
	private String secret;

	/**
	 * If is set to "false", the secret key used for encryption algorithm is not
	 * cached.
	 */
	@ServletContextInitParameter(PREFFIX + "SECRET.CACHE")
	private Boolean secretCache;

	/**
	 * Indicate the encryption algorithm used for encrypt the view state.
	 */
	@ServletContextInitParameter(PREFFIX + "ALGORITHM")
	private String algorithm;

	/**
	 * Defines the initialization vector (Base64 encoded) used for the
	 * encryption algorithm.
	 */
	@ServletContextInitParameter(PREFFIX + "ALGORITHM.IV")
	private String algorithmIv;

	/**
	 * Defines the default mode and padding used for the encryption algorithm.
	 */
	@ServletContextInitParameter(PREFFIX + "ALGORITHM.PARAMETERS")
	private String algorithmParameters;

	/**
	 * Defines the factory class name using for serialize/deserialize the view
	 * state returned by state manager into a byte array.
	 */
	@ServletContextInitParameter(PREFFIX + "SERIAL_FACTORY")
	private Class<? extends SerialFactory> serialFactory;

	/**
	 * Indicate if the view state should be compressed before
	 * encrypted(optional) and encoded.
	 */
	@ServletContextInitParameter(PREFFIX + "COMPRESS_STATE_IN_CLIENT")
	private Boolean compressStateInClient;

	/**
	 * Indicate the algorithm used to calculate the Message Authentication Code
	 * that is added to the view state.
	 */
	@ServletContextInitParameter(PREFFIX + "MAC_ALGORITHM")
	private String macAlgorithm;

	/**
	 * Define the initialization code that are used to initialize the secret key
	 * used on the Message Authentication Code algorithm.
	 */
	@ServletContextInitParameter(PREFFIX + "MAC_SECRET")
	private String macSecret;

	/**
	 * If is set to "false", the secret key used for MAC algorithm is not cached.
	 */
	@ServletContextInitParameter(PREFFIX + "MAC_SECRET.CACHE")
	private Boolean macSecretCache;

	/**
	 * Indicate if the classes associated to components, converters, validators
	 * or behaviors should be loaded as soon as they are added to the current
	 * application instance or instead loaded in a lazy way.
	 */
	@ServletContextInitParameter(PREFFIX + "LAZY_LOAD_CONFIG_OBJECTS")
	private Boolean lazyLoadConfigObjects;

	/**
	 * Allow slash in the library name of a Resource.
	 */
	@ServletContextInitParameter(ResourceHandlerImpl.INIT_PARAM_STRICT_JSF_2_ALLOW_SLASH_LIBRARY_NAME)
	private Boolean strictJsf2AllowSlashLibraryName;

	/**
	 * Define the default buffer size that is used between Resource.
	 */
	@ServletContextInitParameter(ResourceHandlerImpl.INIT_PARAM_RESOURCE_BUFFER_SIZE)
	private Integer resourceBufferSize;

	/**
	 * Defines how to generate the csrf session token.
	 */
	@ServletContextInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_PARAM)
	private String randomKeyInCsrfSessionToken;

	/**
	 * Set the default length of the random key used for the csrf session token.
	 */
	@ServletContextInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_LENGTH_PARAM)
	private Integer randomKeyInCsrfSessionTokenLength;

	/**
	 * Sets the random class to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_CLASS_PARAM)
	private String randomKeyInCsrfSessionTokenSecureRandomClass;

	/**
	 * Sets the random provider to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_PROVIDER_PARAM)
	private String randomKeyInCsrfSessionTokenSecureRandomProvider;

	/**
	 * Sets the random algorithm to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(StateCache.RANDOM_KEY_IN_CSRF_SESSION_TOKEN_SECURE_RANDOM_ALGORITM_PARAM)
	private String randomKeyInCsrfSessionTokenSecureRandomAlgoritm;

	/**
	 * Define the time in minutes where the view state is valid when client side
	 * state saving is used.
	 */
	@ServletContextInitParameter(PREFFIX + "CLIENT_VIEW_STATE_TIMEOUT")
	private Integer clientViewStateTimeout;

	/**
	 * Indicate if the state should be serialized before save it on the session.
	 */
	@ServletContextInitParameter(PREFFIX + "SERIALIZE_STATE_IN_SESSION")
	private Boolean serializeStateInSession;

	/**
	 * Indicates that the serialized state will be compressed before it is
	 * written to the session.
	 */
	@ServletContextInitParameter(PREFFIX + "COMPRESS_STATE_IN_SESSION")
	private Boolean compressStateInSession;

	/**
	 * This parameter has been removed from 2.
	 */
	@ServletContextInitParameter(PREFFIX + "CACHE_OLD_VIEWS_IN_SESSION_MODE")
	private Boolean cacheOldViewsInSessionMode;

	/**
	 * Allow use flash scope to keep track of the views used in session and the
	 * previous ones, so server side state saving can delete old views even if
	 * POST-REDIRECT-GET pattern is used.
	 */
	@ServletContextInitParameter(PREFFIX + "USE_FLASH_SCOPE_PURGE_VIEWS_IN_SESSION")
	private Boolean useFlashScopePurgeViewsInSession;

	/**
	 * Adds a random key to the generated view state session token.
	 */
	@ServletContextInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN")
	private String randomKeyInViewStateSessionToken;

	/**
	 * Set the default length of the random key added to the view state session
	 * token.
	 */
	@ServletContextInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_LENGTH")
	private Integer randomKeyInViewStateSessionTokenLength;

	/**
	 * Sets the random class to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_CLASS")
	private String randomKeyInViewStateSessionTokenSecureRandomClass;

	/**
	 * Sets the random provider to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_PROVIDER")
	private String randomKeyInViewStateSessionTokenSecureRandomProvider;

	/**
	 * Sets the random algorithm to initialize the secure random id generator.
	 */
	@ServletContextInitParameter(PREFFIX + "RANDOM_KEY_IN_VIEW_STATE_SESSION_TOKEN_SECURE_RANDOM_ALGORITM")
	private String randomKeyInViewStateSessionTokenSecureRandomAlgorithm;

	/**
	 * Validate if the managed beans and navigations rules are correct.
	 */
	@ServletContextInitParameter(FacesConfigValidator.VALIDATE_CONTEXT_PARAM)
	private Boolean validate;

	@NestedProperty
	private Annotation annotation = new Annotation();

	/**
	 * If the flag is true, the algoritm skip jar scanning for faces-config
	 * files to check if the current application requires FacesServlet to be
	 * added dynamically (servlet spec 3).
	 */
	@ServletContextInitParameter(PREFFIX + "INITIALIZE_SKIP_JAR_FACES_CONFIG_SCAN")
	private Boolean initializeSkipJarFacesConfigScan;

	/**
	 * The Class of an Comparator<ELResolver/> implementation.
	 */
	@ServletContextInitParameter(ResolverBuilderBase.EL_RESOLVER_COMPARATOR)
	private Class<? extends Comparator<ELResolver>> elResolverComparator;

	/**
	 * The Class of an org.apache.commons.collections.Predicate<ELResolver/>
	 * implementation." + "If used and returns true for a ELResolver instance,
	 * such resolver will not be installed in " + "ELResolvers chain. Use with
	 * caution - can break functionality defined in JSF specification " +
	 * "'ELResolver Instances Provided by Faces'.
	 */
	@ServletContextInitParameter(ResolverBuilderBase.EL_RESOLVER_PREDICATE)
	private Class<? extends Predicate> elResolverPredicate;

	/**
	 * no description.
	 */
	@ServletContextInitParameter(ClientWindowFactoryImpl.INIT_PARAM_DEFAULT_WINDOW_MODE)
	private String defaultWindowMode;

	/**
	 * Indicate the template name used to render the default error page used by
	 * MyFaces specific error handler implementation.
	 */
	@ServletContextInitParameter(PREFFIX + "ERROR_TEMPLATE_RESOURCE")
	private String errorTemplateResource;

	/**
	 * Indicate the template name used to render the default debug page (see
	 * ui:debug tag).
	 */
	@ServletContextInitParameter(PREFFIX + "DEBUG_TEMPLATE_RESOURCE")
	private String debugTemplateResource;

	/**
	 * Indicate if myfaces is responsible to handle errors.
	 */
	@ServletContextInitParameter(ErrorPageWriter.ERROR_HANDLING_PARAMETER)
	private Boolean errorHandling;

	/**
	 * Define if the state caching code should be handled by the
	 * ResponseStateManager or by the StateManager used.
	 */
	@ServletContextInitParameter(HtmlResponseStateManager.INIT_PARAM_HANDLE_STATE_CACHING_MECHANICS)
	private Boolean handleStateCachingMechanics;

	/**
	 * Add autocomplete="off" to the view state hidden field.
	 */
	@ServletContextInitParameter(HtmlResponseStateManager.INIT_PARAM_AUTOCOMPLETE_OFF_VIEW_STATE)
	private Boolean autocompleteOffViewState;

	/**
	 * If this param is true and the project stage is development mode, the
	 * source javascript files will be loaded separately instead have all in
	 * just one file, to preserve line numbers and make javascript debugging of
	 * the default jsf javascript file more simple.
	 */
	@ServletContextInitParameter(InternalClassLoaderResourceLoader.USE_MULTIPLE_JS_FILES_FOR_JSF_UNCOMPRESSED_JS)
	private Boolean useMultipleJsFilesForJsfUncompressedJs;

	/**
	 * Define the mode used for jsf.
	 */
	@ServletContextInitParameter(InternalClassLoaderResourceLoader.MYFACES_JSF_MODE)
	private String jsfJsMode;

	/**
	 * If this param is set to true (default false), a temporal directory is
	 * created and all files handled by this ResourceLoader are cached there,
	 * avoiding the problem described on MYFACES-3586.
	 */
	@ServletContextInitParameter(PREFFIX + "TEMPORAL_RESOURCEHANDLER_CACHE_ENABLED")
	private Boolean temporalResourcehandlerCacheEnabled;

	/**
	 * Class name of a custom ServiceProviderFinder implementation.
	 */
	@ServletContextInitParameter(PREFFIX + "SERVICE_PROVIDER_FINDER")
	private Class<? extends ServiceProviderFinder> serviceProviderFinder;

	@NestedProperty
	private Spi spi = new Spi();

	/**
	 * If this param is set to true (by default), when pss algorithm is executed
	 * to save state, a visit tree traversal is done, instead a plain traversal
	 * like previous versions (2.
	 */
	@ServletContextInitParameter(DefaultFaceletsStateManagementStrategy.SAVE_STATE_WITH_VISIT_TREE_ON_PSS)
	private Boolean saveStateWithVisitTreeOnPass;

	/**
	 * Define how duplicate ids are checked when ProjectStage is Production, by
	 * default (auto) it only check ids of components that does not encapsulate
	 * markup (like facelets UILeaf).
	 */
	@ServletContextInitParameter(DefaultFaceletsStateManagementStrategy.CHECK_ID_PRODUCTION_MODE)
	private String checkIdProductionMode;

	/**
	 * no description.
	 */
	@ServletContextInitParameter(PREFFIX + "MARK_INITIAL_STATE_WHEN_APPLY_BUILD_VIEW")
	private Boolean markInitialStateWhenApplyBuildView;

	/**
	 * Wrap exception caused by calls to EL expressions, so information like the
	 * location, expression string and tag name can be retrieved by the
	 * ExceptionHandler implementation and used to output meaningful information
	 * about itself.
	 */
	@ServletContextInitParameter(ContextAwareUtils.INIT_PARAM_WRAP_TAG_EXCEPTIONS_AS_CONTEXT_AWARE)
	private Boolean wrapTagExceptionsAsContextAware;

	/**
	 * Indicates if expressions generated by facelets should be cached or not.
	 */
	@ServletContextInitParameter(FaceletCompositionContextImpl.INIT_PARAM_CACHE_EL_EXPRESSIONS)
	private Boolean cacheElExpressions;

	/**
	 * Defines the number of views to be hold per each view metadata definition.
	 */
	@ServletContextInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_POOL_SIZE)
	private Integer viewPoolMaxPoolSize;

	/**
	 * Defines the limit of the views that cannot be reused partially.
	 */
	@ServletContextInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_MAX_DYNAMIC_PARTIAL_LIMIT)
	private Integer viewPoolMaxDynamicPartialLimit;

	/**
	 * Defines the type of memory reference that is used to hold the view into
	 * memory.
	 */
	@ServletContextInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_ENTRY_MODE)
	private String viewPoolEntryMode;

	/**
	 * Defines if the view pool uses deferred navigation to recycle views when
	 * navigation is performed.
	 */
	@ServletContextInitParameter(ViewPool.INIT_PARAM_VIEW_POOL_DEFERRED_NAVIGATION)
	private Boolean viewPoolDeferredNavigation;

	@NestedProperty
	private Validator Validator = new Validator();

	/**
	 * This parameter specifies the ExpressionFactory implementation to use.
	 */
	@ServletContextInitParameter(PREFFIX + "EXPRESSION_FACTORY")
	private Class<? extends ExpressionFactory> expressionFactory;

	/**
	 * If this param is set to true, the check for faces servlet mapping is not
	 * done.
	 */
	@ServletContextInitParameter(PREFFIX + "INITIALIZE_ALWAYS_STANDALONE")
	private Boolean initializeAlwaysStandalone;

	/**
	 * Class name of a custom FacesInitializer implementation.
	 */
	@ServletContextInitParameter(PREFFIX + "FACES_INITIALIZER")
	private Class<? extends FacesInitializer> facesInitializer;

	/**
	 * comma delimited list of plugin classes which can be hooked into myfaces.
	 */
	@ServletContextInitParameter(value = PREFFIX + "FACES_INIT_PLUGINS", listSeparator = ",")
	private List<Class<?>> facesInitPlugins;

	/**
	 * Annotation class of useCdiForAnnotationScanning and scanPackages properties.
	 */
	@Getter
	@Setter
	public static class Annotation {

		/**
		 * Defines if CDI should be used for annotation scanning to improve the
		 * startup performance.
		 */
		@ServletContextInitParameter(CdiAnnotationProviderExtension.USE_CDI_FOR_ANNOTATION_SCANNING)
		private Boolean useCdiForAnnotationScanning;

		/**
		 * Servlet context init parameter which defines which packages to scan
		 * for beans, separated by commas.
		 */
		@ServletContextInitParameter(DefaultAnnotationProvider.SCAN_PACKAGES)
		private String scanPackages;
	}

	/**
	 * Spi class of injectionProvider property..
	 */
	@Getter
	@Setter
	public static class Spi {

		/**
		 * no description.
		 */
		@ServletContextInitParameter(PREFFIX + "spi.InjectionProvider")
		private String injectionProvider;
	}

	/**
	 * Validator class of beanBeforeJsfValidation property.
	 */
	@Getter
	@Setter
	public static class Validator {

		/**
		 * Enforce f:validateBean to be called first before any JSF validator.
		 */
		@ServletContextInitParameter(PREFFIX + "validator.BEAN_BEFORE_JSF_VALIDATION")
		private Boolean beanBeforeJsfValidation;
	}

}
