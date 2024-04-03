package com.unimagdalena.productStore.dto;

public record DetalleEnvioDto(Long id,
                Long pedido,
                String direccion,
                String transportadora,
                String numeroGuia) {
}
