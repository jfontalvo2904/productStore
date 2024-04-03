package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
