package com.example.loja.dtos.response;

import com.example.loja.entities.Categoria;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;

    // Factory method — converte Entidade → DTO usando Builder
    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }
}
