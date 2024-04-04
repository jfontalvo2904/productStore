package com.unimagdalena.productStore.controllers.producto;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.productStore.dto.producto.ProductoDto;
import com.unimagdalena.productStore.dto.producto.ProductoToSaveDto;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.services.producto.ProductoServices;

@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoServices entityService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getAll() {
        return new ResponseEntity<>(this.entityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/inStock")
    public ResponseEntity<List<ProductoDto>> getAllInStock() {
        return new ResponseEntity<>(this.entityService.getAllInStock(), HttpStatus.OK);
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<List<ProductoDto>> getAllByNombre(@PathVariable("stock") Integer stock) {
        return new ResponseEntity<>(this.entityService.getAllByStock(stock), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoDto>> getAllByNombre(@PathVariable("nombre") String nombre) {
        return new ResponseEntity<>(this.entityService.getAllByNombre(nombre), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/*")
    public ResponseEntity<ProductoDto> guardar(@RequestBody ProductoToSaveDto data) {
        return new ResponseEntity<>(this.entityService.guardar(data), HttpStatus.CREATED);
    }

}
