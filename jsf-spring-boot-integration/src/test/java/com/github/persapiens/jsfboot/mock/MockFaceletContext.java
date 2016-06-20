package com.github.persapiens.jsfboot.mock;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import lombok.Getter;

/**
 * Facelet Context Mock
 */
public class MockFaceletContext extends FaceletContext {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    private FacesContext facesContext;

    public MockFaceletContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }    
    
    @Getter
    private Map<String, Object> attributes = new HashMap<>();

    @Override
    public FacesContext getFacesContext() {
        return facesContext;
    }

    @Override
    public String generateUniqueId(String base) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public ExpressionFactory getExpressionFactory() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public void setVariableMapper(VariableMapper varMapper) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public void setFunctionMapper(FunctionMapper fnMapper) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void includeFacelet(UIComponent parent, String relativePath) throws IOException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public void includeFacelet(UIComponent parent, URL absolutePath) throws IOException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public ELResolver getELResolver() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public FunctionMapper getFunctionMapper() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public VariableMapper getVariableMapper() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
