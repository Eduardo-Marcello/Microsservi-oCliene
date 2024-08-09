package br.com.marcello.clientservice.controller;

import br.com.marcello.clientservice.model.Client;
import br.com.marcello.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  buscarPorId(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PostMapping("/save")
    public void salvar(@RequestBody Client client) {
        clientService.save(client);
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Client client) {
        return clientService.update(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  remover(@PathVariable Long id) {
        return clientService.delete(id);
    }
}
