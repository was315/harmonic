package org.harmonic.core.exception;

/**
 * @author Shuai Wang
 * @date 2019/5/5
 */
public class ProcessorException extends RuntimeException {

    private static final long serialVersionUID = 2490032266117754879L;

    public ProcessorException(String message) {
        super(message);
    }

    public ProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessorException(Throwable cause) {
        super(cause);
    }


}
