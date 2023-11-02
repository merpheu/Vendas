package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Clientes extends JpaRepository <Cliente, Integer> {

  //  List<Cliente> findByNameLike(String nome);
  //  boolean existsByNome (String nome);

}
