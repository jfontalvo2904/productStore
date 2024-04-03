package com.unimagdalena.productStore.dto;

public record ProductoTSDto(
        String nombre,
        float price,
        Integer stock) {
}