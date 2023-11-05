package org.example.service.impl;

import org.example.domain.entity.Pedido;
import org.example.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
