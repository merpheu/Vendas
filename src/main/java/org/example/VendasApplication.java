package org.example;


import org.example.domain.entity.Cliente;
import org.example.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

public class VendasApplication {
    private final Clientes clientes;

    public VendasApplication(Clientes clientes) {
        this.clientes = clientes;
    }

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando clientes");
            clientes.save(new Cliente());
            clientes.save(new Cliente());

            boolean existe = clientes.existsByNome("xyz");
            System.out.println("existe um cliente com o nome xyz? " + existe);


        };
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }


}
