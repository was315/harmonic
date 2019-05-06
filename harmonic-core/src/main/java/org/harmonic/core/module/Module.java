package org.harmonic.core.module;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Shuai Wang
 * @date 2019/5/6
 */
public interface Module {

    List<Type> getInputTypes();

    Type getOutputType();

}
