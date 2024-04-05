package com.unimagdalena.productStore.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException() {
        super();
    }

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ClienteNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
