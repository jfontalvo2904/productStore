package com.unimagdalena.productStore.entity;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(nullable = false)
    private Double totalPago;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    private MetodoDePago metodoDePago;
}
