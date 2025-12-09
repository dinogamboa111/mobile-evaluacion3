package com.galaxy.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

import com.galaxy.task.model.enums.NivelUrgencia;
import com.galaxy.task.model.enums.PlanetaCategoria;


@Entity
@Table(name = "misiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "nombre_mision", nullable = false, length = 50)
    private String nombreMision;

    @NotBlank
    @Size(min = 10, max = 200)
    @Column(nullable = false, length = 200)
    private String objetivo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PlanetaCategoria planeta;

    
    @NotNull
    @Column(name = "fecha_lanzamiento", nullable = false)
    private LocalDate fechaLanzamiento;

    
    @NotNull
    @Column(name = "hora_lanzamiento", nullable = false)
    private LocalTime horaLanzamiento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_urgencia", nullable = false, length = 20)
    private NivelUrgencia nivelUrgencia;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completada = false;

    @Column(name = "notificacion_activa", nullable = false)
    @Builder.Default
    private Boolean notificacionActiva = true;

    @Column(name = "is_favorita", nullable = false)
    @Builder.Default
    private Boolean isFavorita = false;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    @Builder.Default
    private Instant fechaCreacion = Instant.now();

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = Instant.now();
        }
    }
}
