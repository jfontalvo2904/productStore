package com.unimagdalena.productStore.services.itemPedido;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.ItemPedido;
import com.unimagdalena.productStore.exception.cliente.ClienteNotFoundException;
import com.unimagdalena.productStore.exceptions.ItemPedidoNotFoundException;

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
