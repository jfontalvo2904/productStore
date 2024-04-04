package com.unimagdalena.productStore.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.unimagdalena.productStore.entity.Producto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    ProductoDto ProductoToDto(Producto producto);

    Producto DtoToProducto(ProductoDto productoDto);

    @Mapping(target = "itemsPedido", expression = "java(java.util.Collections.emptyList())")
    Producto TsDtoToProducto(ProductoTSDto producto);

    List<ProductoDto> ProductosToDtos(List<Producto> productos);
}
