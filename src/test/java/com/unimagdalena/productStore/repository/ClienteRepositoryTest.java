package com.unimagdalena.productStore.repository;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.entity.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteRepositoryTest extends AbstractIntegrationDBTest {

    private ClienteRepository clienteRepository;

    private Cliente clienteActual;

    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @BeforeEach
    void setUp() {
        this.clienteActual = this.crearYguardarClienteEnBd();
    }

    @AfterEach
    void after() {
        this.clienteRepository.deleteAll();
        this.clienteRepository.flush();
    }

    Cliente crearDefaultCliente() {
        return this.crearCliente("José", "fontalvo0218@gmail.com", "calle 10B");
    }

    Cliente crearCliente( String nombre, String email, String direccion) {
        return  Cliente.builder()
                .nombre(nombre)
                .email(email)
                .direccion(direccion)
                .build();
    }

    Cliente crearYguardarClienteEnBd(){
        Cliente cliente = this.crearDefaultCliente();
        return this.clienteRepository.save(cliente);
    }

    @Test
    void guardarCliente() {
        assertThat(this.clienteActual).isNotNull();
        assertThat(this.clienteActual.getId()).isNotNull();
    }

    @Test
    void buscarClientePorId() {
        Long clienteId = this.clienteActual.getId();

        Optional<Cliente> cliente = this.clienteRepository.findById(clienteId);

        assertThat(cliente.isPresent()).isTrue();

    }

    @Test
    void actualizarCliente() {

        this.clienteActual.setNombre("nuevo nombre");

        Cliente clienteSaved =  this.clienteRepository.save(clienteActual);

        assertThat(clienteSaved.getNombre()).isEqualTo("nuevo nombre");
    }

    @Test
    void eliminarCliente() {

        this.clienteRepository.delete(this.clienteActual);
        Optional<Cliente> clienteExist = this.clienteRepository.findById(clienteActual.getId());
        assertThat(clienteExist.isPresent()).isFalse();
    }

    @Test
    void buscarClientePorEmail() {
        String email = this.clienteActual.getEmail();
        Cliente clienteBuscado = this.clienteRepository.buscarClientePorEmail(email);
        assertThat(clienteBuscado).isNotNull();
        assertThat(clienteBuscado.getEmail()).isEqualTo(email);
    }

    @Test
    void buscarClientePorEmailQueNoExiste() {
        Cliente clienteBuscado = this.clienteRepository.buscarClientePorEmail("prueba@correo.com");
        assertThat(clienteBuscado).isNull();
    }

    @Test
    void buscarClientesPorDireccion(){
        Cliente cliente = this.crearCliente("Victor", "victor@correo.com", "calle 10B");

        this.clienteRepository.save(cliente);

        List<Cliente> clientes = this.clienteRepository.buscarClientesPorDireccion("calle 10B");

        assertThat(clientes).isNotEmpty().hasSize(2);
    }

    @Test
    void buscarClientesPorNombre(){
        Cliente cliente1 = this.crearCliente("Victor", "victor@correo.com", "calle 10B");
        Cliente cliente2 = this.crearCliente("José Alberto", "jose@correo.com", "calle 10B");
        this.clienteRepository.save(cliente1);
        this.clienteRepository.save(cliente2);

        List<Cliente> clientes = this.clienteRepository.buscarClientesPorNombre("José");
        List<Cliente> clientes2 = this.clienteRepository.buscarClientesPorNombre("Victor");

        assertThat(clientes2).isNotEmpty().hasSize(1);
    }


}