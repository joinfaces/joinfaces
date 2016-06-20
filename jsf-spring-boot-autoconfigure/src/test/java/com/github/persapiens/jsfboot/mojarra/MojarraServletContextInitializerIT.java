package com.github.persapiens.jsfboot.mojarra;

import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import static com.github.persapiens.jsfboot.mojarra.MojarraServletContextInitializer.ANOTHER_FACES_CONFIG;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.omnifaces.component.input.Form;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

@SpringApplicationConfiguration(classes = MojarraSpringBootAutoConfiguration.class)
@WebAppConfiguration
@Test
public class MojarraServletContextInitializerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private MojarraProperties mojarraProperties;

    private Set<Class<?>> classes;
    
    @BeforeSuite
    public void setupClasses() {
        MojarraServletContextInitializer configuration = new MojarraServletContextInitializer(null);
        
        classes = JsfAnnotatedClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
    }
    
	public void testMojarraSelectItemsIndexConverter() {        
		assertThat(classes).contains(SelectItemsIndexConverter.class);
	}
    
	public void testMojarraRequiredCheckboxValidator() {        
		assertThat(classes).contains(RequiredCheckboxValidator.class);
	}
    
	public void testMojarraFormComponent() {        
		assertThat(classes).contains(Form.class);
	}

    public void testAnotherFacesConfig() throws ServletException
    {
        MojarraServletContextInitializer mojarraServletContextInitializer 
            = new MojarraServletContextInitializer(mojarraProperties);

        assertThat(mojarraServletContextInitializer.getAnotherFacesConfig()).isEqualTo(ANOTHER_FACES_CONFIG);
	}

    public void testExcludeScopedAnnotations() throws ServletException
    {
        MojarraServletContextInitializer mojarraServletContextInitializer 
            = new MojarraServletContextInitializer(mojarraProperties);

        assertThat(mojarraServletContextInitializer.isExcludeScopedAnnotations()).isTrue();
	}
    
    public void testOnStartup() throws ServletException
    {
        MojarraServletContextInitializer mojarraServletContextInitializer =
            new MojarraServletContextInitializer(mojarraProperties);
        
        ServletContext servletContext = new MojarraMockServletContext();
        
        mojarraServletContextInitializer.onStartup(servletContext);
        
        assertThat(servletContext.getInitParameter(MojarraServletContextConfigurer.PREFFIX + ".clientStateTimeout"))
            .isEqualTo("10");
	}
    
}
