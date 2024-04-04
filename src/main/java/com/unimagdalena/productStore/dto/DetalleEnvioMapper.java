package com.unimagdalena.productStore.dto;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import com.unimagdalena.productStore.entity.DetalleEnvio;
import com.unimagdalena.productStore.entity.Pedido;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DetalleEnvioMapper {

    @Autowired
    protected Pedido pedido;

    @Mapping(target = "pedido", source = ".", qualifiedByName = "setIdPedido")
    public abstract DetalleEnvioDto DetalleEnvioToDto(DetalleEnvio detalleenvio);

    @Named("setIdPedido")
    private Long setIdPedido(DetalleEnvio detalleEnvio) {
        return detalleEnvio.getPedido().getId();
    }

    @Mapping(target = "id", source = ".", qualifiedByName = "getIdLong")
    @Mapping(target = "pedido", source = ".", qualifiedByName = "setPedido")
    public abstract DetalleEnvio DtoToDetalleEnvio(DetalleEnvioDto detalleEnvioDto, Pedido pedido);

    @Named("getIdLong")
    private Long getIdLong(DetalleEnvioDto detalleEnvioDto, Pedido pedido) {
        return detalleEnvioDto.id();
    }

    @Named("setPedido")
    private Pedido setPedido(DetalleEnvioDto detalleEnvioDto, Pedido pedido) {
        return pedido;
    }

    @Mapping(target = "pedido", source = ".", qualifiedByName = "setPedido")
    public abstract DetalleEnvio TsDtoToDetalleEnvio(DetalleEnvioTSDto detalleEnvioTSDto, Pedido pedido);

    @Named("setPedido2")
    private Pedido setPedido2(DetalleEnvioTSDto detalleEnvioTSDto, Pedido pedido) {
        return pedido;
    }

    @Mapping(target = "pedido", source = ".", qualifiedByName = "setIdPedido2")
    public abstract List<DetalleEnvioDto> DetalleEnviosToDtos(List<DetalleEnvio> detalleEnvios);

    @Named("setIdPedido2")
    private Long setIdPedido2(DetalleEnvio detalleEnvio) {
        return detalleEnvio.getPedido().getId();
    }

}
