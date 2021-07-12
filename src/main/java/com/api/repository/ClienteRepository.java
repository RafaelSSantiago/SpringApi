package com.api.repository;

import com.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByPublished(boolean published);

    List<Cliente> findByNomeContaining(String Nome);
}
