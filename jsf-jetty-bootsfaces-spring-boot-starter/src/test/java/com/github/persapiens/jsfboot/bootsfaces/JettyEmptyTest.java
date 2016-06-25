package com.github.persapiens.jsfboot.bootsfaces;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class JettyEmptyTest {
    
    public void nothing() {
        assertThat(new JettyEmpty()).isNotNull();
    }
}
