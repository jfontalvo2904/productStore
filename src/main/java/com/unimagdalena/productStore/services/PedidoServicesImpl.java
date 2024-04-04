package com.unimagdalena.productStore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.ClienteDto;
import com.unimagdalena.productStore.dto.PedidoDto;
import com.unimagdalena.productStore.dto.PedidoTSDto;
import com.unimagdalena.productStore.dto.ProductoDto;
import com.unimagdalena.productStore.dto.ProductoMapper;
import com.unimagdalena.productStore.dto.ProductoTSDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.exceptions.PedidoNotFoundException;
import com.unimagdalena.productStore.exceptions.ProductoNotFoundException;
import com.unimagdalena.productStore.repository.ClienteRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;
import com.unimagdalena.productStore.repository.ProductoRepository;

@Service
public class PedidoServicesImpl implements PedidoServices {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<PedidoDto> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public PedidoDto guardar(PedidoTSDto data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public PedidoDto actualizar(Long id, PedidoDto data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public PedidoDto buscarPorId(Long id) throws PedidoNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void eliminarPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPorId'");
    }

}
