package com.unimagdalena.productStore.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import com.unimagdalena.productStore.entity.Pedido;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidoMapper {

    PedidoDto PedidoToDto(Pedido pedido);

    Pedido DtoToPedido(PedidoDto pedidoDto);

    @Mapping(target = "fechaPedido", expression = "java(java.time.LocalDateTime.now())")
    Pedido TsDtoToPedido(PedidoTSDto pedido);

    List<PedidoDto> PedidosToDtos(List<Pedido> pedidos);
}
