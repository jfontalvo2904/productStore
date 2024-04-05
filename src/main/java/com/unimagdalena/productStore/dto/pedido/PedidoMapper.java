package com.unimagdalena.productStore.dto.pedido;

import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PedidoMapper {

    @Mapping(target = "cliente", expression = "java(null)")
    @Mapping(target = "fechaPedido", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(statusPendiente())")
    @Mapping(target = "itemsPedido", expression = "java(java.util.Collections.emptyList())")
    Pedido peditoToSaveToPedido(PedidoToSaveDto pedido);

    @Mapping(target = "cliente_id", expression = "java(obtenerClienteId(pedido))")
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
        return pedido.getDetalleEnvio().getId();
    }

    default Long obtenerPagoId(Pedido pedido) {
        return pedido.getPago().getId();
    }

}
