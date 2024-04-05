package com.unimagdalena.productStore.services.pago;

import java.util.List;
import java.time.LocalDateTime;

import com.unimagdalena.productStore.dto.pago.PagoDto;
import com.unimagdalena.productStore.dto.pago.PagoToSaveDto;
import com.unimagdalena.productStore.dto.pago.PagoToUpdateDto;
import com.unimagdalena.productStore.exception.pago.PagoNotFoundException;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;

public interface PagoService {

    List<PagoDto> obtenerTodos();
    PagoDto crearPago(PagoToSaveDto pagoToSaveDto);
    PagoDto actualizarPago(Long id, PagoToUpdateDto pagoToUpdateDto);
    PagoDto buscarPagoPorId(Long id) throws PagoNotFoundException;
    void eliminarPago(Long id) throws PagoNotFoundException;
    List<PagoDto> recuperarPagosEnRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<PagoDto> recuperarPagosPorPedidoYMetodoDePago(Long pedidoId, MetodoDePago metodoDePago);
}
