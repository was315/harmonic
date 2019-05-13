package org.harmonic.core.process;

import org.harmonic.core.exception.ProcessorException;
import org.harmonic.core.module.Module;
import org.harmonic.core.util.StringUtils;
import org.harmonic.core.util.ThreadUtils;

import java.util.Map;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
public abstract class AbstractProcessor implements Processor {

    private Module module;

    private String processorName;

    private Processor previous;

    private Processor next;

    private InputConfig inputConfig;

    private ThreadLocal<Object> outputValue = ThreadUtils.newThreadLocal();

    protected ThreadLocal<Boolean> isInvoked = ThreadUtils.newThreadLocal(false);

    protected ThreadLocal<Boolean> isSuccess = ThreadUtils.newThreadLocal(false);

    private ThreadLocal<Boolean> isReady = ThreadUtils.newThreadLocal(false);


    AbstractProcessor(Module module, InputConfig inputConfig) {
        setModule(module);
        setInputConfig(inputConfig);
    }

    @Override
    public void setProcessorName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new ProcessorException("Processor name can't set empty.");
        }
        this.processorName = name;
    }

    @Override
    public String getProcessorName() {
        return StringUtils.isNotEmpty(processorName) ? processorName : "Processor{" + this.module.getModuleName() + "}";
    }

    @Override
    public void setModule(Module module) {
        if (module == null) {
            throw new ProcessorException("Module can't be null.");
        }
        this.module = module;
    }

    @Override
    public Module getModule() {
        return this.module;
    }

    @Override
    public void setPrevious(Processor previous) {
        if (previous == null) {
            throw new ProcessorException("Previous processor can't be null.");
        }
        this.previous = previous;
    }

    Processor getPrevious() {
        return previous;
    }

    @Override
    public boolean isReady() {
        if (isReady.get()) {
            return true;
        }

        if (this.module != null && this.inputConfig != null) {
            checkInputTypes();
            isReady.set(true);
            return true;
        }

        return false;
    }

    void setNext(Processor next) {
        if (next == null) {
            throw new ProcessorException("Next processor can't be null.");
        }
        this.next = next;
    }

    Processor getNext() {
        return next;
    }

    @Override
    public void setInputConfig(InputConfig inputConfig) {
        if (inputConfig == null) {
            throw new ProcessorException("InputConfig can't be null.");
        }

        this.inputConfig = inputConfig;
    }

    @Override
    public InputConfig getInputConfig() {
        return this.inputConfig;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getOutputValue() {
        if (isInvoked.get() && isSuccess.get())  {
            Object o = outputValue.get();
            if (o == null) {
                return null;
            }
            return (T) o;
        }

        throw new ProcessorException(isInvoked.get() ? "Didn't invoke module!" : "Module invoke fail!");
    }

    void setOutputValue(Object outputValue) {
        if (!this.module.getOutputType().isInstance(outputValue)) {
            throw new ProcessorException("Output value type is wrong!");
        }

        this.outputValue.set(outputValue);
    }

    @SuppressWarnings("unchecked")
    private void checkInputTypes() {
        Class[] inputTypes = this.module.getInputTypes();
        Object[] inputValues = this.inputConfig.getParams();
        Class preReturnType = this.inputConfig.getPreReturnType();
        Map<String, Integer> markMap = this.inputConfig.getMarkMap();

        if (inputTypes == null) {
            throw new ProcessorException("InputTypes of " + this.module.getModuleName() + " can't be null.");
        }

        if (inputValues == null) {
            throw new ProcessorException("Parameters of InputConfig in " + getProcessorName() + " can't be null.");
        }

        if (inputTypes.length != inputValues.length) {
            throw new ProcessorException("InputConfig illegal in + " + getProcessorName() + ". Parameter number is wrong.");
        }

        for (int i = 0; i < inputTypes.length; i++) {
            if (InputConfig.RETURN_VALUE.equals(inputValues[i])) {
                if (preReturnType == null || preReturnType.isInstance(void.class)) {
                    throw new ProcessorException("@Return can't be applied!");
                } else if (inputTypes[i].isAssignableFrom(preReturnType)) {
                    markMap.put(InputConfig.RETURN_VALUE, i);
                } else {
                    throw new ProcessorException(doNotMatchTypeMsg(preReturnType, inputTypes[i]));
                }
            }
            else if (!inputTypes[i].isInstance(inputValues[i])) {
                throw new ProcessorException(doNotMatchTypeMsg(inputValues[i].getClass(), inputTypes[i]));
            }

        }
    }


    private String doNotMatchTypeMsg(Class argType, Class requiredType) {
        return String.format("Illegal argument type of %s in %s. Required is: %s.",
                argType.getTypeName(), getProcessorName(), requiredType.getTypeName());
    }
}
