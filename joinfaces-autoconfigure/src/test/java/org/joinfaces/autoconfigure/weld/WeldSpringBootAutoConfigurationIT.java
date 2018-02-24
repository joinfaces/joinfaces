package org.joinfaces.autoconfigure.weld;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeldSpringBootAutoConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class WeldSpringBootAutoConfigurationIT {
    
    @Autowired
    private WeldServletContextInitializer weldServletContextInitializer;
    
    @Test
    public void testWeldServletContextInitializer() {
        assertThat(weldServletContextInitializer).isNotNull();
    }
    
}
