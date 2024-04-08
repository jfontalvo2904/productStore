package com.unimagdalena.productStore.controllers.itemPedido;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.services.itemPedido.ItemPedidoServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/v1/orders-items")
public class ItemPedidoController {

    private final ItemPedidoServiceImpl itemPedidoService;

    public ItemPedidoController(ItemPedidoServiceImpl itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("")
    ResponseEntity<List<ItemPedidoDto>> obtenerTodos(){
        List<ItemPedidoDto> itemPedidos = this.itemPedidoService.obtenerTodos();
        return ResponseEntity.ok().body(itemPedidos);
    }

    @GetMapping("/{id}")
    ResponseEntity<ItemPedidoDto> buscarPorId(@PathVariable("id") Long id){
        ItemPedidoDto itemPedidoDto = this.itemPedidoService.buscarItemPedidoPorId(id);
        return ResponseEntity.ok().body(itemPedidoDto);
    }

    @GetMapping("/order/{pedidoId}")
    ResponseEntity<List<ItemPedidoDto>> buscarPorPedidoId(@PathVariable("pedidoId") Long pedidoId ) {
        List<ItemPedidoDto> itemPedidoDtos = this.itemPedidoService.findByPedidoId(pedidoId);
        return ResponseEntity.ok().body(itemPedidoDtos);
    }

    @GetMapping("/product/{productoId}")
    ResponseEntity<List<ItemPedidoDto>> buscarPorProductoId(@PathVariable("productoId") Long productoId ) {
        List<ItemPedidoDto> itemPedidoDtos = this.itemPedidoService.findByProductoId(productoId);
        return ResponseEntity.ok().body(itemPedidoDtos);
    }

    @GetMapping("/product/sales/{productoId}")
    ResponseEntity<Float> ventasTotalesPorProducto(@PathVariable("productoId") Long productoId) {
        return ResponseEntity
                .ok()
                .body(this.itemPedidoService.calcularTotalVentasPorProducto(productoId));
    }

    @PostMapping("")
    public ResponseEntity<ItemPedidoDto> crearItemPedido(@RequestBody ItemPedidoToSaveDto itemPedidoToSaveDto) {     
        return ResponseEntity.status(HttpStatus.CREATED).body(this.itemPedidoService.crearItemPedido(itemPedidoToSaveDto));    
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<ItemPedidoDto> actualizarItemPedido(@PathVariable("pedidoId") Long pedidoId,
                                       @RequestBody ItemPedidoToUpdateDto itemPedidoToUpdateDto) {
        
        return ResponseEntity.ok().body(this.itemPedidoService.actualizarItemPedido(pedidoId, itemPedidoToUpdateDto));
    }


    

    
}
