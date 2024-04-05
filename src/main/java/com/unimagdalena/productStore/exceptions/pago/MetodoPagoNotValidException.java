package com.unimagdalena.productStore.exceptions.pago;

public class MetodoPagoNotValidException extends RuntimeException{
    public MetodoPagoNotValidException() {
        super("El metodo de pago no es valido");
    }

    public MetodoPagoNotValidException(String message) {
        super(message);
    }

    public MetodoPagoNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetodoPagoNotValidException(Throwable cause) {
        super(cause);
    }

    protected MetodoPagoNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
