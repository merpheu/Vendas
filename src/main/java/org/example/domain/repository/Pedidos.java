package org.example.domain.repository;

import org.example.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Clientes cliente);

}
