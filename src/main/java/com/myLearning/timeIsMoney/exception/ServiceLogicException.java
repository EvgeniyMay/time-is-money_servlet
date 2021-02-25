package com.mylearning.timeismoney.exception;

public class ServiceLogicException extends Exception {

    public ServiceLogicException() {
        super();
    }

    public ServiceLogicException(String message) {
        super(message);
    }

    public ServiceLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLogicException(Throwable cause) {
        super(cause);
    }
}
