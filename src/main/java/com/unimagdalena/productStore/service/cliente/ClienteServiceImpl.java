package com.unimagdalena.productStore.service.cliente;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.exception.cliente.ClienteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Override
    public ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto) {
        return null;
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteToUpdateDto clienteToUpdateDto) throws ClienteNotFoundException {
        return null;
    }

    @Override
    public ClienteDto buscarClientePorId(Long id) throws ClienteNotFoundException {
        return null;
    }

    @Override
    public void eliminarCliente(Long id) throws ClienteNotFoundException {

    }

    @Override
    public ClienteDto buscarClientePorEmail(String email) throws ClienteNotFoundException {
        return null;
    }

    @Override
    public List<ClienteDto> buscarClientePorDireccion(String direccion) {
        return null;
    }

    @Override
    public List<ClienteDto> buscarClientePorNombre(String nombre) {
        return null;
    }
}
