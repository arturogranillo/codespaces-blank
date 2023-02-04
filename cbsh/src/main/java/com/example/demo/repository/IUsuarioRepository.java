package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Usuario;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource()
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);
}