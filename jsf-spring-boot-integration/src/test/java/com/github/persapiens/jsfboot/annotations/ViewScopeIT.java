package com.github.persapiens.jsfboot.annotations;

import com.github.persapiens.jsfboot.mock.JsfMock;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class ViewScopeIT {

    private JsfMock jsfMock;

    private static final String KEY = "key";
    
    @BeforeClass
    public void init() {
        jsfMock = new JsfMock();
        jsfMock.init();
    }

    @AfterClass
    public void release() {
        jsfMock.release();        
    }
    
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
