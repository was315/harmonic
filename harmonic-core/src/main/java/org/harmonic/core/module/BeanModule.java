package org.harmonic.core.module;

import lombok.Data;
import org.harmonic.core.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author Shuai Wang
 * @date 2019/5/8
 */
@Data
public class BeanModule<T> implements Module<T> {

    private T instance;

    private Method entryMethod;

    private String beanName;

    @Override
    public String getModuleName() {
        return StringUtils.isNotEmpty(beanName) ? beanName : "Module[" + instance.getClass().getName() + "]";
    }

    @Override
    public Class[] getInputTypes() {
        return entryMethod.getParameterTypes();
    }

    @Override
    public Class getOutputType() {
        return entryMethod.getReturnType();
    }
}
