package com.unimagdalena.productStore.service.cliente;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.exception.cliente.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {

    ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto);
    ClienteDto actualizarCliente(Long id, ClienteToUpdateDto clienteToUpdateDto) throws ClienteNotFoundException;
    ClienteDto buscarClientePorId(Long id) throws ClienteNotFoundException;
    void eliminarCliente(Long id) throws ClienteNotFoundException;
    ClienteDto buscarClientePorEmail(String email) throws ClienteNotFoundException;
    List<ClienteDto> buscarClientePorDireccion( String direccion);
    List<ClienteDto> buscarClientePorNombre(String nombre);


}
