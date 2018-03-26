/*
 * Copyright 2016-2016 the original author or authors.
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
package org.joinfaces.autoconfigure.rewrite;

import java.util.EnumSet;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletContextListener;
import org.ocpsoft.rewrite.servlet.impl.RewriteServletRequestListener;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto Configuration of Rewrite.
 *
 * @author Marcelo Fernandes
 */
@Configuration
@EnableConfigurationProperties({RewriteFilterProperties.class, RewriteProperties.class})
@ConditionalOnClass(RewriteFilter.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class RewriteAutoConfiguration {

    @Inject
    private RewriteFilterProperties rewriteFilterProperties;
    
    @Bean
    public RewriteServletRequestListener rewriteServletRequestListener() {
        return new RewriteServletRequestListener();
    }

    @Bean
    public RewriteServletContextListener rewriteServletContextListener() {
        return new RewriteServletContextListener();
    }
    
    @Bean
    public FilterRegistrationBean rewriteFilterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RewriteFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));        
        registration.addUrlPatterns(rewriteFilterProperties.getUrlPatterns().toArray(new String[rewriteFilterProperties.getUrlPatterns().size()]));
        return registration;
    }
}
