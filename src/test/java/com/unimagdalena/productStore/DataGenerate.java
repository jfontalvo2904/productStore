package com.unimagdalena.productStore;

import com.unimagdalena.productStore.entity.*;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DataGenerate {

    private Cliente cliente;
    private Pedido pedido;
    private Producto producto;
    private ItemPedido itemPedido;
    private DetalleEnvio detalleEnvio;


    public DataGenerate() {
        this.producto = this.producto();
        this.cliente = this.cliente();

        this.pedido = this.pedido();
        this.pedido.setPago(this.pago());

        this.cliente.setPedidos(this.pedidos());

        this.itemPedido = this.itemPedido();

        this.producto.setItemsPedido(this.itemPedidos());

        this.pedido.setItemsPedido(this.itemPedidos());

        this.detalleEnvio = this.detalleEnvio();

        this.pedido.setDetalleEnvio(this.detalleEnvio);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public DetalleEnvio getDetalleEnvio() {
        return detalleEnvio;
    }

    public LocalDateTime actualDate() {
        return LocalDateTime.now();
    }

    public Cliente cliente() {
        return new Cliente(1L,
                "cliente 1",
                "email1@example.com",
                "dirección1",
                null);
    }

    public Pago pago() {
        return new Pago(1L,this.pedido,2000D,this.actualDate(), MetodoDePago.DAVIPLATA);
    }

    public Pedido pedido() {
        return new Pedido(1L,this.cliente,this.actualDate(), PedidoStatus.PENDIENTE,this.detalleEnvio,null,null);
    }

    public DetalleEnvio detalleEnvio() {
        return new DetalleEnvio(1L,this.pedido,"dirección","transportadora","Ab33");
    }

    public Producto producto() {
        return new Producto(1L,"producto1",1000F,10,null);
    }

    public ItemPedido itemPedido() {
        return new ItemPedido(1L,this.pedido,this.producto,10,this.producto.getPrice());
    }

    public List<Pedido> pedidos() {
        return Arrays.asList(this.pedido);
    }

    public List<ItemPedido> itemPedidos() {
        return Arrays.asList(this.itemPedido);
    }


}
