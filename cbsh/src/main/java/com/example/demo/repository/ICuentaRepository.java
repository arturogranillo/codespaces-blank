package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cuenta;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    
    List<Cuenta> findAll();
    
    Cuenta save(Cuenta cuenta);
    
    // Cuenta update(Cuenta cuenta);
    
    void delete(Cuenta cuenta);

    Optional<Cuenta> findByNumero(String numero);
}

