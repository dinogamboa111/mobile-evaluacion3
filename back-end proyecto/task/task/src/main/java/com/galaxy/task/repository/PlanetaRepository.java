package com.galaxy.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.task.model.Planeta;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {

    Optional<Planeta> findByNombre(String nombre);
    
    List<Planeta> findByTipoOrderByDistanciaSolAsc(String tipo);
    
    List<Planeta> findAllByOrderByDistanciaSolAsc();

}