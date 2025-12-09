package com.galaxy.task.controller;

import com.galaxy.task.dto.PlanetaDTO;
import com.galaxy.task.service.PlanetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planetas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlanetaController {

    private final PlanetaService planetaService;

    // ===== ENDPOINTS ESPECÍFICOS PRIMERO =====
    
    @GetMapping("/inicializar-datos")
    public ResponseEntity<String> precargarDatos() {
        planetaService.precargarPlanetasIniciales();
        return ResponseEntity.ok("✅ Planetas inicializados correctamente");
    }

    @GetMapping
    public ResponseEntity<List<PlanetaDTO>> obtenerTodos() {
        return ResponseEntity.ok(planetaService.obtenerTodosLosPlanetas());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PlanetaDTO>> obtenerPorTipo(
            @PathVariable("tipo") String tipo
    ) {
        return ResponseEntity.ok(planetaService.obtenerPlanetasPorTipo(tipo));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PlanetaDTO> obtenerPorNombre(
            @PathVariable("nombre") String nombre
    ) {
        return ResponseEntity.ok(planetaService.obtenerPlanetaPorNombre(nombre));
    }

    // ===== ENDPOINTS CON VARIABLES AL FINAL =====

    @GetMapping("/{id}")
    public ResponseEntity<PlanetaDTO> obtenerPorId(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(planetaService.obtenerPlanetaPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanetaDTO> crear(
            @RequestBody PlanetaDTO planetaDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(planetaService.crearPlaneta(planetaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanetaDTO> actualizar(
            @PathVariable("id") Long id,
            @RequestBody PlanetaDTO planetaDTO
    ) {
        return ResponseEntity.ok(planetaService.actualizarPlaneta(id, planetaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable("id") Long id
    ) {
        planetaService.eliminarPlaneta(id);
        return ResponseEntity.noContent().build();
    }
}