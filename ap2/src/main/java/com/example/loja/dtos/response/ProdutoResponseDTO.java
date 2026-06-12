package com.example.loja.dtos.response;

import com.example.loja.entities.Produto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;

    // Dados embutidos da Categoria (evita expor a entidade diretamente)
    private CategoriaResponseDTO categoria;

    // Lista de pedidos vinculados a este produto
    private List<PedidoResponseDTO> pedidos;

    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .estoque(produto.getEstoque())
                .categoria(CategoriaResponseDTO.fromEntity(produto.getCategoria()))
                .pedidos(produto.getPedidos().stream()
                        .map(PedidoResponseDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
