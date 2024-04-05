package com.unimagdalena.productStore.dto.pedido;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoMapper;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = ItemPedidoMapper.class)
public interface PedidoMapper {

    @Mapping(target = "cliente", expression = "java(null)")
    @Mapping(target = "fechaPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(statusPendiente())")
    @Mapping(target = "itemsPedido", expression = "java(java.util.Collections.emptyList())")
    Pedido peditoToSaveToPedido(PedidoToSaveDto pedido);

    @Mapping(target = "cliente_id", expression = "java(obtenerClienteId(pedido))")
    @Mapping(target = "detalle_envio_id", expression = "java(obtenerDetalleEnvioId(pedido))")
    @Mapping(target = "pago_id", expression = "java(obtenerPagoId(pedido))")
    PedidoDto pedidoToDTO(Pedido pedido);

    @IterableMapping(elementTargetType = PedidoDto.class)
    List<PedidoDto> pedidosToDTO(List<Pedido> pedidos);

    default PedidoStatus statusPendiente() {
        return PedidoStatus.PENDIENTE;
    }

    default Long obtenerClienteId(Pedido pedido) {
        return pedido.getCliente().getId();
    }

    default Long obtenerDetalleEnvioId(Pedido pedido) {
        if(pedido.getDetalleEnvio() == null) return null;

        return pedido.getDetalleEnvio().getId();
    }

    default Long obtenerPagoId(Pedido pedido) {
        if(pedido.getPago() == null) return null;
        return pedido.getPago().getId();
    }
  
}
