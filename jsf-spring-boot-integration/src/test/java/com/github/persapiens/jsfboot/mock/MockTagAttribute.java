package com.github.persapiens.jsfboot.mock;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.view.Location;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

/**
 * Tag Attribute Mock
 */
public class MockTagAttribute extends TagAttribute {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";
    
    private String value;
    
    public MockTagAttribute() {
    }

    public MockTagAttribute(String value) {
        this.value = value;
    }
    
    @Override
    public boolean getBoolean(FaceletContext ctx) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public int getInt(FaceletContext ctx) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String getLocalName() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public MethodExpression getMethodExpression(FaceletContext ctx, Class type, Class[] paramTypes) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String getNamespace() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Object getObject(FaceletContext ctx) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String getQName() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getValue(FaceletContext ctx) {
        return value;
    }

    @Override
    public Object getObject(FaceletContext ctx, Class type) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public ValueExpression getValueExpression(FaceletContext ctx, Class type) {
        return new MockValueExpression(value);
    }

    @Override
    public boolean isLiteral() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
