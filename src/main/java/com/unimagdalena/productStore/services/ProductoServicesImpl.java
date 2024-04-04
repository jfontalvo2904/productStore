package com.unimagdalena.productStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.ClienteDto;
import com.unimagdalena.productStore.dto.ProductoDto;
import com.unimagdalena.productStore.dto.ProductoMapper;
import com.unimagdalena.productStore.dto.ProductoMapperImpl;
import com.unimagdalena.productStore.dto.ProductoTSDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.exceptions.ProductoNotFoundException;
import com.unimagdalena.productStore.repository.ClienteRepository;
import com.unimagdalena.productStore.repository.ProductoRepository;

@Service
public class ProductoServicesImpl implements ProductoServices {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapperImpl productoMapper;

    @Override
    public ProductoDto guardar(ProductoTSDto data) {

        Producto producto = this.productoMapper.TsDtoToProducto(data);
        Producto productoSaved = this.productoRepository.save(producto);
        return this.productoMapper.ProductoToDto(productoSaved);
    }

    @Override
    public List<ProductoDto> getAll() {
        List<Producto> data = this.productoRepository.findAll();
        return this.productoMapper.ProductosToDtos(data);
    }

    @Override
    public List<ProductoDto> getAllByNombre(String nombre) {
        List<Producto> data = this.productoRepository.findByNombre(nombre);
        return this.productoMapper.ProductosToDtos(data);
    }

    @Override
    public List<ProductoDto> getAllInStock() {
        List<Producto> data = this.productoRepository.buscarProductosEnStock();
        return this.productoMapper.ProductosToDtos(data);
    }

    @Override
    public List<ProductoDto> getAllByStock(Integer stock) {
        List<Producto> data = this.productoRepository.buscarProductosPorStock(stock);
        return this.productoMapper.ProductosToDtos(data);
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
