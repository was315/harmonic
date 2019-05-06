package org.harmonic.core.exception;

/**
 * @author Shuai Wang
 * @date 2019/5/5
 */
public class HarmonicException extends Exception {

    private static final long serialVersionUID = 2490032266117754879L;

    public HarmonicException(String message) {
        super(message);
    }

    public HarmonicException(String message, Throwable cause) {
        super(message, cause);
    }

    public HarmonicException(Throwable cause) {
        super(cause);
    }


}
