package com.unimagdalena.productStore.services.itemProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoMapperImpl;
import com.unimagdalena.productStore.repository.ItemPedidoRepository;

public class ItemProductoServiceImpl {
    @Autowired
    private ItemPedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoMapperImpl pedidoMapper;

    @Override
    public List<ItemPedidoDto> getAll() {
        List<ItemPedidoDto> data = this.pedidoRepository.findAll();
        return this.pedidoMapper.pedidosToDTO(data);
    }

    @Override
    public PedidoDto guardar(PedidoToSaveDto data) {
        Pedido pedido = this.pedidoMapper.peditoToSaveToPedido(data);
        Pedido pedidoSaved = this.pedidoRepository.save(pedido);
        return this.pedidoMapper.pedidoToDTO(pedidoSaved);
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
