package com.unimagdalena.productStore.exceptions;

public class DetalleEnvioNotFoundException extends RuntimeException {
    public DetalleEnvioNotFoundException() {
        super();
    }

    public DetalleEnvioNotFoundException(String message) {
        super(message);
    }

    public DetalleEnvioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DetalleEnvioNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DetalleEnvioNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
