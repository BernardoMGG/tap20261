package com.example.loja.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequestDTO {

    private String nomeCliente;
    private BigDecimal valorTotal;
}
