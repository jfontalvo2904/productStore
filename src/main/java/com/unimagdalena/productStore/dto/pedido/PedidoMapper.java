package com.unimagdalena.productStore.dto.pedido;

import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PedidoMapper {

     @Mapping(target = "cliente", expression = "java(null)")
     @Mapping(target = "fechaPedido", expression = "java(java.time.LocalDateTime.now())")
     @Mapping(target = "status", expression = "java(statusPendiente())")
     @Mapping(target = "detalleEnvio", expression = "java(null)")
     @Mapping(target = "pago", expression = "java(null)")
     @Mapping(target = "itemsPedido", expression = "java(java.util.Collections.emptyList())")
     Pedido peditoToSaveToPedido(PedidoToSaveDto pedido);

     @Mapping(target = "cliente_id", expression = "java(obtenerClienteId(pedido))")
     @Mapping(target = "detalleEnvio_id", expression = "java(obtenerDetalleEnvioId(pedido))")
     @Mapping(target = "pago_id",expression = "java(obtenerPagoId(pedido))")
     PedidoDto pedidoToDTO(Pedido pedido);

     @IterableMapping(elementTargetType = PedidoDto.class)
     List<PedidoDto> pedidosToDTO(List<Pedido> pedidos);

     default PedidoStatus statusPendiente(){
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
