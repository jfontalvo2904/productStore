package com.unimagdalena.productStore.dto.producto;

public record ProductoDto(Long id,
        String nombre,
        Float price,
        Integer stock) {
}
