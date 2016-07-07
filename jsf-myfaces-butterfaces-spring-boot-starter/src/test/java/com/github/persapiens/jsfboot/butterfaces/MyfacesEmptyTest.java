package com.github.persapiens.jsfboot.butterfaces;

import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.Test;

@Test
public class MyfacesEmptyTest {

    public void nothing() {
        assertThat(new MyfacesEmpty()).isNotNull();
    }
}
