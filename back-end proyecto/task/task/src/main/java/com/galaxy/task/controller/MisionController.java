package com.galaxy.task.controller;

import com.galaxy.task.dto.EstadisticasDTO;
import com.galaxy.task.dto.MisionDTO;
import com.galaxy.task.service.MisionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/misiones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MisionController {

    private final MisionService misionService;

    
    @GetMapping
    public ResponseEntity<List<MisionDTO>> obtenerTodas() {
        return ResponseEntity.ok(misionService.obtenerTodasLasMisiones());
    }

    
    @GetMapping("/pendientes")
    public ResponseEntity<List<MisionDTO>> obtenerPendientes() {
        return ResponseEntity.ok(misionService.obtenerMisionesPendientes());
    }

    
    @GetMapping("/completadas")
    public ResponseEntity<List<MisionDTO>> obtenerCompletadas() {
        return ResponseEntity.ok(misionService.obtenerMisionesCompletadas());
    }

    
    @GetMapping("/favoritas")
    public ResponseEntity<List<MisionDTO>> obtenerFavoritas() {
        return ResponseEntity.ok(misionService.obtenerMisionesFavoritas());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<MisionDTO> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(misionService.obtenerMisionPorId(id));
    }

    
    @PostMapping
    public ResponseEntity<MisionDTO> crear(@Valid @RequestBody MisionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(misionService.crearMision(dto));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<MisionDTO> actualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody MisionDTO dto
    ) {
        return ResponseEntity.ok(misionService.actualizarMision(id, dto));
    }

    
    @PatchMapping("/{id}/completada")
    public ResponseEntity<MisionDTO> toggleCompletada(@PathVariable("id") Long id) {
        return ResponseEntity.ok(misionService.toggleCompletada(id));
    }

    
    @PatchMapping("/{id}/favorita")
    public ResponseEntity<MisionDTO> toggleFavorita(@PathVariable("id") Long id) {
        return ResponseEntity.ok(misionService.toggleFavorita(id));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        misionService.eliminarMision(id);
        return ResponseEntity.noContent().build();
    }

    
    @DeleteMapping("/completadas")
    public ResponseEntity<Void> eliminarCompletadas() {
        misionService.eliminarTodasLasCompletadas();
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasDTO> estadisticas() {
        return ResponseEntity.ok(misionService.obtenerEstadisticas());
    }
}
