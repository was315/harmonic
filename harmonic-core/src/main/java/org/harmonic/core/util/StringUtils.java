package org.harmonic.core.util;

/**
 * @author Shuai Wang
 * @date 2019/5/4
 */
public class StringUtils {

    /**
     * check if given {@code String} is Empty.
     *
     * @param str the argument
     * @return always return {@code true} for a non-null and non-String Argument.
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

}
