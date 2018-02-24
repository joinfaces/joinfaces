package org.joinfaces.autoconfigure.weld;

import javax.servlet.ServletException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class WeldServletContextInitializerIT {

    @Test
    public void testOnStartup() throws ServletException {
        WeldServletContextInitializer weldServletContextInitializer = new WeldServletContextInitializer();
        weldServletContextInitializer.onStartup(new MockServletContext());
        Object field = ReflectionTestUtils.getField(weldServletContextInitializer, "enhancedListener");
        assertThat(field).isNotNull();
        weldServletContextInitializer.onStartup(new MockServletContext());
        Object field2 = ReflectionTestUtils.getField(weldServletContextInitializer, "enhancedListener");
        assertThat(field).isEqualTo(field2);
    }

}
