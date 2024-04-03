package com.unimagdalena.productStore.dto;

import java.time.LocalDateTime;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;

public record PagoDto(Long id,
        Long pedido,
        double totalPago,
        LocalDateTime fechaPago,
        MetodoDePago metodoDePago) {
}