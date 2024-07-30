package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.modelo.Cliente;
import com.example.api.repository.Repositorio;


@RestController
@CrossOrigin(origins = "*") // Para permitir requisicoes de qualquer origem. é uma segurança do browser
public class Controle {

    @Autowired
    private Repositorio acao;

    
    @PostMapping("/") // Cliente
    public ResponseEntity<?> cadastrar(@RequestBody Cliente c){
        if(c.getNome().equals("")){
            String resposta = "O nome do cliente deve ser informado.";
            return new ResponseEntity<>(resposta, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(c), HttpStatus.CREATED);
        }
        //return acao.save(c);
    }

    @GetMapping("/")
    public Iterable<Cliente> lista(){
        return acao.findAll();
    }

    public Iterable<Cliente> listarTodos() {
		Pageable pageable = PageRequest.of(0, 5);
		return acao.findAll(pageable);
	}
    
    @PutMapping("/")
    public Cliente editar(@RequestBody Cliente c){

        // save faz tambem um salvar como alteracao.
        return acao.save(c);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id){
        acao.deleteById(id);
    }
    
}
