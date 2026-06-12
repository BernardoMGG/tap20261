package com.example.loja.services.impl;

import com.example.loja.dtos.request.CategoriaRequestDTO;
import com.example.loja.dtos.response.CategoriaResponseDTO;
import com.example.loja.entities.Categoria;
import com.example.loja.repositories.CategoriaRepository;
import com.example.loja.services.interfaces.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Injeção de dependência via construtor (DIP + Clean Code)
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        // Builder Pattern para instanciar a entidade de forma fluida
        Categoria categoria = Categoria.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build();

        Categoria salva = categoriaRepository.save(categoria);
        return CategoriaResponseDTO.fromEntity(salva);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
        return CategoriaResponseDTO.fromEntity(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria atualizada = categoriaRepository.save(categoria);
        return CategoriaResponseDTO.fromEntity(atualizada);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada com id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
