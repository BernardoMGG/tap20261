package com.example.copa.controllers;

import com.example.copa.dtos.request.PartidaRequestDTO;
import com.example.copa.dtos.response.PartidaResponseDTO;
import com.example.copa.services.interfaces.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/partidas")
@RequiredArgsConstructor
public class PartidaController {
    private final PartidaService partidaService;

    @PostMapping
    public ResponseEntity<PartidaResponseDTO> criar(@RequestBody PartidaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.criar(dto));
    }

    @GetMapping("/selecao/{nomeSelecao}")
    public ResponseEntity<List<PartidaResponseDTO>> listarPorSelecao(@PathVariable String nomeSelecao) {
        return ResponseEntity.ok(partidaService.listarPorSelecao(nomeSelecao));
    }

    @PostMapping("/{partidaId}/selecoes/{nomeSelecao}")
    public ResponseEntity<PartidaResponseDTO> associarSelecao(
            @PathVariable Long partidaId, @PathVariable String nomeSelecao) {
        return ResponseEntity.ok(partidaService.associarSelecao(partidaId, nomeSelecao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponseDTO> atualizar(@PathVariable Long id, @RequestBody PartidaRequestDTO dto) {
        return ResponseEntity.ok(partidaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        partidaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}