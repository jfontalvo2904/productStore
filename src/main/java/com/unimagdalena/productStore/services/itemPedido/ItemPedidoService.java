package com.unimagdalena.productStore.services.itemPedido;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.entity.ItemPedido;
import com.unimagdalena.productStore.exceptions.itemPedido.ItemPedidoNotFoundException;

import java.util.List;

public interface ItemPedidoService {
    List<ItemPedidoDto> obtenerTodos();
    ItemPedidoDto crearItemPedido(ItemPedidoToSaveDto itemPedidoToSaveDto);
    ItemPedidoDto actualizarItemPedido(Long id, ItemPedidoToUpdateDto itemPedidoToUpdateDto) throws ItemPedidoNotFoundException;
    ItemPedidoDto buscarItemPedidoPorId(Long id) throws ItemPedidoNotFoundException;
    ItemPedido buscarItemPedido(Long id) throws ItemPedidoNotFoundException;
    List<ItemPedidoDto> findByPedidoId(Long pedidoId);
    List<ItemPedidoDto> findByProductoId(Long productoId);
    Float calcularTotalVentasPorProducto( Long productoId);
    void eliminarItemPedido(Long id) throws ItemPedidoNotFoundException;

}
