package com.unimagdalena.productStore.dto.pago;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;

public record PagoToUpdateDto(Long pedido_id, Double totalPago, MetodoDePago metodoDePago) {}
