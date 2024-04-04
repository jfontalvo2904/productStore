package com.unimagdalena.productStore.dto.detalleEnvio;

import com.unimagdalena.productStore.entity.DetalleEnvio;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DetalleEnvioMapper {

    @Mapping(target = "pedido", expression = "java(null)")
    DetalleEnvio detalleEnvioToSaveToDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    @Mapping(target = "pedido_id", expression = "java(obtenerPedidoId(detalleEnvio))")
    DetalleEnvioDto detalleEnvioToDto(DetalleEnvio detalleEnvio);

    @IterableMapping(elementTargetType = DetalleEnvioDto.class)
    List<DetalleEnvioDto> detallesEnvioToDto(List<DetalleEnvio> detallesEnvio);

    default Long obtenerPedidoId(DetalleEnvio detalleEnvio) {
        return detalleEnvio.getPedido().getId();
    }
}
