package com.unimagdalena.productStore.exceptions.cliente;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException() {
        super("El cliente no ha sido encontrado");
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

    protected ClienteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
