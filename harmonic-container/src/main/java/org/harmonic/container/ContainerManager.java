package org.harmonic.container;

import org.harmonic.core.module.Module;
import org.harmonic.core.process.Processor;

/**
 * @author Shuai Wang
 * @date 2019/5/21
 */
public interface ContainerManager {

    Module getModule(String name);

    Module getModule(Class<?> cls);

    Processor getProcessor(String name);

}
