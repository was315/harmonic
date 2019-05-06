package org.harmonic.core.process;

import org.harmonic.core.module.Module;

import java.lang.reflect.Type;

/**
 * @author Shuai Wang
 * @date 2019/5/5
 */
public interface Processor {

    boolean invoke();

    void setModule(Module module);

    void setInputValues(Object... inputValues);

    <T> T getOutputValue();

    Processor getNext();

    boolean setIfNextLegal(Processor processor);

}
