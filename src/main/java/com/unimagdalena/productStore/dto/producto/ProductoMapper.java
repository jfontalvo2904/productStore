package com.unimagdalena.productStore.dto.producto;

import com.unimagdalena.productStore.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    Producto productoToSaveToProducto(ProductoToSaveDto producto);

    ProductoDto productoToProductoDto(Producto producto);

    List<ProductoDto> productosToProductosDto(List<Producto> productos);
}
