package com.unimagdalena.productStore.repository;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.entity.*;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class ItemPedidoRepositoryTest extends AbstractIntegrationDBTest {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private Cliente clienteActual;
    private Pedido pedidoActual;
    private Producto productoActual;
    private ItemPedido itemPedidoActual;


    @Autowired
    public ItemPedidoRepositoryTest(ClienteRepository clienteRepository,
                                    PedidoRepository pedidoRepository,
                                    DetalleEnvioRepository detalleEnvioRepository,
                                    ItemPedidoRepository itemPedidoRepository,
                                    ProductoRepository productoRepository) {

        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.productoRepository = productoRepository;

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

    //Item pedido

    ItemPedido crearItemPedido(Pedido pedido,
                               Producto producto,
                               Integer cantidad,
                               Float precioUnitario) {

        return ItemPedido.builder()
                .pedido(pedido)
                .producto(producto)
                .cantidad(cantidad)
                .precioUnitario(precioUnitario)
                .build();
    }


    ItemPedido guardarDefaultItemPedido() {
        ItemPedido itemPedido = this.crearItemPedido(this.pedidoActual,
                this.productoActual,
                10,
                this.productoActual.getPrice());


        return this.itemPedidoRepository.save(itemPedido);
    }

    //Productos

    Producto crarProducto(String nombre, Float price, Integer stock){
        return Producto.builder()
                .nombre(nombre)
                .price(price)
                .stock(stock)
                .build();
    }

    Producto guardarDefaultProducto() {
        Producto producto = this.crarProducto("café",1000F,10);
        return this.productoRepository.save(producto);
    }

    @BeforeEach
    void setUp() {
        this.itemPedidoRepository.deleteAll();
        this.itemPedidoRepository.flush();

        this.productoRepository.deleteAll();
        this.productoRepository.flush();

        this.pedidoRepository.deleteAll();
        this.pedidoRepository.flush();

        this.clienteRepository.deleteAll();
        this.clienteRepository.flush();

        this.clienteActual = this.guardarDefaultCliente(); //cliente que hace el pedido
        this.pedidoActual = this.guardarDefaultPedidoSinPagar(); //el respectivo pedido que hizo el cleinte
        this.productoActual = this.guardarDefaultProducto(); //el producto que va a tener un item de pedido
        this.itemPedidoActual = this.guardarDefaultItemPedido();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void crearItemPedido() {
        assertThat(this.itemPedidoActual).isNotNull();
        assertThat(this.itemPedidoActual.getId()).isNotNull();
    }

    @Test
    void buscarItemPedidoPorId() {
        Long id = this.itemPedidoActual.getId();
        Optional<ItemPedido> itemPedido = this.itemPedidoRepository.findById(id);
        assertThat(itemPedido.isPresent()).isTrue();
    }

    @Test
    void actualizarItemPedido() {
        this.itemPedidoActual.setCantidad(30);
        this.itemPedidoRepository.save(itemPedidoActual);
        Optional<ItemPedido> pedido = this.itemPedidoRepository.findById(this.itemPedidoActual.getId());
        assertThat(pedido.isPresent()).isTrue();
        assertThat(pedido.get().getCantidad()).isEqualTo(30);
    }

    @Test
    void eliminarPedido() {
        this.itemPedidoRepository.delete(this.itemPedidoActual);
        Optional<ItemPedido> itemPedidoBuscado = this.itemPedidoRepository.findById(this.itemPedidoActual.getId());
        assertThat(itemPedidoBuscado.isPresent()).isFalse();
    }

    @Test
    void totalVentasProducto() {
        Long id = this.productoActual.getId();
        Float totalVentas = this.itemPedidoRepository.calcularTotalVentasPorProducto(id);
        assertThat(totalVentas).isEqualTo(10 * 1000F);
    }

    @Test
    void itemDeUnProducto() {
        List<ItemPedido> items = this.itemPedidoRepository.findByProductoId(this.productoActual.getId());
        assertThat(items).isNotEmpty().hasSize(1);
    }

    @Test
    void itemPorPedidoId() {
        List<ItemPedido> items = this.itemPedidoRepository.findByPedidoId(this.pedidoActual.getId());
        assertThat(items).isNotEmpty().hasSize(1);
    }
}