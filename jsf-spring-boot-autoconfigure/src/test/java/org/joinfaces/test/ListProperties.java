package org.joinfaces.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lars Grefer
 */
@ConfigurationProperties("joinfaces.test")
@Getter
@Setter
public class ListProperties {

	private List<String> listA = new ArrayList<String>();
	private List<String> listB = new ArrayList<String>();
	private List<String> listC = new ArrayList<String>();
	private List<String> listD = new ArrayList<String>();
}
