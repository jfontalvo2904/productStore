package com.unimagdalena.productStore.controllers;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.productStore.dto.ProductoDto;
import com.unimagdalena.productStore.dto.ProductoTSDto;
import com.unimagdalena.productStore.services.ProductoServices;

@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoServices entityService;

    @GetMapping
    public List<ProductoDto> getAll() {
        return entityService.getAll();
    }

    @PostMapping(consumes = "application/*")
    public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoTSDto data) {
        return new ResponseEntity<>(entityService.guardar(data), HttpStatus.CREATED);
    }

}
