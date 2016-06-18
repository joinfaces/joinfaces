package com.github.persapiens.jsfboot.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.Getter;
 
import org.mockito.Mockito;
 
/**
 * Taken from http://ovaraksin.blogspot.com.br/2014/03/set-up-jsf-environment-for-junit-tests.html
 */
@Getter
public class JsfMock {
 
    private FacesContext mockFacesContext;
    private UIViewRoot mockViewRoot;
    private Application mockApplication;
    private ExternalContext mockExternalContext;
    private HttpSession mockHttpSession;
    private HttpServletRequest mockHttpServletRequest;
    private HttpServletResponse mockHttpServletResponse;
    private Map<String, Object> mockViewMap;
 
    public void release() {
        mockFacesContext.release();
    }
 
    public void init() {
        mockFacesContext = FacesContextMocker.mockFacesContext();
        mockApplication = Mockito.mock(Application.class);
        mockViewRoot = Mockito.mock(UIViewRoot.class);
        mockExternalContext = Mockito.mock(ExternalContext.class);
        mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
        mockHttpServletResponse = Mockito.mock(HttpServletResponse.class);
        mockHttpSession = Mockito.mock(HttpSession.class);
 
        mockViewMap = new HashMap<>();
        
        Mockito.when(mockFacesContext.getApplication()).thenReturn(mockApplication);
        Mockito.when(mockApplication.getSupportedLocales()).thenReturn(createLocales().iterator());
 
        Mockito.when(mockFacesContext.getViewRoot()).thenReturn(mockViewRoot);
        Mockito.when(mockViewRoot.getLocale()).thenReturn(new Locale("en"));
        Mockito.when(mockViewRoot.getViewMap()).thenReturn(mockViewMap);
 
        Mockito.when(mockFacesContext.getExternalContext()).thenReturn(mockExternalContext);
        Mockito.when(mockExternalContext.getRequest()).thenReturn(mockHttpServletRequest);
        Mockito.when(mockHttpServletRequest.getSession()).thenReturn(mockHttpSession);
 
        Map<String, String> requestMap = new HashMap<String, String>();
        Mockito.when(mockExternalContext.getRequestParameterMap()).thenReturn(requestMap);        
    }

    private List<Locale> createLocales() {
        ArrayList<Locale> locales = new ArrayList<>();
        locales.add(new Locale("en"));
        locales.add(new Locale("de"));
        return locales;
    }
}    