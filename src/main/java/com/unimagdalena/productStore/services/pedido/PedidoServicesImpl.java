package com.unimagdalena.productStore.services.pedido;

import java.util.List;

import com.unimagdalena.productStore.exceptions.cliente.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unimagdalena.productStore.dto.pedido.PedidoDto;
import com.unimagdalena.productStore.dto.pedido.PedidoMapperImpl;
import com.unimagdalena.productStore.dto.pedido.PedidoToSaveDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.exceptions.pedido.PedidoNotFoundException;
import com.unimagdalena.productStore.repository.ClienteRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;

@Service
public class PedidoServicesImpl implements PedidoServices {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoMapperImpl pedidoMapper;

    @Override
    public List<PedidoDto> getAll() {
        List<Pedido> data = this.pedidoRepository.findAll();
        return this.pedidoMapper.pedidosToDTO(data);
    }

    @Override
    public PedidoDto guardar(PedidoToSaveDto data) throws PedidoNotFoundException {
        Cliente cliente = this.clienteRepository
                .findById(data.cliente_id())
                .orElseThrow(ClienteNotFoundException::new);

        Pedido pedido = this.pedidoMapper.peditoToSaveToPedido(data);
        pedido.setCliente(cliente);
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

}
