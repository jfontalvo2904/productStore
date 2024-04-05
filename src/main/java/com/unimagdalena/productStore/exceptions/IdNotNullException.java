package com.unimagdalena.productStore.exceptions;

public class IdNotNullException extends RuntimeException{
    public IdNotNullException() {
        super("El id no puede ser null");
    }

    public IdNotNullException(String message) {
        super(message);
    }

    public IdNotNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdNotNullException(Throwable cause) {
        super(cause);
    }

    protected IdNotNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
