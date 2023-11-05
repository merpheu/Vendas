package org.example.service.impl.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Cliente;
import org.example.domain.entity.Pedido;
import org.example.domain.entity.Produto;
import org.example.domain.entity.itemPedido;
import org.example.domain.repository.Clientes;
import org.example.domain.repository.ItemsPedido;
import org.example.domain.repository.Pedidos;
import org.example.domain.repository.Produtos;
import org.example.dto.PedidoDTO;
import org.example.excepetion.RegraNegocioException;
import org.example.service.impl.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemsPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);

        pedido.setItens(ItemsPedido);
        return pedido;
    }

    private List<ItemsPedido> converterItems(Pedido pedido, List<ItemsPedido> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items.stream().map( dto -> {
                   Integer idprod = dto.getProduto;
                    Produto produto = produtosRepository
                            .findById(idprod)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idprod
                                    ));

                    itemPedido temPedido = new itemPedido();
                    temPedido.setQuantidade(temPedido.getQuantidade());
                    temPedido.setPedido(pedido);
                    temPedido.setProduto(produto);
                    return temPedido;
                }).collect(Collectors.toList());

    }


}
