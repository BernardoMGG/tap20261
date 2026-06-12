package com.example.copa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jogadores")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Jogador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer numeroCamisa;

    @Column(nullable = false)
    private String posicao;

    @Column(nullable = false)
    private Integer idade;

    // Relacionamento N:1 com Selecao
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selecao_id", nullable = false)
    private Selecao selecao;

    @Override
    public String toString() {
        return "Jogador{id=" + id + ", nome='" + nome + "'}";
    }
}