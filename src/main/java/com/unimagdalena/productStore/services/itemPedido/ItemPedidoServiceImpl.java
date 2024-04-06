package com.unimagdalena.productStore.services.itemPedido;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoMapperImpl;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToSaveDto;
import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoToUpdateDto;
import com.unimagdalena.productStore.entity.ItemPedido;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.entity.Producto;
import com.unimagdalena.productStore.exceptions.IdNotNullException;
import com.unimagdalena.productStore.exceptions.itemPedido.ItemPedidoCantidadInvalidadException;
import com.unimagdalena.productStore.exceptions.itemPedido.ItemPedidoNotFoundException;
import com.unimagdalena.productStore.exceptions.pedido.PedidoNotFoundException;
import com.unimagdalena.productStore.exceptions.producto.ProductoNotFoundException;
import com.unimagdalena.productStore.repository.ItemPedidoRepository;
import com.unimagdalena.productStore.repository.PedidoRepository;
import com.unimagdalena.productStore.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ItemPedidoMapperImpl itemPedidoMapper;

    public ItemPedidoServiceImpl(ItemPedidoRepository itemPedidoRepository,
                                 PedidoRepository pedidoRepository,
                                 ProductoRepository productoRepository,
                                 ItemPedidoMapperImpl itemPedidoMapper) {

        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.itemPedidoMapper = itemPedidoMapper;
    }

    public Pedido buscarPedido(Long id) {
        return this.pedidoRepository
                .findById(id)
                .orElseThrow(PedidoNotFoundException::new);
    }

    public Producto buscarProducto(Long id) {
        return this.productoRepository
                .findById(id)
                .orElseThrow(ProductoNotFoundException::new);
    }

    public void createValidate(Long pedidoId, Long productoId, Integer cantidad) {
        if(pedidoId == null)
            throw new PedidoNotFoundException("Debes proporcionar un id de pedido");

        if(productoId == null)
            throw new PedidoNotFoundException("Debes proporcionar un id de producto");

        if(cantidad == null)
            throw new ItemPedidoCantidadInvalidadException("Debes proporcionar una cantidad");

        if(cantidad <=  0)
            throw new ItemPedidoCantidadInvalidadException();
    }

    @Override
    public List<ItemPedidoDto> obtenerTodos() {
        List<ItemPedido> itemsPedido = this.itemPedidoRepository.findAll();
        return this.itemPedidoMapper.itemsPedidoToDto(itemsPedido);
    }

    @Override
    public ItemPedidoDto crearItemPedido(ItemPedidoToSaveDto itemPedidoToSaveDto) {

        Long pedidoId = itemPedidoToSaveDto.pedido_id();
        Long productoId = itemPedidoToSaveDto.producto_id();
        Integer cantidad = itemPedidoToSaveDto.cantidad();

        this.createValidate(pedidoId,productoId,cantidad);

        Pedido pedido = this.buscarPedido(pedidoId);
        Producto producto = this.buscarProducto(productoId);

        ItemPedido itemPedido = this.itemPedidoMapper.itemPedidoToSaveToItemPedido(itemPedidoToSaveDto);
        itemPedido.setPedido(pedido);
        itemPedido.setProducto(producto);
        itemPedido.setPrecioUnitario(producto.getPrice());

        ItemPedido itemPedidoSaved  = this.itemPedidoRepository.save(itemPedido);

        return this.itemPedidoMapper.itemPedidoToDto(itemPedidoSaved);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(Long id, ItemPedidoToUpdateDto itemPedidoToUpdateDto) throws ItemPedidoNotFoundException {
        ItemPedido itemPedido = this.buscarItemPedido(id);

        if(itemPedidoToUpdateDto.pedido_id() != null) {
            itemPedido.setPedido(this.buscarPedido(itemPedidoToUpdateDto.pedido_id()));
        }

        if(itemPedidoToUpdateDto.producto_id() != null) {
            itemPedido.setProducto(this.buscarProducto(itemPedidoToUpdateDto.producto_id()));
        }

        Integer cantidad = itemPedidoToUpdateDto.cantidad();
        if(cantidad != null && cantidad <= 0) throw new ItemPedidoCantidadInvalidadException();

        itemPedido.setCantidad(cantidad);

        ItemPedido saved = this.itemPedidoRepository.save(itemPedido);
        return this.itemPedidoMapper.itemPedidoToDto(saved);

    }

    @Override
    public ItemPedidoDto buscarItemPedidoPorId(Long id) throws ItemPedidoNotFoundException {
        return this.itemPedidoMapper.itemPedidoToDto(this.buscarItemPedido(id));
    }

    @Override
    public ItemPedido buscarItemPedido(Long id) throws ItemPedidoNotFoundException {
        return this.itemPedidoRepository.findById(id).orElseThrow(ItemPedidoNotFoundException::new);
    }

    @Override
    public List<ItemPedidoDto> findByPedidoId(Long pedidoId) {
        if(pedidoId == null) throw  new IdNotNullException("El id del pedido no puede ser null");

        return this.itemPedidoMapper
                .itemsPedidoToDto(this.itemPedidoRepository.findByPedidoId(pedidoId));
    }

    @Override
    public List<ItemPedidoDto> findByProductoId(Long productoId) {
        if(productoId == null) throw  new IdNotNullException("El id del producto no puede ser null");

        return this.itemPedidoMapper
                .itemsPedidoToDto(this.itemPedidoRepository.findByProductoId(productoId));
    }

    @Override
    public Float calcularTotalVentasPorProducto(Long productoId) {
        if(productoId == null) throw  new IdNotNullException("El id del producto no puede ser null");

       return this.itemPedidoRepository.calcularTotalVentasPorProducto(productoId);
    }

    @Override
    public void eliminarItemPedido(Long id) throws ItemPedidoNotFoundException {
        ItemPedido itemPedido = this.buscarItemPedido(id);
        this.itemPedidoRepository.delete(itemPedido);
    }
}
