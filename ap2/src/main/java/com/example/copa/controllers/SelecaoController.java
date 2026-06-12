package com.example.copa.controllers;

import com.example.copa.dtos.request.SelecaoRequestDTO;
import com.example.copa.dtos.response.SelecaoResponseDTO;
import com.example.copa.services.interfaces.SelecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/selecoes")
@RequiredArgsConstructor
public class SelecaoController {
    private final SelecaoService selecaoService;

    @PostMapping
    public ResponseEntity<SelecaoResponseDTO> criar(@RequestBody SelecaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(selecaoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<SelecaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(selecaoService.listarTodas());
    }

    @PutMapping("/{nome}")
    public ResponseEntity<SelecaoResponseDTO> atualizar(@PathVariable String nome, @RequestBody SelecaoRequestDTO dto) {
        return ResponseEntity.ok(selecaoService.atualizar(nome, dto));
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        selecaoService.deletar(nome);
        return ResponseEntity.noContent().build();
    }
}