package com.unimagdalena.productStore.dto;

public record DetalleEnvioTSDto(Long id,
        Long pedido,
        String direccion,
        String transportadora,
        String numeroGuia) {
}
