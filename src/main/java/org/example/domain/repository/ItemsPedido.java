package org.example.domain.repository;

import org.example.domain.entity.itemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository <itemPedido, Integer>{
}
