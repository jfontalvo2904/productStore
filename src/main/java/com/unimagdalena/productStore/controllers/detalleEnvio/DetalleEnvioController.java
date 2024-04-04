package com.unimagdalena.productStore.controllers.detalleEnvio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioDto;
import com.unimagdalena.productStore.dto.detalleEnvio.DetalleEnvioToSaveDto;
import com.unimagdalena.productStore.services.detalleEnvio.DetalleEnvioServices;

@RestController
@RequestMapping("/detalleEnvios")
public class DetalleEnvioController {
    @Autowired
    private DetalleEnvioServices entityService;

    @GetMapping
    public ResponseEntity<List<DetalleEnvioDto>> getAll() {
        return new ResponseEntity<>(this.entityService.getAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/*")
    public ResponseEntity<DetalleEnvioDto> guardar(@RequestBody DetalleEnvioToSaveDto data) {
        return new ResponseEntity<>(this.entityService.guardar(data), HttpStatus.CREATED);
    }

}
