package com.example.loja.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoRequestDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private Long categoriaId; // referência à Categoria pelo ID
}
