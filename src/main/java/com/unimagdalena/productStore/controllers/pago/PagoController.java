package com.unimagdalena.productStore.controllers.pago;

import com.unimagdalena.productStore.dto.pago.PagoDto;
import com.unimagdalena.productStore.dto.pago.PagoToSaveDto;
import com.unimagdalena.productStore.dto.pago.PagoToUpdateDto;
import com.unimagdalena.productStore.services.pago.PagoServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class PagoController {

    private final PagoServiceImpl pagoService;

    @Autowired
    public PagoController(PagoServiceImpl pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("")
    ResponseEntity<List<PagoDto>>  obtenerTodos() {
        return ResponseEntity.ok().body(this.pagoService.obtenerTodos());
    }

    @PostMapping("")
    ResponseEntity<PagoDto> crearPago(@RequestBody PagoToSaveDto pagoToSaveDto) {
        return ResponseEntity.ok().body(this.pagoService.crearPago(pagoToSaveDto));
    }

    @PutMapping("/{pagoId}")
    ResponseEntity<PagoDto> actualizarPago(@PathVariable("pagoId") Long pagoId, @RequestBody PagoToUpdateDto pagoToUpdateDto){
        return ResponseEntity.ok().body(this.pagoService.actualizarPago(pagoId, pagoToUpdateDto));
    }

    @GetMapping("/{pedidoId}")
    ResponseEntity<PagoDto> buscarPagoPorId( @PathVariable("pedidoId") Long id) {
        return ResponseEntity.ok().body(this.pagoService.buscarPagoPorId(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> eliminarPago(@PathVariable("id") Long id) {
        this.pagoService.eliminarPago(id);
        return ResponseEntity.ok().body("Pago eliminado correctamente");
    }

}
