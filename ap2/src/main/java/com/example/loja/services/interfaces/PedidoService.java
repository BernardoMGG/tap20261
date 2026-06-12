package com.example.loja.services.interfaces;

import com.example.loja.dtos.request.PedidoRequestDTO;
import com.example.loja.dtos.response.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {

    PedidoResponseDTO criar(PedidoRequestDTO dto);

    PedidoResponseDTO buscarPorId(Long id);

    List<PedidoResponseDTO> listarTodos();

    PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto);

    void deletar(Long id);
}
