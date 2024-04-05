package com.unimagdalena.productStore.exceptions.pago;

public class PagoNotFoundException extends  RuntimeException{
    public PagoNotFoundException() {
        super("El pago no fue encontrado");
    }

    public PagoNotFoundException(String message) {
        super(message);
    }

    public PagoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PagoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected PagoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
