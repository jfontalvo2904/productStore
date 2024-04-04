package com.unimagdalena.productStore.dto.pago;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;

public record PagoToSaveDto(Long pedio_id, double totalPago, MetodoDePago metodoDePago) {}
