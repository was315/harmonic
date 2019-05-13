package org.harmonic.core.process;

import org.harmonic.core.exception.ProcessorException;
import org.harmonic.core.module.Module;

import java.util.Map;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
public class SerialProcessor extends AbstractProcessor {


    public SerialProcessor(Module module, InputConfig inputConfig) {
        super(module, inputConfig);
    }

    @Override
    public Processor invoke() {
        if (!isReady()) {
            throw new ProcessorException(getProcessorName() + " can't be invoked. It is not ready!");
        }

        try {
            Module module = getModule();
            InputConfig inputConfig = getInputConfig();
            Object[] params = inputConfig.getParams().clone();
            Map<String, Integer> markMap = inputConfig.getMarkMap();
            Integer index = markMap.get(InputConfig.RETURN_VALUE);
            if (index != null && index >= 0) {
                params[index] = getPrevious().getOutputValue();
            }

            Object result = module.getEntryMethod().invoke(module.getInstance(), params);
            setOutputValue(result);
            isSuccess.set(true);
            if (getNext() != null) {
                return getNext().invoke();
            }
        } catch (Exception e) {
            setError(e);
        } finally {
            isInvoked.set(true);
        }

        return this;
    }



    @Override
    public void setIfNextLegal(Processor processor) {
        if (!isReady()) {
            throw new ProcessorException(getProcessorName() + " is not ready!");
        }

        processor.getInputConfig().setPreReturnType(getModule().getOutputType());
        if (!processor.isReady()) {
            throw new ProcessorException( processor.getProcessorName() + " is not ready!");
        }
        setNext(processor);
        processor.setPrevious(this);
    }
}
