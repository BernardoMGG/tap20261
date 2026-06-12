package com.example.loja.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer estoque;

    // -------------------------------------------------------
    // Relacionamento N:1 com Categoria (lado "muitos")
    // -------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    // -------------------------------------------------------
    // Relacionamento N:M com Pedido
    // Produto é o lado DONO — define a tabela associativa
    // -------------------------------------------------------
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "produto_pedido",                         // tabela associativa
            joinColumns = @JoinColumn(name = "produto_id"),  // FK para este lado
            inverseJoinColumns = @JoinColumn(name = "pedido_id") // FK para Pedido
    )
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    // Métodos auxiliares para manter consistência bidirecional
    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        pedido.getPedidos().add(this);
    }

    public void removerPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
        pedido.getPedidos().remove(this);
    }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco + "}";
    }
}
