package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.ProductoDto;
import com.unimagdalena.productStore.dto.ProductoTSDto;
import com.unimagdalena.productStore.exceptions.ProductoNotFoundException;

public interface ProductoServices {

    List<ProductoDto> getAll();

    ProductoDto guardar(ProductoTSDto data);

    ProductoDto actualizar(Long id, ProductoDto data);

    ProductoDto buscarPorId(Long id) throws ProductoNotFoundException;

    void eliminarPorId(Long id);
}
