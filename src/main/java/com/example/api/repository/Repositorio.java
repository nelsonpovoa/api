package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.modelo.Cliente;

@Repository
public interface Repositorio extends JpaRepository<Cliente, Long>  {

}
