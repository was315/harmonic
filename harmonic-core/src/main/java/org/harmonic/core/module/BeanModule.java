package org.harmonic.core.module;

import lombok.Data;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shuai Wang
 * @date 2019/5/8
 */
@Data
public class BeanModule<T> implements Module<T> {

    private T instance;

    private Method entryMethod;

    @Override
    public List<Class> getInputTypes() {
        return Arrays.asList(entryMethod.getParameterTypes());
    }

    @Override
    public Class getOutputType() {
        return entryMethod.getReturnType();
    }
}
