package org.joinfaces.primefaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializerBeans;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static junit.framework.TestCase.assertTrue;

/**
 * @author nyilmaz
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrimefacesSpringBootAutoConfiguration.class)
public class PrimefacesFileUploadFilterIT {

   @Autowired
   WebApplicationContext wac;

   @Test
   public void testFileUploadFilterRegistration() {
      ServletContextInitializerBeans beans =
          new ServletContextInitializerBeans((ListableBeanFactory) wac.getAutowireCapableBeanFactory());
      boolean filterBeanFound = false;
      for (ServletContextInitializer bean : beans) {
         if(bean instanceof FilterRegistrationBean
            && ((FilterRegistrationBean) bean).getFilter() instanceof FileUploadFilter) {
            filterBeanFound = true;
         }
      }
      assertTrue(filterBeanFound);
   }

}
