package com.github.persapiens.jsfboot.myfaces;

import com.github.persapiens.jsfboot.JsfClassFactory;
import static com.github.persapiens.jsfboot.myfaces.MyfacesServletContextInitializer.ANOTHER_FACES_CONFIG;
import java.util.Set;
import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import net.bootsfaces.component.tree.TreeRenderer;
import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import static org.assertj.core.api.Assertions.assertThat;
import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeSuite;

@SpringApplicationConfiguration(classes = MyfacesSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MyfacesServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MyfacesProperties myfacesProperties;

    private Set<Class<?>> classes;
    
    @BeforeSuite
    public void setupClasses() {
        MyfacesServletContextInitializer configuration = new MyfacesServletContextInitializer(null);
        
        classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();
	}
    
	public void testJavaxFacesHtmlPanelGroup() {        
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	public void testMyacesHtmlGridRenderer() {        
		assertThat(classes).contains(HtmlGridRenderer.class);
	}
    
	public void testOmnifacesSelectItemsIndexConverter() {        
		assertThat(classes).contains(SelectItemsIndexConverter.class);
	}
    
	public void testOmnifacesRequiredCheckboxValidator() {        
		assertThat(classes).contains(RequiredCheckboxValidator.class);
	}
    
	public void testOmnifacesFormComponent() {        
		assertThat(classes).contains(Form.class);
	}
    
	public void testBootsfacesTreeRenderer() {        
		assertThat(classes).contains(TreeRenderer.class);
	}

    public void testAnotherFacesConfig() throws ServletException
    {
        MyfacesServletContextInitializer myfacesServletContextInitializer 
            = new MyfacesServletContextInitializer(myfacesProperties);

        assertThat(myfacesServletContextInitializer.getAnotherFacesConfig()).isEqualTo(ANOTHER_FACES_CONFIG);
	}

    public void testExcludeScopedAnnotations() throws ServletException
    {
        MyfacesServletContextInitializer myfacesServletContextInitializer 
            = new MyfacesServletContextInitializer(myfacesProperties);

        assertThat(myfacesServletContextInitializer.isExcludeScopedAnnotations()).isTrue();
	}
    
    public void testOnStartup() throws ServletException
    {
        MyfacesServletContextInitializer myfacesServletContextInitializer =
            new MyfacesServletContextInitializer(myfacesProperties);
        
        ServletContext servletContext = new MyfacesMockServletContext();
        
        myfacesServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(MyfacesServletContextConfigurer.PREFFIX + "STRICT_JSF_2_CC_EL_RESOLVER"))
            .isEqualTo("myElResolver");
	}

}
