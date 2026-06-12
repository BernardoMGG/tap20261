package com.example.copa.services.interfaces;
import com.example.copa.dtos.request.JogadorRequestDTO;
import com.example.copa.dtos.response.JogadorResponseDTO;
import java.util.List;

public interface JogadorService {
    JogadorResponseDTO criar(JogadorRequestDTO dto);
    List<JogadorResponseDTO> listarPorSelecao(String nomeSelecao);
    JogadorResponseDTO atualizar(String nome, JogadorRequestDTO dto);
    void deletar(String nome);
}