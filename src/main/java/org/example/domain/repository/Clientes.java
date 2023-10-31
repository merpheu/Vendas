package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Clientes extends JpaRepository <Cliente, Integer> {
    List<Cliente> findbyNameLike(String name);
    List<Cliente> findbyNameorId (String name, String id);

    boolean existsByNome(String nome);
}
