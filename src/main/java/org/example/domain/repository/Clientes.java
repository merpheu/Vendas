package org.example.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

@Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }
    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }
    @Transactional
    public void deletar(Cliente cliente){
      if(!entityManager.contains(cliente)){
          cliente =entityManager.merge(cliente);
      }
        entityManager.remove(cliente);
    }
    @Transactional
    public void deletar(Integer id){
       Cliente cliente = entityManager.find(Cliente.class, id);
       deletar(cliente);
    }
    @Transactional
    public List<Cliente> buscarPorNome(String nome){
      String jpql= "select c from Cliente c where c.nome =:nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome +"%");
        return query.getResultList();
    }

    @Transactional
    public List<Cliente> obterTodos(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}