package com.unimagdalena.productStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.ClienteDto;
import com.unimagdalena.productStore.dto.ProductoDto;
import com.unimagdalena.productStore.dto.ProductoTSDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.repository.ClienteRepository;
import com.unimagdalena.productStore.repository.ProductoRepository;

@Service
public class ProductoServicesImpl {

    @Autowired
    private ProductoRepository productoRepository;
    private final ProductoMapperImpl productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapperImpl productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    public ProductoDto guardar(ProductoTSDto data) {

        Producto producto = this.productoMapper.productoToSDtoToMensaje(data);
        Producto productoSaved = this.productoRepository.save(producto);
        return this.productoMapper.mensajeToMensajeDto(productoSaved);
    }

    @Override
    public List<ProductoDto> getAll() {
        List<Producto> data = this.productoRepository.findAll();
        return this.productoMapper.productosToProductosDto(data);
    }
}
