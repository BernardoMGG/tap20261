package com.example.loja.controllers;

import com.example.loja.dtos.request.PedidoRequestDTO;
import com.example.loja.dtos.response.PedidoResponseDTO;
import com.example.loja.services.interfaces.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    // POST /api/pedidos
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO response = pedidoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/pedidos
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // GET /api/pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    // PUT /api/pedidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizar(id, dto));
    }

    // DELETE /api/pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
