package org.joinfaces.primefaces;

import com.sun.faces.config.FacesInitializer;
import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * To be processed after {@link FacesInitializer} runs, so we have {@link FacesServlet} at hand.
 *
 * @author nyilmaz
 */
@Order(Ordered.LOWEST_PRECEDENCE)
class PrimefacesFileUploadServletContextInitializer implements ServletContextInitializer {

   private static final String FACES_SERVLET_NAME = "FacesServlet";

   private final MultipartConfigElement multipartConfigElement;

   PrimefacesFileUploadServletContextInitializer(MultipartConfigElement multipartConfigElement) {
      this.multipartConfigElement = multipartConfigElement;
   }

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      ServletRegistration servletRegistration = servletContext.getServletRegistration(FACES_SERVLET_NAME);
      if(servletRegistration instanceof ServletRegistration.Dynamic) {
         ((ServletRegistration.Dynamic) servletRegistration).setMultipartConfig(this.multipartConfigElement);
      }
   }
}
