package com.github.persapiens.jsfboot.mock;

import javax.faces.context.FacesContext;
 
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
 
/**
 * Taken from http://ovaraksin.blogspot.com.br/2014/03/set-up-jsf-environment-for-junit-tests.html
 */
public abstract class FacesContextMocker extends FacesContext {
 
    private FacesContextMocker() {
    }
 
    private static final Release RELEASE = new Release();
 
    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
    }
 
    public static FacesContext mockFacesContext() {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        Mockito.doAnswer(RELEASE).when(context).release();
        return context;
    }
}