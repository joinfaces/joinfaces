package com.github.persapiens.jsfboot.javaxfaces;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Taken from https://javaserverfaces.java.net/docs/2.2/javadocs/constant-values.html
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jsf")
public class JavaxFacesProperties {

	/**
	 * Set the project stage to "Development", "UnitTest", "SystemTest", or "Production".
	 */
	private String projectStage; 
    
    /**
     * A space separated list of resource extensions for types that shouldn't be served by the ResourceHandler implementation. See the specification for further details.
     */
    private String resourceExcludes;
    
    private String webappContractsDirectory;
    
    private String webappResourcesDirectory;

    
	/**
	 * Semicolon-separated list of view IDs that must save state using the JSF 1.2-style state saving.
	 */
	private String fullStateSavingViewIds; 

	/**
	 * If true, use the JSF2 partial state saving for views.
	 */
	private Boolean partialStateSaving; 

    /**
     * If this param is set, and calling toLowerCase().equals("true") on a String representation of its value returns true, and the javax.faces.STATE_SAVING_METHOD is set to "server" (as indicated below), the server state must be guaranteed to be Serializable such that the aggregate state implements java.io.Serializable. The intent of this parameter is to ensure that the act of writing out the state to an ObjectOutputStream would not throw a NotSerializableException, but the runtime is not required verify this before saving the state.
     */
    private Boolean serializeServerState;

	/**
	 * "server" or "client"
	 */
	private String stateSavingMethod;


	/**
	 * Change the default suffix for JSP views.
	 */
	private String defaultSuffix;

	/**
	 * ViewHandler. Useful for applications that use legacy Facelets implementation.
	 */
	private Boolean disableFaceletJsfViewhandler;

	/**
	 * The buffer size set on the response.
	 */
	private String faceletsBufferSize; 

	/**
	 * TagDecorator implementations. See javadoc for javax.faces.view .facelets.TagDecorator.
	 */
	private String faceletsDecorators; 

	/**
	 * Semicolon-separated list of paths to Facelet tag libraries.
	 */
	private String faceletsLibraries; 

	/**
	 * Time in seconds that facelets should be checked for changes since last request. A value of -1 disables refresh checking.
	 */
	private Long faceletsRefreshPeriod; 

	/**
	 * If true, strip XML comments out of Facelets before delivering to the client.
	 */
	private Boolean faceletsSkipComments; 

	/**
	 * Set the suffix for Facelet xhtml files.	
	 */
	private String faceletsSuffix; 

	/**
	 * Semicolon-separated list of Facelet files that don't use the default facelets suffix.
	 */
	private String faceletsViewMappings; 

    
    private Boolean honorCurrentComponentAttributes;

    
	/**
	 * If "true", validate null and empty values. If "auto" validate when JSR-303 Bean Validation is enabled (in AS6 it is enabled by default).
	 */
	private String validateEmptyFields; 

    
    private String separatorChar;
    
    
    private Partial partial = new Partial(); 
    
    @Getter @Setter    
    public static class Partial {
        private Boolean execute;
        private Boolean render;
        private Boolean resetValues;
    }
    

	/**
	 * Controls if DateTimeConverter instances use the system timezone (if true) or GMT (if false).
	 */
	private Boolean datetimeconverterDefaultTimezoneIsSystemTimezone;

    
	private Flow flow = new Flow(); 
    
    @Getter @Setter    
    public static class Flow {
        private Boolean nullFlow;
    }

        
	private Validator validator = new Validator(); 
    
    @Getter @Setter    
    public static class Validator {
        /**
         * If "true", disable JSR-303 Bean Validation.
         */
        private Boolean disableDefaultBeanValidator;
    }

    
	/**
	 * An implementation of javax.faces .view.facelets .ResourceResolver. See javadoc for details.
	 */
	private String faceletsResourceResolver; 


    
	/**
	 * Comma-delimited list of faces config files.
	 */
	private String configFiles;

	/**
	 * ID for alternate Lifecycle implementations.
	 */
	private String lifecycleId;
    
    private String clientWindowMode;

	/**
	 * If true, consider empty UIInput values to be null instead of empty string.
	 */
	private Boolean interpretEmptyStringSubmittedValuesAsNull; 
    public static final String EMPTY_STRING_AS_NULL =
          "javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL";
    
    
}
