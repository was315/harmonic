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

    void setProcessorName(String name);

    String getProcessorName();

    boolean invoke();

    void setModule(Module module);

    Module getModule();

    boolean isReady();

    void setInputConfig(InputConfig inputConfig);

    InputConfig getInputConfig();

    <T> T getOutputValue();

    void setPrevious(Processor processor);

    void setIfNextLegal(Processor processor);

}
