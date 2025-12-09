package com.galaxy.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.galaxy.task.model.Mision;
import com.galaxy.task.model.enums.NivelUrgencia;
import com.galaxy.task.model.enums.PlanetaCategoria;

import java.time.Instant;
import java.util.List;


@Repository
public interface MisionRepository extends JpaRepository<Mision, Long> {


    List<Mision> findByCompletadaOrderByFechaLanzamientoAsc(Boolean completada);
    
    List<Mision> findByIsFavoritaOrderByFechaLanzamientoAsc(Boolean isFavorita);
    
    List<Mision> findByPlanetaOrderByFechaLanzamientoAsc(PlanetaCategoria planeta);
    
    List<Mision> findByNivelUrgenciaOrderByFechaLanzamientoAsc(NivelUrgencia nivelUrgencia);
    
    List<Mision> findByFechaLanzamientoBetweenOrderByFechaLanzamientoAsc(
        Instant fechaInicio, 
        Instant fechaFin
    );

    
    @Query("SELECT m FROM Mision m WHERE m.completada = false ORDER BY m.fechaLanzamiento ASC")
    List<Mision> findMisionesPendientes();

    @Query("SELECT m FROM Mision m WHERE m.isFavorita = true AND m.completada = false " +
            "ORDER BY m.fechaLanzamiento ASC")
    List<Mision> findMisionesFavoritasPendientes();

    @Query("SELECT m FROM Mision m WHERE m.completada = false " +
            "ORDER BY m.fechaLanzamiento ASC LIMIT :limite")
    List<Mision> findProximasMisiones(int limite);

    
    Long countByCompletada(Boolean completada);
    
    Long countByIsFavorita(Boolean isFavorita);
}