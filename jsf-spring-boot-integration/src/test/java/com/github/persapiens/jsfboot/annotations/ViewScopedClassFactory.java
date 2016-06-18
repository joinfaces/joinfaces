package com.github.persapiens.jsfboot.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

public class ViewScopedClassFactory implements ObjectFactory<ViewScopedClass> {

    @Override
    public ViewScopedClass getObject() throws BeansException {
        return new ViewScopedClass();
    }

}
