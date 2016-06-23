package com.github.persapiens.jsfboot;

import com.sun.faces.facelets.compiler.UIText;
import java.util.Set;
import javax.faces.component.html.HtmlPanelGroup;
import org.apache.myfaces.renderkit.html.HtmlGridRenderer;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@Test
public class JsfClassFactoryIT  {
    
	public void testJavaxFacesHtmlPanelGroupWithMojarra() {        
        MojarraServletContextInitializer configuration = new MojarraServletContextInitializer();
        
        Set<Class<?>> classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	public void testMojarraUITextWithMojarra() {        
        MojarraServletContextInitializer configuration = new MojarraServletContextInitializer();
        
        Set<Class<?>> classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
		assertThat(classes).contains(UIText.class);
	}
    
	public void testJavaxFacesHtmlPanelGroupWithMyfaces() {        
        MyfacesServletContextInitializer configuration = new MyfacesServletContextInitializer();
        
        Set<Class<?>> classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	public void testMyfacesHtmlGridRendererWithMyfaces() {        
        MyfacesServletContextInitializer configuration = new MyfacesServletContextInitializer();
        
        Set<Class<?>> classes = JsfClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
		assertThat(classes).contains(HtmlGridRenderer.class);
	}
    
}
