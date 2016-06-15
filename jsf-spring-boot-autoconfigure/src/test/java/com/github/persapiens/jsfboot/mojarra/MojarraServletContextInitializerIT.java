package com.github.persapiens.jsfboot.mojarra;

import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import java.util.Set;
import org.testng.annotations.Test;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.testng.annotations.BeforeSuite;
import static org.assertj.core.api.Assertions.assertThat;
import org.omnifaces.component.input.Form;
import org.omnifaces.validator.RequiredCheckboxValidator;

@Test
public class MojarraServletContextInitializerIT {

    private Set<Class<?>> classes;
    
    @BeforeSuite
    public void setupClasses() {
        MojarraServletContextInitializer configuration = new MojarraServletContextInitializer(null);
        
        classes = JsfAnnotatedClassFactory.builder()
            .jsfAnnotatedClassFactoryConfiguration(configuration)
            .build().find();        
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

}
