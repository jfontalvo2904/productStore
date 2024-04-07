package com.unimagdalena.productStore.services.pago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import com.unimagdalena.productStore.repository.PagoRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;
import com.unimagdalena.productStore.AbstractIntegrationDBTest;
import com.unimagdalena.productStore.dto.pago.PagoDto;
import com.unimagdalena.productStore.dto.pago.PagoToSaveDto;
import com.unimagdalena.productStore.dto.pago.PagoToUpdateDto;
import com.unimagdalena.productStore.entity.Pago;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PagoServiceImplTest extends AbstractIntegrationDBTest {
    
    @MockBean
    private PagoRepository pagoRepository;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Autowired
    @InjectMocks
    private PagoServiceImpl pagoService;

    List<Pedido> listaDePedidos() {
        LocalDateTime fechaActual = LocalDateTime.now();

        Pedido pedido = new Pedido(1L, null,fechaActual, PedidoStatus.PENDIENTE, null, null, null);
        Pedido pedido1 = new Pedido(1L, null,fechaActual, PedidoStatus.PENDIENTE, null, null, null);
        return Arrays.asList(pedido,pedido1);
    }
    
    List<Pago> listaDePagos() {
        LocalDateTime fechaActual = LocalDateTime.now();

        List<Pedido> listaDePedidos = this.listaDePedidos();

        Pago pago1 = new Pago(1L,listaDePedidos.get(0),2000D,fechaActual,MetodoDePago.DAVIPLATA);
        Pago pago2 = new Pago(1L,listaDePedidos.get(1),2000D,fechaActual,MetodoDePago.DAVIPLATA); 
        
        return Arrays.asList(pago1,pago2);
    }

  
    @BeforeEach
    void setUp() {
    }

    @Test
    void obtenerTodos() {
        when(this.pagoRepository.findAll()).thenReturn(this.listaDePagos());
        List<PagoDto> pagoDtos = this.pagoService.obtenerTodos();
        assertThat(pagoDtos).isNotEmpty().hasSize(2);

    }

    @Test
    void crearPago() {
        Pedido pedido = new Pedido(1L, null,LocalDateTime.now(), PedidoStatus.PENDIENTE, null, null, null);
        
        PagoToSaveDto pagoToSaveDto = new PagoToSaveDto(pedido.getId(), 20000D, MetodoDePago.DAVIPLATA);

        when(this.pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));

        Pago pagoSaved = new Pago(1L, pedido, pagoToSaveDto.totalPago(), LocalDateTime.now(), pagoToSaveDto.metodoDePago());

        when(this.pagoRepository.save(any(Pago.class))).thenReturn(pagoSaved);

        PagoDto pago = this.pagoService.crearPago(pagoToSaveDto);

        assertThat(pago).isNotNull();
        assertThat(pago.pedido_id()).isEqualTo(1L);

    }

    @Test
    void actualizarPago() {

        Pedido pedido = new Pedido(1L, null,LocalDateTime.now(), PedidoStatus.PENDIENTE, null, null, null);
        when(this.pedidoRepository.findById(anyLong())).thenReturn(Optional.of(pedido));
        
        Pago pago = new Pago(1L, pedido, 2000D, LocalDateTime.now(), MetodoDePago.EFECTIVO);
        
        when(this.pagoRepository.findById(anyLong())).thenReturn(Optional.of(pago));

        when(this.pagoRepository.save(any(Pago.class))).thenAnswer(invocation-> {
            return invocation.getArgument(0);
        } );

        PagoToUpdateDto pagoToUpdae = new PagoToUpdateDto(1L, 3000D, MetodoDePago.NEQUI);

        PagoDto pagoSaved = this.pagoService.actualizarPago(1L, pagoToUpdae);

        assertThat(pagoSaved).isNotNull();
        assertThat(pagoSaved.metodoDePago()).isEqualTo(MetodoDePago.NEQUI);
        assertThat(pagoSaved.totalPago()).isEqualTo(3000D);
    }

    @Test
    void buscarPagoPorId() {
        Pago pago = new Pago(1L, 
                            new Pedido(1L, null, null, null, null, null, null),
                            2000D,
                            LocalDateTime.now(),
                            MetodoDePago.EFECTIVO);
        
        when(this.pagoRepository.findById(anyLong())).thenReturn(Optional.of(pago));

        PagoDto pagoBuscado = this.pagoService.buscarPagoPorId(1L);
        assertThat(pagoBuscado).isNotNull();
    }

    @Test
    void recuperarPagosEnRangoDeFecha() {
        when(this.pagoRepository
        .recuperarPagosEnRangoDeFecha( any(LocalDateTime.class),  any(LocalDateTime.class)))
        .thenReturn(this.listaDePagos());

        List<PagoDto> pagos =  this.pagoService.recuperarPagosEnRangoDeFecha(LocalDateTime.now(), LocalDateTime.now());

        assertThat(pagos).isNotEmpty().hasSize(2);
    }

    @Test
    void recuperarPagosPorPedidoYMetodoDePago() {
        when(this.pagoRepository.recuperarPagosPorPedidoYMetodoDePago(anyLong(), any(MetodoDePago.class)))
        .thenReturn(this.listaDePagos());

        List<PagoDto> pagos =  this.pagoService.recuperarPagosPorPedidoYMetodoDePago(1L, MetodoDePago.DAVIPLATA);

        assertThat(pagos).isNotEmpty().hasSize(2);

    }
}