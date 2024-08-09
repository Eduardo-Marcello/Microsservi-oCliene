package br.com.marcello.clientservice.service;

import br.com.marcello.clientservice.ClientServiceApplication;
import br.com.marcello.clientservice.model.Client;
import br.com.marcello.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientServiceApplication clientServiceApplication;

    public ResponseEntity<Object> getAll() {
        if (clientRepository.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clientRepository.findAll());
        }
    }

    public ResponseEntity<Optional<Client>> getById(Long id) {
        if(clientRepository.findById(id).isPresent()){
            return ResponseEntity.ok(clientRepository.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public ResponseEntity<Client> update(Long id, Client client){
        Optional<Client> clientOptional = clientRepository.findById(id);

        if(clientOptional.isPresent()){
            Client clientUpdate = clientOptional.get();
            clientUpdate.setCpf(client.getCpf());
            clientUpdate.setEmail(client.getEmail());
            clientUpdate.setNome(client.getNome());
            clientUpdate.setTelefone(client.getTelefone());
            Client clientUpdated = clientRepository.save(clientUpdate);
            return ResponseEntity.ok(clientUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Client> delete(Long id) {
        Optional<Client> clientExist =  clientRepository.findById(id);
        if (clientExist.isPresent()){
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
