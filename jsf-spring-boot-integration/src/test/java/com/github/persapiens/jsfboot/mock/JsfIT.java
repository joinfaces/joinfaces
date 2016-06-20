package com.github.persapiens.jsfboot.mock;

import javax.inject.Inject;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Abstract integration test for jsf
 * Activate JsfMock for each test execution
 */
public class JsfIT extends AbstractTestNGSpringContextTests {

    @Getter
    private JsfMock jsfMock;
    
    @Inject
    private ApplicationContext applicationContext;
    
    @BeforeMethod
    public void init() {
        jsfMock = new JsfMock();
        jsfMock.init(applicationContext);
    }

    @AfterMethod
    public void release() {
        jsfMock.release();        
    }

}
