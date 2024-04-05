package com.unimagdalena.productStore.services.cliente;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.exceptions.cliente.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {


    List<ClienteDto> obtenerTodos();
    ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto);
    ClienteDto actualizarCliente(Long id, ClienteToUpdateDto clienteToUpdateDto) throws ClienteNotFoundException;
    ClienteDto buscarClientePorId(Long id) throws ClienteNotFoundException;
    Cliente buscarCliente(Long id) throws ClienteNotFoundException;
    void eliminarCliente(Long id) throws ClienteNotFoundException;
    ClienteDto buscarClientePorEmail(String email) throws ClienteNotFoundException;
    List<ClienteDto> buscarClientePorDireccion( String direccion);
    List<ClienteDto> buscarClientePorNombre(String nombre);

}
