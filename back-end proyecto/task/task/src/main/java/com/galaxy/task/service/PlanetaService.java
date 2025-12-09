package com.galaxy.task.service;

import java.util.List;

import com.galaxy.task.dto.PlanetaDTO;

public interface PlanetaService {
    
    List<PlanetaDTO> obtenerTodosLosPlanetas();
    
    List<PlanetaDTO> obtenerPlanetasPorTipo(String tipo);
    
    PlanetaDTO obtenerPlanetaPorId(Long id);
    
    PlanetaDTO obtenerPlanetaPorNombre(String nombre);
    
    PlanetaDTO crearPlaneta(PlanetaDTO planetaDTO);
    
    PlanetaDTO actualizarPlaneta(Long id, PlanetaDTO planetaDTO);
    
    void eliminarPlaneta(Long id);
    
    void precargarPlanetasIniciales();
}