package org.harmonic.container.spring;

import org.harmonic.container.ContainerManager;
import org.harmonic.core.module.Module;
import org.harmonic.core.process.Processor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Shuai Wang
 * @date 2019/5/22
 */
public class SpringContainer implements ContainerManager, ApplicationContextAware {

    @Override
    public Module getModule(String name) {
        return null;
    }

    @Override
    public Module getModule(Class<?> cls) {
        return null;
    }

    @Override
    public Processor getProcessor(String name) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
