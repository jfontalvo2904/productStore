package com.unimagdalena.productStore.controllers.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.repository.ClienteRepository;

import static org.assertj.core.api.Assertions.assertThat;


class ClienteControllerTest extends AbstractIntegrationDBTest{

    private ClienteController clienteController;
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @Autowired
    public ClienteControllerTest(ClienteController clienteController, ClienteRepository clienteRepository){
        this.clienteController = clienteController;
        this.clienteRepository = clienteRepository;
    }

    public ClienteToSaveDto clienteToSave() {
        return new ClienteToSaveDto("cliente 1", "email@example.com", "dirección");
    }


    @BeforeEach
    void setUp() {
        this.clienteRepository.deleteAll();
        Cliente clienteToSave = new Cliente(null,"default cliente", "cliente@example.com","dirección", null);
        this.cliente = this.clienteRepository.save(clienteToSave);
        this.clienteRepository.flush();

    }

    @Test
    void obtenerClientes() {

        ResponseEntity<List<ClienteDto>> response = this.clienteController.obtenerClientes();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }

    @Test
    void buscarPorId() {
        Long clienteId = this.cliente.getId();
        ResponseEntity<ClienteDto> response = this.clienteController.buscarPorId(clienteId);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        assertThat(response.getBody().id())
        .isNotNull()
        .isEqualTo(clienteId);
    }

    @Test
    void buscarPorEmail() {
        ResponseEntity<ClienteDto> response = this.clienteController.buscarPorEmail(this.cliente.getEmail());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void buscarPorNombre() {
        ResponseEntity<List<ClienteDto>> response = this.clienteController.buscarPorNombre(this.cliente.getNombre());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }

    @Test
    void buscarClientesPorDireccion() {
        ResponseEntity<List<ClienteDto>> response = this.clienteController.buscarClientesPorDireccion(this.cliente.getDireccion());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }

    @Test
    void guardarCliente() {
        ClienteToSaveDto clienteToSave = this.clienteToSave();
        ResponseEntity<ClienteDto> response = this.clienteController.guardarCliente(clienteToSave);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void actualizarCliente() {
        ClienteToUpdateDto clienteToUpdateDto = new ClienteToUpdateDto(
                                                                "nuevo nombre",
                                                                "nuevo@email.com",
                                                                "nueva dirección");

        ResponseEntity<ClienteDto> response= this.clienteController.actualizarCliente(this.cliente.getId(), clienteToUpdateDto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        ClienteDto cliente = response.getBody();
        
        assertThat(cliente).isNotNull();
        assertThat(cliente.nombre()).isEqualTo("nuevo nombre");
        assertThat(cliente.email()).isEqualTo("nuevo@email.com");
        assertThat(cliente.direccion()).isEqualTo("nueva dirección");

    }

    @Test
    void eliminarCliente() {
        ResponseEntity<String> response = this.clienteController.eliminarCliente(this.cliente.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo("Cliente eliminado correctamente");
    }
}