package com.unimagdalena.productStore.exceptions.pago;

public class TotalPagoNotValidException extends RuntimeException{
    public TotalPagoNotValidException() {
        super("El valor a pagar no es valido");
    }

    public TotalPagoNotValidException(String message) {
        super(message);
    }

    public TotalPagoNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public TotalPagoNotValidException(Throwable cause) {
        super(cause);
    }

    protected TotalPagoNotValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
