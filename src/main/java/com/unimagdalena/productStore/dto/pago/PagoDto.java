package com.unimagdalena.productStore.dto.pago;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;

import java.time.LocalDateTime;

public record PagoDto(Long id,
                      Long pedido_id,
                      Double totalPago,
                      LocalDateTime fechaPago,
                      MetodoDePago metodoDePago) {
}
