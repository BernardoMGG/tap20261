package com.example.loja.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime dataPedido = LocalDateTime.now();

    // Relacionamento N:M com Produto
    // O lado INVERSO — o lado dono (com @JoinTable) está na entidade Produto
    @ManyToMany(mappedBy = "pedidos")
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", nomeCliente='" + nomeCliente + "', valorTotal=" + valorTotal + "}";
    }
}
