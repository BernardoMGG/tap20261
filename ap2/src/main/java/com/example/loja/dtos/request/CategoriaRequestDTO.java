package com.example.loja.dtos.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaRequestDTO {

    private String nome;
    private String descricao;
}
