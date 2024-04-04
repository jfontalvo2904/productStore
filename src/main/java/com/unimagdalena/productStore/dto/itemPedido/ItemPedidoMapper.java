package com.unimagdalena.productStore.dto.itemPedido;

import com.unimagdalena.productStore.entity.ItemPedido;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ItemPedidoMapper {

    @Mapping(target = "pedido", expression = "java(null)")
    @Mapping(target = "producto", expression = "java(null)")
    @Mapping(target = "precioUnitario",expression = "java(null)")
    ItemPedido itemPedidoToSaveToItemPedido(ItemPedidoToSaveDto itemPedidoToSaveDto);

    @Mapping(target = "pedido", expression = "java(null)")
    @Mapping(target = "producto", expression = "java(null)")
    @Mapping(target = "precioUnitario",expression = "java(null)")
    ItemPedido itemPedidoToUpdateToItemPedido(ItemPedidoToUpdateDto itemPedidoToUpdateDto);


    //el dto para actualizar ahora mismo es igual pero en el futuro podría cambiar, por eso lo dejo así
    @Mapping(target = "pedido_id", expression = "java(obtenerPedidoId(itemPedido))")
    @Mapping(target = "producto_id", expression = "java(obtenerProductoId(itemPedido))")
    ItemPedidoDto itemPedidoToDto( ItemPedido itemPedido);

    @IterableMapping(elementTargetType = ItemPedidoDto.class)
    List<ItemPedidoDto> itemsPedidoToDto(List<ItemPedido> itemPedidos);

    default Long obtenerPedidoId(ItemPedido itemPedido) {
        // Suponiendo que tu clase ItemPedido tiene un método getId() para obtener el ID del pedido
        return itemPedido.getPedido().getId();
    }
    default Long obtenerProductoId(ItemPedido itemPedido) {
        // Suponiendo que tu clase ItemPedido tiene un método getId() para obtener el ID del pedido
        return itemPedido.getProducto().getId();
    }
}
