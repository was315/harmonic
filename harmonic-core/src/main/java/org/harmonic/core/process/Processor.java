package org.harmonic.core.process;

import org.harmonic.core.module.Module;


/**
 * Processor is the basic unit of process, which can be used to orchestrate your
 * service through config. A module can be injected, and also, can be invoked by
 * processor. The interface implementation should not only support serial execution,
 * the parallel execution and hybrid execution should also be supported as well.
 *
 * @author Shuai Wang
 * @date 2019/5/5
 */
public interface Processor {

    boolean invoke();

    void setModule(Module module);

    void setInputConfig(InputConfig inputConfig);

    <T> T getOutputValue();

    boolean setIfNextLegal(Processor processor);

}
