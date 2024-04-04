package com.unimagdalena.productStore.services.itemProducto;

import java.util.List;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.exceptions.PedidoNotFoundException;

public interface ItemProductoService {

    List<ItemPedidoDto> getAll();

    ItemPedidoDto guardar(ItemPedidoToSaveDto data);

    ItemPedidoDto actualizar(Long id, ItemPedidoDto data);

    ItemPedidoDto buscarPorId(Long id) throws PedidoNotFoundException;

    void eliminarPorId(Long id);
}
