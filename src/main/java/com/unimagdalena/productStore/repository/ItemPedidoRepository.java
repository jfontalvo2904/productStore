package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.ItemPedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {

    // Buscar Items del pedido por Pedido Id
    @Query("select  item from ItemPedido item where item.pedido.id = ?1")
    List<ItemPedido> findByPedidoId(Long pedidoId);

    // Buscar items del pedido para un producto específico
    @Query("select item from ItemPedido item where item.producto.id = ?1")
    List<ItemPedido> findByProductoId(Long productoId);

    // Calcular la suma del total de ventas para un producto
    @Query("SELECT SUM(ip.cantidad * ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id = ?1")
    Float calcularTotalVentasPorProducto( Long productoId);
}
