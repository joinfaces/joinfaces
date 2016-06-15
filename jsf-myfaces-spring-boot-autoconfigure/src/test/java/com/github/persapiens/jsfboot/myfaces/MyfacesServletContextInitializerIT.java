package com.github.persapiens.jsfboot.myfaces;

import com.github.persapiens.jsfboot.JsfAnnotatedClassFactory;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.omnifaces.component.input.Form;
import org.testng.annotations.Test;
import org.omnifaces.converter.SelectItemsIndexConverter;
import org.omnifaces.validator.RequiredCheckboxValidator;
import org.testng.annotations.BeforeSuite;

@Test
public class MyfacesServletContextInitializerIT {

    private Set<Class<?>> classes;
    
    @BeforeSuite
    public void setupClasses() {
        MyfacesServletContextInitializer configuration = new MyfacesServletContextInitializer(null);
        
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
