package org.joinfaces.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ListPropertiesAutoConfiguration.class)
public class ListPropertiesTest {

	@Autowired
	private ListProperties listProperties;

	@Test
	public void testPropertiesListAProperties() {
		assertThat(listProperties.getListA()).containsExactly("foo", "bar");
	}

	@Test
	public void testPropertiesListBProperties() {
		assertThat(listProperties.getListB()).containsExactly("foo", "bar");
	}

	@Test
	public void testYamlListCProperties() {
		assertThat(listProperties.getListC()).containsExactly("foo", "bar");
	}

	@Test
	public void testYamlListDProperties() {
		assertThat(listProperties.getListD()).containsExactly("foo", "bar");
	}
}
