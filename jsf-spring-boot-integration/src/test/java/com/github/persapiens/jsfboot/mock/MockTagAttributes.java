package com.github.persapiens.jsfboot.mock;

import java.util.HashMap;
import java.util.Map;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributes;
import lombok.Getter;

/**
 * Tag Attributes Mock
 */
public class MockTagAttributes extends TagAttributes {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    @Getter
    private Map<String, TagAttribute> tagAttributes = new HashMap<>();

    @Override
    public TagAttribute[] getAll() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public TagAttribute get(String localName) {
        return tagAttributes.get(localName);
    }

    @Override
    public TagAttribute get(String ns, String localName) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public TagAttribute[] getAll(String namespace) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public String[] getNamespaces() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }
}
