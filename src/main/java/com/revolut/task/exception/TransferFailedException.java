package com.revolut.task.exception;

public class TransferFailedException extends Exception{
    public TransferFailedException() {
    }

    public TransferFailedException(String message) {
        super(message);
    }

    public TransferFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransferFailedException(Throwable cause) {
        super(cause);
    }

    public TransferFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
