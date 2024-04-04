package com.unimagdalena.productStore.services.producto;

import java.util.List;

import com.unimagdalena.productStore.dto.producto.ProductoDto;
import com.unimagdalena.productStore.dto.producto.ProductoToSaveDto;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.exceptions.ProductoNotFoundException;

public interface ProductoServices {

    List<ProductoDto> getAll();

    ProductoDto guardar(ProductoToSaveDto data);

    ProductoDto actualizar(Long id, ProductoDto data);

    ProductoDto buscarPorId(Long id) throws ProductoNotFoundException;

    void eliminarPorId(Long id);
}
