package org.harmonic.core.process;

import org.harmonic.core.exception.ProcessorException;
import org.harmonic.core.module.Module;
import org.harmonic.core.util.ThreadUtils;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
public abstract class AbstractProcessor implements Processor {

    private Module instance;

    private Processor next;

    private InputConfig inputConfig;

    private ThreadLocal<Object[]> inputValues = ThreadUtils.newThreadLocal();

    private ThreadLocal outputValue = ThreadUtils.newThreadLocal();

    private ThreadLocal<Boolean> isInvoked = ThreadUtils.newThreadLocal(false);

    private ThreadLocal<Boolean> isSuccess = ThreadUtils.newThreadLocal(false);


    @Override
    public void setModule(Module module) {
        this.instance = module;
    }

    private Module getModule() {
        return this.instance;
    }

    @Override
    public void setInputConfig(InputConfig inputConfig) {
        this.inputConfig = inputConfig;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getOutputValue() {
        Object o = outputValue.get();
        if (o == null) {
            return null;
        }

        if (isInvoked.get() && isSuccess.get())  {
            return (T) o;
        }

        throw new ProcessorException(isInvoked.get() ? "Didn't invoke module" : "Module invoke fail");
    }

}
