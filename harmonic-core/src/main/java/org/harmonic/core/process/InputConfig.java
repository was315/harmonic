package org.harmonic.core.process;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
@Data
public class InputConfig {

    public static final String RETURN_VALUE = "@return";

    private Object[] params;

    private Class preReturnType;

    private Map<String, Integer> markMap = new HashMap<>();

}
