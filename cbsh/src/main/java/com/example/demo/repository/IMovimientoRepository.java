package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movimiento;
import java.util.List;
import java.util.Optional;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findAll();
    
    Movimiento save(Movimiento movimiento);
    
    //Movimiento update(Movimiento movimiento);
    
    void delete(Movimiento movimiento);

    List<Movimiento> findByIdCuenta(Long idCuenta);
}
