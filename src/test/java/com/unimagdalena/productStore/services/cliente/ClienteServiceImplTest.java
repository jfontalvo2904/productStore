package com.unimagdalena.productStore.services.cliente;

import static org.mockito.Mockito.*;

import com.unimagdalena.productStore.dto.cliente.ClienteToSaveDto;
import com.unimagdalena.productStore.dto.cliente.ClienteToUpdateDto;
import com.unimagdalena.productStore.exceptions.cliente.ClienteNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.dto.cliente.ClienteDto;
import com.unimagdalena.productStore.repository.ClienteRepository;
import com.unimagdalena.productStore.entity.Cliente;

import java.util.*;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class ClienteServiceImplTest extends AbstractIntegrationDBTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;


    List<Cliente> listaDeClientes() {
        // Crear algunos clientes de ejemplo
        Cliente cliente1 = new Cliente(1L, "Cliente 1", "cliente1@example.com", "Dirección 1", null);
        Cliente cliente2 = new Cliente(2L, "Cliente 2", "cliente2@example.com", "Dirección 2", null);

        return Arrays.asList(cliente1,cliente2);
    }

    @BeforeEach
    void setUp(){

        when(clienteRepository.findById(anyLong())).thenAnswer(invocation -> {

            Long id = invocation.getArgument(0); // Obtener el id pasado como argumento

            // Buscar en la lista de clientes si existe un cliente con ese id
            for (Cliente cliente : this.listaDeClientes()) {
                if (Objects.equals(cliente.getId(), id)) {
                    return Optional.of(cliente); // Devolver el cliente si se encuentra en la lista
                }
            }

            throw  new ClienteNotFoundException();
        });

    }
    
    @Test
    void testActualizarCliente() {

        doAnswer(invocation -> {
            return invocation.<Cliente>getArgument(0);
        }).when(clienteRepository).save(any(Cliente.class));

        ClienteToUpdateDto clienteToUpdateDto =
                new ClienteToUpdateDto(
                "nuevo nombre",
                "nuevoEmail",
                "nuevaDireccion");

        ClienteDto clienteActualizado = this.clienteService
                .actualizarCliente(1L ,clienteToUpdateDto);

        assertThat(clienteActualizado).isNotNull();
        assertThat(clienteActualizado.nombre()).isEqualTo("nuevo nombre");
        assertThat(clienteActualizado.email()).isEqualTo("nuevoEmail");
        assertThat(clienteActualizado.direccion()).isEqualTo("nuevaDireccion");
    }

    @Test
    void testBuscarCliente() {
        ClienteDto cliente = clienteService.buscarClientePorId(1L);
        assertThat(cliente).isNotNull();
    }

    @Test
    void testBuscarClientePorDireccion() {

        when(clienteRepository.buscarClientesPorDireccion(anyString())).thenAnswer(invocation -> {

            String direccion = invocation.getArgument(0); // Obtener el id pasado como argumento

            List<Cliente> clientes = new ArrayList<>();

            // Buscar en la lista de clientes si existe un cliente con ese id
            for (Cliente cliente : this.listaDeClientes()) {
                if (Objects.equals(cliente.getDireccion(), direccion)) {
                    clientes.add(cliente);
                }
            }

            return clientes;
        });

        List<ClienteDto> clientes = this.clienteService.buscarClientePorDireccion("Dirección 1");
        assertThat(clientes).isNotEmpty().hasSize(1);
    }

    @Test
    void testBuscarClientePorEmail() {

        when(clienteRepository.buscarClientePorEmail(anyString()))
                .thenReturn( new Cliente(1L, "Cliente 1", "cliente1@example.com", "Dirección 1", null));

        ClienteDto clienteDto  = this.clienteService.buscarClientePorEmail("cliente1@example.com");

        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.email()).isEqualTo("cliente1@example.com");

    }

    @Test
    void testBuscarClientePorId() {

        ClienteDto cliente = this.clienteService.buscarClientePorId(1L);
        verify(this.clienteRepository).findById(1L);
        assertThat(cliente).isNotNull();
        assertThat(cliente.id()).isNotNull();
    }

    @Test
    void testBuscarClientePorNombre() {
        when(this.clienteRepository.buscarClientesPorNombre(anyString())).thenReturn(this.listaDeClientes());
        List<ClienteDto> clientes = this.clienteService.buscarClientePorNombre("nombre");
        assertThat(clientes).isNotEmpty().hasSize(2);
    }

    @Test
    void testCrearCliente() {
        ClienteToSaveDto cliente = new ClienteToSaveDto("Jose", "email","dirección");

        doAnswer(invocation -> {
            Cliente clienteArgument = invocation.<Cliente>getArgument(0);
            clienteArgument.setId(1L);
            return clienteArgument;
        }).when(clienteRepository).save(any(Cliente.class));

        ClienteDto clienteSaved = this.clienteService.crearCliente(cliente);

        assertThat(clienteSaved).isNotNull();
        assertThat(clienteSaved.nombre()).isEqualTo("Jose");
        assertThat(clienteSaved.email()).isEqualTo("email");
        assertThat(clienteSaved.direccion()).isEqualTo("dirección");

    }

    @Test
    void testObtenerTodos() {
        when(clienteRepository.findAll()).thenReturn(this.listaDeClientes());

        List<ClienteDto> clientes = this.clienteService.obtenerTodos();

        verify(this.clienteRepository).findAll();

        assertThat(clientes)
                .isNotEmpty()
                .hasSize(2);
    }
}
