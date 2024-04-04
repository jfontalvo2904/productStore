package com.unimagdalena.productStore.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.unimagdalena.productStore.entity.Producto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    ProductoDto ProductoToDto(Producto producto);

    Producto DtoToProducto(ProductoDto productoDto);

    Producto TsDtoToProducto(ProductoTSDto producto);
}
