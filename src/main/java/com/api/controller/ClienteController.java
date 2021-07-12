package com.api.controller;

import com.api.model.Cliente;
import com.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin (origins= "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    ClienteRepository Repo;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam (required = false) String nome){
        try{
            List<Cliente> clientes = new ArrayList<Cliente>();

            if(nome == null)
                Repo.findAll().forEach(clientes::add);
            else
                Repo.findByNomeContaining(nome).forEach(clientes::add);

            if(clientes.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return  new ResponseEntity<>(clientes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente>getClienteById(@PathVariable("id") long id){
        Optional<Cliente> clienteData = Repo.findById(id);

        if(clienteData.isPresent()){
            return new ResponseEntity<>(clienteData.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        try{
            Cliente _cliente = Repo
                    .save(new Cliente(cliente.getNome(), cliente.getDescription(), false));
            return  new ResponseEntity<>(_cliente,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteData = Repo.findById(id);

        if (clienteData.isPresent()){
            Cliente _cliente = clienteData.get();
            _cliente.setNome(cliente.getNome());
            _cliente.setDescription(cliente.getDescription());
            _cliente.setPublished(cliente.isPublished());
            return new ResponseEntity<>(Repo.save(_cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") long id){
        try{
            Repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clientes")
    public ResponseEntity<HttpStatus> deleteAllClientes(){
        try {
            Repo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clientes/published")
    public ResponseEntity<List<Cliente>> findByPublished(){
        try {
            List<Cliente> clientes = Repo.findByPublished(true);
            if(clientes.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clientes,  HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
