package com.unimagdalena.productStore.services.producto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.producto.ProductoDto;
import com.unimagdalena.productStore.dto.producto.ProductoMapperImpl;
import com.unimagdalena.productStore.dto.producto.ProductoToSaveDto;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.exceptions.producto.ProductoNotFoundException;
import com.unimagdalena.productStore.repository.ProductoRepository;

@Service
public class ProductoServicesImpl implements ProductoServices {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapperImpl productoMapper;

    @Override
    public ProductoDto guardar(ProductoToSaveDto data) {

        Producto producto = this.productoMapper.productoToSaveToProducto(data);
        Producto productoSaved = this.productoRepository.save(producto);
        return this.productoMapper.productoToProductoDto(productoSaved);
    }

    @Override
    public List<ProductoDto> getAll() {
        List<Producto> data = this.productoRepository.findAll();
        return this.productoMapper.productosToProductosDto(data);
    }

    @Override
    public List<ProductoDto> getAllByNombre(String nombre) {
        List<Producto> data = this.productoRepository.findByNombre(nombre);
        return this.productoMapper.productosToProductosDto(data);
    }

    @Override
    public List<ProductoDto> getAllInStock() {
        List<Producto> data = this.productoRepository.buscarProductosEnStock();
        return this.productoMapper.productosToProductosDto(data);
    }

    @Override
    public List<ProductoDto> getAllByStock(Integer stock) {
        List<Producto> data = this.productoRepository.buscarProductosPorStock(stock);
        return this.productoMapper.productosToProductosDto(data);
    }

    @Override
    public ProductoDto actualizar(Long id, ProductoDto data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public ProductoDto buscarPorId(Long id) throws ProductoNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void eliminarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPorId'");
    }
}
