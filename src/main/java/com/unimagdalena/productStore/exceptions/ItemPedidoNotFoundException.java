package com.unimagdalena.productStore.exceptions;

public class ItemPedidoNotFoundException extends RuntimeException {
    public ItemPedidoNotFoundException() {
        super( "El item de pedido no ha sido encontrado");
    }

    public ItemPedidoNotFoundException(String message) {
        super(message);
    }

    public ItemPedidoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemPedidoNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ItemPedidoNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
