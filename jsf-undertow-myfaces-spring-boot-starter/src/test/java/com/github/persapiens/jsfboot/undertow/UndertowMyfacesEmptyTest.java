package com.github.persapiens.jsfboot.undertow;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class UndertowMyfacesEmptyTest {
    
    public void nothing() {
        assertThat(new UndertowMyfacesEmpty()).isNotNull();
    }    
}
