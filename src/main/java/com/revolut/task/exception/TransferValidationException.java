package com.revolut.task.exception;

public class TransferValidationException extends Exception {

    public TransferValidationException() {
    }

    public TransferValidationException(String message) {
        super(message);
    }

    public TransferValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferValidationException(Throwable cause) {
        super(cause);
    }

    public TransferValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
