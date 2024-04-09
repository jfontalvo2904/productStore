package com.unimagdalena.productStore.controllers.pago;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.dto.pago.PagoDto;
import com.unimagdalena.productStore.dto.pago.PagoToSaveDto;
import com.unimagdalena.productStore.dto.pago.PagoToUpdateDto;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import com.unimagdalena.productStore.services.pago.PagoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

class PagoControllerTest extends AbstractIntegrationDBTest {

    @MockBean
    private PagoServiceImpl pagoService;

    @InjectMocks
    @Autowired
    private PagoController pagoController;

    PagoDto pagoDto(){
        return new PagoDto(
                1L,
                1L,
                2000D,
                LocalDateTime.now(),
                MetodoDePago.DAVIPLATA);
    }

    List<PagoDto> pagosDto() {
        return Arrays.asList(this.pagoDto());
    }

    PagoToSaveDto pagoToSaveDto() {
        return new PagoToSaveDto(1L,200D,MetodoDePago.EFECTIVO);
    }

    PagoToUpdateDto pagoToUpdateDto() {
        return new PagoToUpdateDto(1L,1000D,MetodoDePago.PSE);
    }

    @Test
    void obtenerTodos() {
        when(this.pagoService.obtenerTodos()).thenReturn(this.pagosDto());
        ResponseEntity<List<PagoDto>> response = this.pagoController.obtenerTodos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }

    @Test
    void crearPago() {
        when(this.pagoService.crearPago(any(PagoToSaveDto.class))).thenReturn(this.pagoDto());
        ResponseEntity<PagoDto> response = this.pagoController.crearPago(this.pagoToSaveDto());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody().id()).isNotNull();
    }

    @Test
    void actualizarPago() {
        when(this.pagoService.actualizarPago(anyLong(),any(PagoToUpdateDto.class))).thenReturn(this.pagoDto());
        ResponseEntity<PagoDto> response = this.pagoController.actualizarPago(1L,this.pagoToUpdateDto());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody().id()).isNotNull();
    }

    @Test
    void buscarPagoPorId() {
        when(this.pagoService.buscarPagoPorId(anyLong())).thenReturn(this.pagoDto());
        ResponseEntity<PagoDto> response = this.pagoController.buscarPagoPorId(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody().id()).isNotNull();
    }

    @Test
    void eliminarPago() {
        doNothing().when(this.pagoService).eliminarPago(anyLong());
        ResponseEntity<String> response = this.pagoController.eliminarPago(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo("Pago eliminado correctamente");
    }

    @Test
    void recuperarPagosEnRangoDeFecha() {
        when(this.pagoService.recuperarPagosEnRangoDeFecha(
                any(LocalDateTime.class),
                any(LocalDateTime.class))).thenReturn(this.pagosDto());

        ResponseEntity<List<PagoDto>> response = this.pagoController
                .recuperarPagosEnRangoDeFecha(LocalDateTime.now(),LocalDateTime.now());


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty().hasSize(1);

    }

    @Test
    void recuperarPagosPorPedidoYMetodoDePago() {
        when(this.pagoService.recuperarPagosPorPedidoYMetodoDePago(
               1L,
                MetodoDePago.EFECTIVO)).thenReturn(this.pagosDto());

        ResponseEntity<List<PagoDto>> response = this.pagoController
                .recuperarPagosPorPedidoYMetodoDePago(1L,MetodoDePago.EFECTIVO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }
}