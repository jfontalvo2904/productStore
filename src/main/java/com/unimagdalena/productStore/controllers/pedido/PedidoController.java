package com.unimagdalena.productStore.controllers.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.productStore.dto.pedido.PedidoDto;
import com.unimagdalena.productStore.dto.pedido.PedidoToSaveDto;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.services.pedido.PedidoServices;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {
    @Autowired
    private PedidoServices entityService;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> getAll() {
        return new ResponseEntity<>(this.entityService.getAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/*")
    public ResponseEntity<PedidoDto> guardar(@RequestBody PedidoToSaveDto data) {
        return new ResponseEntity<>(this.entityService.guardar(data), HttpStatus.CREATED);
    }

}
