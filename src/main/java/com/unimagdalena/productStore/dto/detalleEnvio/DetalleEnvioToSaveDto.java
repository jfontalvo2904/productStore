package com.unimagdalena.productStore.dto.detalleEnvio;

public record DetalleEnvioToSaveDto(Long pedido_id,
                                    String direccion,
                                    String transportadora,
                                    String numeroGuia) {}
