package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.PedidoDto;
import com.unimagdalena.productStore.dto.PedidoTSDto;
import com.unimagdalena.productStore.exceptions.PedidoNotFoundException;

public interface PedidoServices {

    List<PedidoDto> getAll();

    PedidoDto guardar(PedidoTSDto data);

    PedidoDto actualizar(Long id, PedidoDto data);

    PedidoDto buscarPorId(Long id) throws PedidoNotFoundException;

    void eliminarPorId(Long id);
}
