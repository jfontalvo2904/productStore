package com.unimagdalena.productStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column( nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;
    
    private String direccion;


    @OneToMany(mappedBy="cliente", cascade=CascadeType.ALL,
            fetch=FetchType.EAGER, orphanRemoval=true)
    List<Pedido> pedidos;
}
