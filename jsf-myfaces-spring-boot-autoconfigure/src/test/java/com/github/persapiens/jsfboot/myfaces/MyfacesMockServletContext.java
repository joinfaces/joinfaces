package com.github.persapiens.jsfboot.myfaces;

import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;
import org.mockito.Mockito;
import org.springframework.mock.web.MockServletContext;

public class MyfacesMockServletContext extends MockServletContext {

	@Override
	public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
		return Mockito.mock(ServletRegistration.Dynamic.class);
	}
    
}
