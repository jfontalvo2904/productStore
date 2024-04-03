package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.PagoDto;
import com.unimagdalena.productStore.exceptions.PagoNotFoundException;

public interface PagoServices {

    List<PagoDto> getAll();

    PagoDto guardar(PagoDto data);

    PagoDto actualizar(Long id, PagoDto data);

    PagoDto buscarPorId(Long id) throws PagoNotFoundException;

    void eliminarPorId(Long id);
}
