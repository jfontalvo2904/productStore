package com.unimagdalena.productStore.exceptions.itemPedido;

public class ItemPedidoCantidadInvalidadException extends RuntimeException{
    public ItemPedidoCantidadInvalidadException() {
        super("La cantidad debe ser un valor válido");
    }

    public ItemPedidoCantidadInvalidadException(String message) {
        super(message);
    }

    public ItemPedidoCantidadInvalidadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemPedidoCantidadInvalidadException(Throwable cause) {
        super(cause);
    }

    protected ItemPedidoCantidadInvalidadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
