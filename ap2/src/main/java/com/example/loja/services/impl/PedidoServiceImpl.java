package com.example.loja.services.impl;

import com.example.loja.dtos.request.PedidoRequestDTO;
import com.example.loja.dtos.response.PedidoResponseDTO;
import com.example.loja.entities.Pedido;
import com.example.loja.repositories.PedidoRepository;
import com.example.loja.services.interfaces.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO dto) {
        Pedido pedido = Pedido.builder()
                .nomeCliente(dto.getNomeCliente())
                .valorTotal(dto.getValorTotal())
                .dataPedido(LocalDateTime.now())
                .build();

        Pedido salvo = pedidoRepository.save(pedido);
        return PedidoResponseDTO.fromEntity(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));
        return PedidoResponseDTO.fromEntity(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));

        pedido.setNomeCliente(dto.getNomeCliente());
        pedido.setValorTotal(dto.getValorTotal());

        Pedido atualizado = pedidoRepository.save(pedido);
        return PedidoResponseDTO.fromEntity(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
