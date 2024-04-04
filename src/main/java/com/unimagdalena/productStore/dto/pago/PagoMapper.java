package com.unimagdalena.productStore.dto.pago;

import com.unimagdalena.productStore.entity.Pago;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PagoMapper {

    @Mapping(target = "pedido", expression = "java(null)")
    @Mapping(target = "fechaPago", expression = "java(java.time.LocalDateTime.now())")
    Pago pagoToSaveToPago(PagoToSaveDto pago);


    @Mapping(target = "pedido", expression = "java(null)")
    @Mapping(target = "fechaPago", expression = "java(java.time.LocalDateTime.now())")
    Pago pagoToUpdateToPago(PagoToUpdateDto pago);

    @Mapping(target = "pedido_id", expression = "java(obtenerPedidoId(pago))")
    PagoDto pagoToDTO(Pago pago);

    @IterableMapping(elementTargetType = PagoDto.class)
    List<PagoDto> pagosToDTO(List<Pago> pagos);



    default Long obtenerPedidoId(Pago pago) {
        return pago.getPedido().getId();
    }
}
