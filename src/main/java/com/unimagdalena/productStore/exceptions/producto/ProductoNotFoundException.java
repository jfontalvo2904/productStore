package com.unimagdalena.productStore.exceptions.producto;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException() {
        super();
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }

    public ProductoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProductoNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
