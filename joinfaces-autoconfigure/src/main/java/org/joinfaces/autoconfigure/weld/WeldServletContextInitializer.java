package org.joinfaces.autoconfigure.weld;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;

public class WeldServletContextInitializer implements ServletContextInitializer{
    
    private EnhancedListener enhancedListener = null;

    private EnhancedListener getEnhancedListener() {
        if(enhancedListener == null){
            enhancedListener = new EnhancedListener();
        }
        return enhancedListener;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        getEnhancedListener().onStartup(null, servletContext);
    }
}
