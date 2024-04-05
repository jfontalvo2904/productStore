package com.unimagdalena.productStore.repository;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Pago;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class PagoRepositoryTest extends AbstractIntegrationDBTest {

    private ClienteRepository clienteRepository;
    private PedidoRepository pedidoRepository;

    private PagoRepository pagoRepository;

    private Cliente clienteActual;
    private Pedido pedidoActual;
    private Pago pagoActual;
   

    @Autowired
    public PagoRepositoryTest(ClienteRepository clienteRepository, 
                            PedidoRepository pedidoRepository, 
                            DetalleEnvioRepository detalleEnvioRepository, 
                            PagoRepository pagoRepository) {
        
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagoRepository = pagoRepository;
    }

    @BeforeEach
    void setUp() {
        this.pagoRepository.deleteAll();
        this.pagoRepository.flush();

        this.pedidoRepository.deleteAll();
        this.pedidoRepository.flush();

        this.clienteRepository.deleteAll();
        this.clienteRepository.flush();

        this.clienteActual = this.guardarDefaultCliente();
        this.pedidoActual = this.guardarDefaultPedidoSinPagar();
        this.pagoActual = this.guardarDefaulPago();
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

    Cliente guardarDefaultCliente(){
        Cliente cliente = this.crearDefaultCliente();
        return this.clienteRepository.save(cliente);
    }

    //pedidos 

    Pedido crearPedido(Cliente cliente,
                       LocalDateTime fechaPeido,
                       PedidoStatus status,
                       Pago pago) {

        return Pedido.builder()
                .cliente(cliente)
                .fechaPedido(fechaPeido)
                .status(status)
                .pago(pago)
                .build();

    }

    Pedido guardarDefaultPedidoSinPagar() {
        Pedido pedido = this.crearPedido(this.clienteActual, LocalDateTime.now(), PedidoStatus.PENDIENTE, null);
        return this.pedidoRepository.save(pedido);
    }

    Pago crearPago(Pedido pedido, double totalPago, LocalDateTime fechaPago, MetodoDePago metodoDePago) {
        
        return Pago.builder()
        .pedido(pedido)
        .totalPago(totalPago)
        .fechaPago(fechaPago)
        .metodoDePago(metodoDePago)
        .build();

    }

    Pago crearDefaultPago() {
        return this.crearPago(this.pedidoActual, 100000, LocalDateTime.now(), MetodoDePago.DAVIPLATA);
    }

    Pago guardarDefaulPago() {
        return this.pagoRepository.save(this.crearDefaultPago());
    }

    @Test
    void guardarPago() {
        assertThat(this.pagoActual).isNotNull();
        assertThat(this.pagoActual.getPedido()).isNotNull();
    }

    @Test
    void actualizarPago() {
        this.pagoActual.setMetodoDePago(MetodoDePago.EFECTIVO);
        Pago pagoSaved = this.pagoRepository.save(this.pagoActual);
        assertThat(pagoSaved.getMetodoDePago()).isEqualTo(MetodoDePago.EFECTIVO);

    }

    @Test
    void buscarPagoPorId() {
        Optional<Pago> pago = this.pagoRepository.findById(this.pagoActual.getId());
        assertThat(pago.isPresent()).isTrue();
    }

    @Test
    void eliminarPago() {
        this.pagoRepository.delete(this.pagoActual);
        Optional<Pago> pago = this.pagoRepository.findById(this.pagoActual.getId());
        assertThat(pago.isPresent()).isFalse();
        Optional<Cliente> cliente = this.clienteRepository.findById(this.clienteActual.getId());
        assertThat(cliente.isPresent()).isTrue();
        Optional<Pedido> pedido = this.pedidoRepository.findById(this.pedidoActual.getId());
        assertThat(pedido.isPresent()).isTrue();
    }

    @Test
    void recuperarPagosEnRangoDeFecha() {
        LocalDateTime fechaInicio = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2024, Month.APRIL, 3, 0, 0);
        this.pagoActual.setFechaPago(fechaInicio);
        this.pagoRepository.save(pagoActual);

        List<Pago> pagos = this.pagoRepository
                               .recuperarPagosEnRangoDeFecha(fechaInicio, fechaFin);

        assertThat(pagos).isNotEmpty().hasSize(1);

        List<Pago> pagos2 = this.pagoRepository.recuperarPagosEnRangoDeFecha(fechaFin, fechaFin);

        assertThat(pagos2).isEmpty();

    }

    @Test
    void recuperarPagosPorPedidoYMetodoDePago() {
        Long idPedido = this.pedidoActual.getId();

        List<Pago> pagos = this.pagoRepository
                .recuperarPagosPorPedidoYMetodoDePago(idPedido,MetodoDePago.DAVIPLATA);
        assertThat(pagos).isNotEmpty();

        List<Pago> pagos2 = this.pagoRepository
                .recuperarPagosPorPedidoYMetodoDePago(idPedido,MetodoDePago.NEQUI);

        assertThat(pagos2).isEmpty();
    }
}