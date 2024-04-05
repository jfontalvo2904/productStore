package com.unimagdalena.productStore.services.cliente;

import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteMapperImpl;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.exception.cliente.ClienteNotFoundException;
import com.unimagdalena.productStore.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;
    private final ClienteMapperImpl clienteMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapperImpl clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public Cliente buscarCliente(Long id) throws ClienteNotFoundException {
        return this.clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }

    @Override
    public List<ClienteDto> obtenerTodos() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return this.clienteMapper.clientesToClientesDto(clientes);
    }

    @Override
    public ClienteDto crearCliente(ClienteToSaveDto clienteToSaveDto) {
        Cliente cliente = this.clienteMapper.clienteToSaveToCliente(clienteToSaveDto);
        Cliente clienteSaved = this.clienteRepository.save(cliente);
        return this.clienteMapper.clienteToClienteDto(clienteSaved);
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteToUpdateDto clienteToUpdateDto) throws ClienteNotFoundException {

        Cliente cliente = buscarCliente(id);

        if(clienteToUpdateDto.nombre() != null) {
            cliente.setNombre(clienteToUpdateDto.nombre());
        }

        if(clienteToUpdateDto.email() != null) {
            cliente.setEmail(clienteToUpdateDto.email());
        }

        if(clienteToUpdateDto.direccion() != null) {
            cliente.setDireccion(clienteToUpdateDto.direccion());
        }

        Cliente clienteUpdated = this.clienteRepository.save(cliente);
        return this.clienteMapper.clienteToClienteDto(clienteUpdated);

    }

    @Override
    public ClienteDto buscarClientePorId(Long id) throws ClienteNotFoundException {
        Cliente cliente = buscarCliente(id);
        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public void eliminarCliente(Long id) throws ClienteNotFoundException {
        Cliente cliente = this.buscarCliente(id);
        this.clienteRepository.delete(cliente);
    }

    @Override
    public ClienteDto buscarClientePorEmail(String email) throws ClienteNotFoundException {
        Cliente cliente = this.clienteRepository.buscarClientePorEmail(email);
        if(cliente == null) throw new ClienteNotFoundException();

        return this.clienteMapper.clienteToClienteDto(cliente);
    }

    @Override
    public List<ClienteDto> buscarClientePorDireccion(String direccion) {
        List<Cliente> clientes = this.clienteRepository.buscarClientesPorDireccion(direccion);
        return this.clienteMapper.clientesToClientesDto(clientes);
    }

    @Override
    public List<ClienteDto> buscarClientePorNombre(String nombre) {
        List<Cliente> clientes = this.clienteRepository.buscarClientesPorNombre(nombre);
        return this.clienteMapper.clientesToClientesDto(clientes);
    }
}
