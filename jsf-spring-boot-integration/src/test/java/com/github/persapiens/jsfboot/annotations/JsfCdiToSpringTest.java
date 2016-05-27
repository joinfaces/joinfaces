package com.github.persapiens.jsfboot.annotations;

import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Test
public class JsfCdiToSpringTest {

	public void testCdiRequestScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.enterprise.context.RequestScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.REQUEST);
	}

	public void testJsfRequestScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.bean.RequestScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.REQUEST);
	}

	public void testCdiSessionScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.enterprise.context.SessionScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.SESSION);
	}

	public void testJsfSessionScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.bean.SessionScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.SESSION);
	}

	public void testCdiApplicationScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.enterprise.context.ApplicationScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.SINGLETON);
	}

	public void testJsfApplicationScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.bean.ApplicationScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.SINGLETON);
	}

	public void testJsfNoneScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.bean.NoneScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.PROTOTYPE);
	}

	public void testJsfViewViewScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.view.ViewScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.VIEW);
	}

	public void testJsfBeanViewScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.faces.bean.ViewScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.VIEW);
	}

	public void testCdiConversationScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(javax.enterprise.context.ConversationScoped.class.getName());

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isEqualTo(JsfCdiToSpring.SESSION);
	}

	public void testUnknownScopedAnnotation() {
		Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add("unkownScopedAnnotation");

		assertThat(JsfCdiToSpring.scopeName(annotationTypes))
            .isNull();
	}

}
