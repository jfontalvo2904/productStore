package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.DetalleEnvioDto;
import com.unimagdalena.productStore.exceptions.DetalleEnvioNotFoundException;

public interface DetalleEnvioServices {

    List<DetalleEnvioDto> getAll();

    DetalleEnvioDto guardar(DetalleEnvioDto data);

    DetalleEnvioDto actualizar(Long id, DetalleEnvioDto data);

    DetalleEnvioDto buscarPorId(Long id) throws DetalleEnvioNotFoundException;

    void eliminarPorId(Long id);

}