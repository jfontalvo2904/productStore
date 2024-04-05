package com.unimagdalena.productStore.services.pago;

import com.unimagdalena.productStore.dto.pago.*;
import com.unimagdalena.productStore.entity.Pago;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import com.unimagdalena.productStore.exceptions.IdNotNullException;
import com.unimagdalena.productStore.exceptions.pago.MetodoPagoNotValidException;
import com.unimagdalena.productStore.exceptions.pago.PagoNotFoundException;
import com.unimagdalena.productStore.exceptions.pago.TotalPagoNotValidException;
import com.unimagdalena.productStore.exceptions.pedido.PedidoNotFoundException;
import com.unimagdalena.productStore.repository.PagoRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagoMapperImpl pagoMapper;

    public PagoServiceImpl(PagoRepository pagoRepository, PedidoRepository pedidoRepository, PagoMapperImpl pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagoMapper = pagoMapper;
    }

    Pedido buscarPedido(Long id) {
        return this.pedidoRepository.findById(id).orElseThrow(PedidoNotFoundException::new);
    }

    Pago buscarPago(Long id) {
        return this.pagoRepository.findById(id).orElseThrow(PagoNotFoundException::new);
    }

    @Override
    public List<PagoDto> obtenerTodos() {
        return this.pagoMapper.pagosToDTO(this.pagoRepository.findAll());
    }

    @Override
    public PagoDto crearPago(PagoToSaveDto pagoToSaveDto) {
        if(pagoToSaveDto.pedido_id() == null) throw new IdNotNullException("El id del pedido no puede ser null");

        Pedido pedido = this.buscarPedido(pagoToSaveDto.pedido_id());

        Double totalPago = pagoToSaveDto.totalPago();

        if( totalPago == null || totalPago <= 0) throw new TotalPagoNotValidException("Ingresa un valor valido para el pago" );

        if(pagoToSaveDto.metodoDePago() == null) throw  new MetodoPagoNotValidException();

        Pago pago = this.pagoMapper.pagoToSaveToPago(pagoToSaveDto);
        pago.setPedido(pedido);
        Pago saved = this.pagoRepository.save(pago);

        return this.pagoMapper.pagoToDTO(saved);
    }

    @Override
    public PagoDto actualizarPago( Long id,PagoToUpdateDto pagoToUpdateDto) {
        if(id == null) throw new IdNotNullException("El id del pago");

        Pago pago = this.buscarPago(id);
        Pedido pedido;

        if(pagoToUpdateDto.pedido_id() != null ) {
           pedido = this.buscarPedido(pagoToUpdateDto.pedido_id());
           pago.setPedido(pedido);
        }

        if(pagoToUpdateDto.totalPago() != null && pagoToUpdateDto.totalPago() > 0) {
            pago.setTotalPago(pagoToUpdateDto.totalPago());
        }

        if(pagoToUpdateDto.metodoDePago() != null) {
            pago.setMetodoDePago(pagoToUpdateDto.metodoDePago());
        }

        Pago pagoSaved = this.pagoRepository.save(pago);

        return this.pagoMapper.pagoToDTO(pagoSaved);
    }


    @Override
    public PagoDto buscarPagoPorId(Long id) throws PagoNotFoundException {
        return this.pagoMapper.pagoToDTO(this.buscarPago(id));
    }

    @Override
    public void eliminarPago(Long id) throws PagoNotFoundException {
        if(id == null) throw new IdNotNullException("El id del pago no puede ser null");

        Pago pago = this.buscarPago(id);
        this.pagoRepository.delete(pago);
    }

    @Override
    public List<PagoDto> recuperarPagosEnRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Pago> pagos = this.pagoRepository.recuperarPagosEnRangoDeFecha(fechaInicio, fechaFin);
        return this.pagoMapper.pagosToDTO(pagos);
    }

    @Override
    public List<PagoDto> recuperarPagosPorPedidoYMetodoDePago(Long pedidoId, MetodoDePago metodoDePago) {
        if(pedidoId == null) throw new IdNotNullException("El id del pago no puede ser null");

        List<Pago> pagos = this.pagoRepository.recuperarPagosPorPedidoYMetodoDePago(pedidoId,metodoDePago);
        return this.pagoMapper.pagosToDTO(pagos);
    }
}
