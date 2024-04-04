package com.unimagdalena.productStore.dto;

public record DetalleEnvioTSDto(
        Long pedido,
        String direccion,
        String transportadora,
        String numeroGuia) {
}
