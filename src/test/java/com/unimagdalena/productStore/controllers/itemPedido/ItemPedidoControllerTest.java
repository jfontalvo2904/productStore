package com.unimagdalena.productStore.controllers.itemPedido;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.services.itemPedido.ItemPedidoServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPedidoControllerTest extends AbstractIntegrationDBTest {
    
    @MockBean
    private ItemPedidoServiceImpl itemPedidoService;

    @Autowired
    @InjectMocks
    private ItemPedidoController itemPedidoController;
    

    public List<ItemPedidoDto> itemsPedido() {
        return Arrays.asList(new ItemPedidoDto(1L, 1L, 1L, 2, 1000F));
    }

    public ItemPedidoDto itemPedido() {
        return new ItemPedidoDto(1L, 1L, 1L, 2, 1000F);
    }

    public ItemPedidoToSaveDto itemPedidoToSaveDto() {
        return new ItemPedidoToSaveDto(1L, 1L, 10);
    }

    public ItemPedidoToUpdateDto itemPedidoToUpdateDto() {
        return new ItemPedidoToUpdateDto(2L, 2L, 3);
    }



    @Test
    void obtenerTodos() {

        when(this.itemPedidoService.obtenerTodos()).thenReturn(this.itemsPedido());

        ResponseEntity<List<ItemPedidoDto>> response = this.itemPedidoController.obtenerTodos();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty().hasSize(1);
    }

    @Test
    void buscarPorId() {
        when(this.itemPedidoService.buscarItemPedidoPorId(anyLong()))
        .thenReturn(this.itemPedido());

        ResponseEntity<ItemPedidoDto> response = this.itemPedidoController.buscarPorId(1L);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isEqualTo(1L);
    }

    @Test
    void buscarPorPedidoId() {
        when(this.itemPedidoService.findByPedidoId(anyLong()))
        .thenReturn(this.itemsPedido());

        ResponseEntity<List<ItemPedidoDto>> response = this.itemPedidoController.buscarPorPedidoId(1L);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty();
        
    }

    @Test
    void buscarPorProductoId() {
        
        when(this.itemPedidoService.findByProductoId(anyLong()))
        .thenReturn(this.itemsPedido());

        ResponseEntity<List<ItemPedidoDto>> response = this.itemPedidoController.buscarPorProductoId(1L);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void ventasTotalesPorProducto() {

        when(this.itemPedidoService.calcularTotalVentasPorProducto(anyLong()))
        .thenReturn(3000F);

        ResponseEntity<Float> response = this.itemPedidoController.ventasTotalesPorProducto(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody()).isEqualTo(3000F);
    }

    @Test
    void crearItemPedido() {
        when(this.itemPedidoService.crearItemPedido(any(ItemPedidoToSaveDto.class)))
        .thenReturn(this.itemPedido());

        ResponseEntity<ItemPedidoDto> response = this.itemPedidoController.crearItemPedido(this.itemPedidoToSaveDto());
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody().id()).isNotNull();
    }

    @Test
    void actualizarItemPedido() {
        when(this.itemPedidoService.actualizarItemPedido(anyLong(), any(ItemPedidoToUpdateDto.class)))
        .thenReturn( this.itemPedido());

        ResponseEntity<ItemPedidoDto> response = this.itemPedidoController
        .actualizarItemPedido(1L,this.itemPedidoToUpdateDto());
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response.getBody().id()).isNotNull();
    }
}