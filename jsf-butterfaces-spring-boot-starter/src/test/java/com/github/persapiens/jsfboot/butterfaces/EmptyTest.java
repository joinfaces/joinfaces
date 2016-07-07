package com.github.persapiens.jsfboot.butterfaces;

import com.github.persapiens.jsfboot.butterfaces.Empty;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class EmptyTest {
    
    public void nothing() {
        assertThat(new Empty()).isNotNull();
    }
}
