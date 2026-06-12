package com.example.loja.services.interfaces;

import com.example.loja.dtos.request.CategoriaRequestDTO;
import com.example.loja.dtos.response.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    CategoriaResponseDTO criar(CategoriaRequestDTO dto);

    CategoriaResponseDTO buscarPorId(Long id);

    List<CategoriaResponseDTO> listarTodas();

    CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto);

    void deletar(Long id);
}
