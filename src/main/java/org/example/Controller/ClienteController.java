package org.example.Controller;

import org.example.domain.entity.Cliente;
import org.example.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/cliente/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getclienteId(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
            return  ResponseEntity.ok(cliente.get());
        }
        return  ResponseEntity.notFound().build();
    }
          @PostMapping("/api/clientes")
          @ResponseBody
         public ResponseEntity saveCli (@RequestBody Cliente cliente){
                Cliente clienteSalvo = clientes.save(cliente);
                return ResponseEntity.ok(clienteSalvo);
         }
         @DeleteMapping("/api/clientes{id}")
         @ResponseBody
         public ResponseEntity deleteCli (@PathVariable Integer id){
           Optional<Cliente> cliente = clientes.findById(id);
             if(cliente.isPresent()){
                 return  ResponseEntity.noContent().build();
             }
             return  ResponseEntity.notFound().build();
         }

         public ResponseEntity update(@PathVariable Integer id,@RequestBody Cliente cliente){
              return clientes.findById(id).map(clientexistente-> {cliente.setId(clientexistente.getId());
                  clientes.save(cliente);
                  return ResponseEntity.noContent().build();
              }).orElseGet( ()-> ResponseEntity.notFound().build());
         }
}
