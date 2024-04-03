package com.unimagdalena.productStore.dto;

public record ProductoDto(Long id,
        String nombre,
        float price,
        Integer stock) {
}
