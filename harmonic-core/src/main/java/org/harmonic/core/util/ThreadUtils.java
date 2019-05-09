package org.harmonic.core.util;

/**
 * @author Shuai Wang
 * @date 2019/5/10
 */
public class ThreadUtils {

    public static <T> ThreadLocal<T> newThreadLocal() {
        return new ThreadLocal<>();
    }

    public static <T> ThreadLocal<T> newThreadLocal(T initVal) {
        return ThreadLocal.withInitial(() -> initVal);
    }
}
