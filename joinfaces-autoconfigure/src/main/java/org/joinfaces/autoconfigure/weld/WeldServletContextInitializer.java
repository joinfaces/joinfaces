/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.weld;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.jboss.weld.environment.servlet.EnhancedListener;
import org.springframework.boot.web.servlet.ServletContextInitializer;

/**
 * The goal of this class is to start the weld listener in order to enable the 
 * scan of all the cdi annotations
 *
 * @author classicPintus
 */

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
