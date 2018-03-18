package org.joinfaces.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = JoinfacesTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class JoinfacesTestApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloFromSpring() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/index.xhtml", String.class);

        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();

        assertThat(entity.getBody()).contains("Hello from Spring:");
    }
}