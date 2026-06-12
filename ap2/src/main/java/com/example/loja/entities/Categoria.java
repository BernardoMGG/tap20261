package com.example.loja.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String descricao;

    // Relacionamento 1:N com Produto
    // mappedBy aponta para o campo "categoria" dentro da entidade Produto
    // cascade = ALL: operações na Categoria propagam para os Produtos
    // orphanRemoval = true: se um Produto for removido da lista, ele é deletado do banco
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    // toString manual para evitar loop infinito com relacionamento bidirecional
    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nome='" + nome + "'}";
    }
}
