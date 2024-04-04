package com.unimagdalena.productStore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unimagdalena.productStore.dto.PedidoDto;
import com.unimagdalena.productStore.services.PedidoServices;

@RestController
@RequestMapping("/pedido")

public class PedidoController {
    @Autowired
    private PedidoServices entityService;

}
