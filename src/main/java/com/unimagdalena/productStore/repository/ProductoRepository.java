package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);

    @Query("SELECT x FROM Producto x WHERE x.stock > 0")
    List<Producto> buscarProductosEnStock();

    @Query("SELECT x FROM Producto x WHERE x.stock <= ?1")
    List<Producto> buscarProductosPorStock(Integer stock);
}
