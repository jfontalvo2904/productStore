package com.unimagdalena.productStore.services.detalleEnvio;

import java.util.List;

import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioDto;
import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioToSaveDto;
import com.unimagdalena.productStore.exceptions.DetalleEnvioNotFoundException;

public interface DetalleEnvioServices {

    List<DetalleEnvioDto> getAll();

    DetalleEnvioDto guardar(DetalleEnvioToSaveDto data);

    DetalleEnvioDto actualizar(Long id, DetalleEnvioDto data);

    DetalleEnvioDto buscarPorId(Long id) throws DetalleEnvioNotFoundException;

    void eliminarPorId(Long id);

}