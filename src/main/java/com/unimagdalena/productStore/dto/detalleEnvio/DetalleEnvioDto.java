package com.unimagdalena.productStore.dto.detalleEnvio;

public record DetalleEnvioDto(Long id,
                              Long pedido_id,
                              String direccion,
                              String transportadora,
                              String numeroGuia) {
}
