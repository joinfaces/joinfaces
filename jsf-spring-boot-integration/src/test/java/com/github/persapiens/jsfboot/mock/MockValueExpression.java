package com.github.persapiens.jsfboot.mock;

import javax.el.ELContext;
import javax.el.ValueExpression;

/**
 * Value Expression Mock
 */
public class MockValueExpression extends ValueExpression {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    public MockValueExpression(String value) {
        this.value = value;
    }
    
    private String value;
    
    @Override
    public Object getValue(ELContext elc) {
        return value;
    }

    @Override
    public void setValue(ELContext elc, Object o) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public boolean isReadOnly(ELContext elc) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Class<?> getType(ELContext elc) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public Class<?> getExpectedType() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String getExpressionString() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof MockValueExpression) {
            result = value.equals(((MockValueExpression) o).value);
        }
        return result;
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public boolean isLiteralText() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
