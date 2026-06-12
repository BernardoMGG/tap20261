package com.example.loja.dtos.response;

import com.example.loja.entities.Pedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoResponseDTO {

    private Long id;
    private String nomeCliente;
    private BigDecimal valorTotal;
    private LocalDateTime dataPedido;

    public static PedidoResponseDTO fromEntity(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .nomeCliente(pedido.getNomeCliente())
                .valorTotal(pedido.getValorTotal())
                .dataPedido(pedido.getDataPedido())
                .build();
    }
}
