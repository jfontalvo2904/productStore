package com.unimagdalena.productStore.services.pedido;

import java.util.List;

import com.unimagdalena.productStore.dto.pedido.PedidoDto;
import com.unimagdalena.productStore.dto.pedido.PedidoToSaveDto;
import com.unimagdalena.productStore.exceptions.pedido.PedidoNotFoundException;

public interface PedidoServices {

    List<PedidoDto> getAll();

    PedidoDto guardar(PedidoToSaveDto data) throws PedidoNotFoundException;

    PedidoDto actualizar(Long id, PedidoDto data);

    PedidoDto buscarPorId(Long id) throws PedidoNotFoundException;

    void eliminarPorId(Long id);
}
