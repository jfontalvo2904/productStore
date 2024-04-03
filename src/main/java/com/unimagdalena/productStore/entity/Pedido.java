package com.unimagdalena.productStore.entity;

import com.unimagdalena.productStore.enums.pedido.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPedido;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private DetalleEnvio detalleEnvio;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pago pago;

    @OneToMany(mappedBy="pedido", orphanRemoval=true)
    List<ItemPedido> itemsPedido;
}
