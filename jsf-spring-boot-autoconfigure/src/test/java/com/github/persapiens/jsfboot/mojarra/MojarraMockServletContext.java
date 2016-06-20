package com.github.persapiens.jsfboot.mojarra;

import java.util.EventListener;
import javax.servlet.ServletRegistration;
import org.mockito.Mockito;
import org.springframework.mock.web.MockServletContext;

public class MojarraMockServletContext extends MockServletContext {

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return Mockito.mock(ServletRegistration.Dynamic.class);
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
    }
    
}
