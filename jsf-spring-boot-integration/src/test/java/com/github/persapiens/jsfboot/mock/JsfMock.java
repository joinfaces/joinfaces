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
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.Tag;
import javax.faces.view.facelets.TagConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.Getter;
 
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
 
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
    private ServletContext mockServletContext;
    private TagConfig mockTagConfig;
    private MockFaceletHandler mockFaceletHandler;
    private Tag mockTag;
    private MockTagAttributes mockTagAttributes;
    private FaceletContext mockFaceletContext;
 
    public void release() {
        mockFacesContext.release();
    }
 
    public void init(ApplicationContext applicationContext) {
        mockFacesContext = FacesContextMocker.mockFacesContext();
        mockApplication = Mockito.mock(Application.class);
        mockViewRoot = Mockito.mock(UIViewRoot.class);
        mockExternalContext = Mockito.mock(ExternalContext.class);
        mockHttpServletRequest = Mockito.mock(HttpServletRequest.class);
        mockHttpServletResponse = Mockito.mock(HttpServletResponse.class);
        mockHttpSession = Mockito.mock(HttpSession.class);
        mockTagConfig = Mockito.mock(TagConfig.class);
        mockFaceletHandler = new MockFaceletHandler();
        mockTagAttributes = new MockTagAttributes();
        mockTag = new Tag(null, null, null, null, mockTagAttributes);
        mockFaceletContext = Mockito.mock(FaceletContext.class);
        
        mockViewMap = new HashMap<>();
        mockServletContext = new MockServletContext();
        if (applicationContext != null)
        {
            mockServletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
        }
        
        Mockito.when(mockTagConfig.getNextHandler()).thenReturn(mockFaceletHandler);
        Mockito.when(mockTagConfig.getTag()).thenReturn(mockTag);

        Mockito.when(mockFaceletContext.getFacesContext()).thenReturn(mockFacesContext);
        
        Mockito.when(mockFacesContext.getApplication()).thenReturn(mockApplication);
        Mockito.when(mockApplication.getSupportedLocales()).thenReturn(createLocales().iterator());
 
        Mockito.when(mockFacesContext.getViewRoot()).thenReturn(mockViewRoot);
        Mockito.when(mockViewRoot.getLocale()).thenReturn(new Locale("en"));
        Mockito.when(mockViewRoot.getViewMap()).thenReturn(mockViewMap);
 
        Mockito.when(mockFacesContext.getExternalContext()).thenReturn(mockExternalContext);
        Mockito.when(mockExternalContext.getRequest()).thenReturn(mockHttpServletRequest);
        Mockito.when(mockHttpServletRequest.getSession()).thenReturn(mockHttpSession);
        Mockito.when(mockExternalContext.getResponse()).thenReturn(mockHttpServletResponse);
        Mockito.when(mockExternalContext.getContext()).thenReturn(mockServletContext);
 
        Map<String, String> requestMap = new HashMap<>();
        Mockito.when(mockExternalContext.getRequestParameterMap()).thenReturn(requestMap);        
    }

    private List<Locale> createLocales() {
        ArrayList<Locale> locales = new ArrayList<>();
        locales.add(new Locale("en"));
        locales.add(new Locale("de"));
        locales.add(new Locale("pt_BR"));
        return locales;
    }
}    