package com.github.persapiens.jsfboot.mock;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletHandler;
import lombok.Getter;

/**
 * Facelet Handler Mock
 */
public class MockFaceletHandler implements FaceletHandler {

    @Getter
    private boolean applied = false;
    
    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        applied = true;
    }
}
