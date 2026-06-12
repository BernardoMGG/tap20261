package com.example.copa.services.interfaces;
import com.example.copa.dtos.request.SelecaoRequestDTO;
import com.example.copa.dtos.response.SelecaoResponseDTO;
import java.util.List;

public interface SelecaoService {
    SelecaoResponseDTO criar(SelecaoRequestDTO dto);
    List<SelecaoResponseDTO> listarTodas();
    SelecaoResponseDTO atualizar(String nome, SelecaoRequestDTO dto);
    void deletar(String nome);
}