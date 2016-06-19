package com.github.persapiens.jsfboot.annotations;

import com.github.persapiens.jsfboot.mock.JsfIT;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

@SpringApplicationConfiguration(classes = AnnotationConfiguration.class)
@Test
@WebAppConfiguration
public class ViewScopeIT extends JsfIT {

    private static final String KEY = "key";
    
    public void testViewScope() {
        ViewScope viewScope = new ViewScope();
        
        ViewScopedClassFactory viewScopedClassFactory = new ViewScopedClassFactory();
        
        Object viewScopedClass = viewScope.get(KEY, viewScopedClassFactory);
        viewScopedClass = viewScope.get(KEY, viewScopedClassFactory);
        
        assertThat(viewScope.remove(KEY)).isEqualTo(viewScopedClass);
    }
    
    public void testConversationId() {
        ViewScope viewScope = new ViewScope();
        assertThat(viewScope.getConversationId()).isNull();
    }
    
    public void testResolveContextualObject() {
        ViewScope viewScope = new ViewScope();
        assertThat(viewScope.resolveContextualObject(KEY)).isNull();
    }
    
    public void testRegisterDestructionCallback() {
        ViewScope viewScope = new ViewScope();
        viewScope.registerDestructionCallback(KEY, null);
    }
}
