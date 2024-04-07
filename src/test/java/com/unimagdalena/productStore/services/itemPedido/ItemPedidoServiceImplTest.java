package com.unimagdalena.productStore.services.itemPedido;

import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.DataGenerate;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.entity.ItemPedido;
import com.unimagdalena.productStore.repository.ItemPedidoRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;
import com.unimagdalena.productStore.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPedidoServiceImplTest extends AbstractIntegrationDBTest {

    @MockBean
    ProductoRepository productoRepository;
    @MockBean
    private PedidoRepository pedidoRepository;
    @MockBean
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    @Autowired
    private ItemPedidoServiceImpl itemPedidoService;

    private DataGenerate dataGenerate = new DataGenerate();

    @BeforeEach
    void setUp() {
        when(this.itemPedidoRepository.findById(anyLong()))
                .thenReturn(Optional.of(this.dataGenerate.getItemPedido()));
    }

    @Test
    void obtenerTodos() {
        when(this.itemPedidoRepository.findAll())
                .thenReturn(this.dataGenerate.itemPedidos());
        List<ItemPedidoDto> itemPedidos = this.itemPedidoService.obtenerTodos();

        assertThat(itemPedidos).isNotEmpty().hasSize(1);
    }

    @Test
    void crearItemPedido() {
       when(this.pedidoRepository.findById(anyLong()))
               .thenReturn(Optional.of(this.dataGenerate.getPedido()));

       when(this.productoRepository.findById(anyLong()))
               .thenReturn(Optional.of(this.dataGenerate.getProducto()));

       when(this.itemPedidoRepository.save(any(ItemPedido.class))).thenAnswer( invocation->{
           ItemPedido itemPedido = invocation.getArgument(0);
           itemPedido.setId(2L);
           return itemPedido;
       } );

        ItemPedidoToSaveDto itemPedidoToSaveDto = new ItemPedidoToSaveDto(1L,1L,5);

        ItemPedidoDto itemPedidoDto  = this.itemPedidoService.crearItemPedido(itemPedidoToSaveDto);

        assertThat(itemPedidoDto).isNotNull();
        assertThat(itemPedidoDto.id()).isEqualTo(2L);
    }

    @Test
    void actualizarItemPedido() {

        when(this.itemPedidoRepository.save(any(ItemPedido.class)))
                .thenAnswer( invocation-> invocation.getArgument(0));

        ItemPedidoToUpdateDto itemPedidoToUpdateDto = new ItemPedidoToUpdateDto(null,null,3);
        ItemPedidoDto itemPedidoDto  = this.itemPedidoService
                .actualizarItemPedido(1L,itemPedidoToUpdateDto);

        assertThat(itemPedidoDto).isNotNull();
        assertThat(itemPedidoDto.cantidad()).isEqualTo(3);

    }

    @Test
    void buscarItemPedidoPorId() {
        ItemPedidoDto itemPedidoDto = this.itemPedidoService.buscarItemPedidoPorId(1L);
        assertThat(itemPedidoDto).isNotNull();
        assertThat(itemPedidoDto.id())
                .isNotNull()
                .isEqualTo(1L);
    }

    @Test
    void findByPedidoId() {
        when(this.itemPedidoRepository.findByPedidoId(anyLong()))
                .thenReturn(this.dataGenerate.itemPedidos());

        List<ItemPedidoDto> items = this.itemPedidoService.findByPedidoId(1L);
        assertThat(items).isNotEmpty().hasSize(1);
    }

    @Test
    void findByProductoId() {
        when(this.itemPedidoRepository.findByProductoId(anyLong()))
                .thenReturn(this.dataGenerate.itemPedidos());

        List<ItemPedidoDto> items = this.itemPedidoService.findByProductoId(1L);
        assertThat(items).isNotEmpty().hasSize(1);
    }
}