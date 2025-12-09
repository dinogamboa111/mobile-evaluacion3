package com.galaxy.task.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galaxy.task.model.enums.NivelUrgencia;
import com.galaxy.task.model.enums.PlanetaCategoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MisionDTO {

    private Long id;

    @NotBlank(message = "El nombre de la misión es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombreMision;

    @NotBlank(message = "El objetivo es obligatorio")
    @Size(min = 10, max = 200, message = "El objetivo debe tener entre 10 y 200 caracteres")
    private String objetivo;

    @NotNull(message = "El planeta es obligatorio")
    private PlanetaCategoria planeta;

    @NotNull(message = "La fecha de lanzamiento es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;

    @NotNull(message = "La hora de lanzamiento es obligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime horaLanzamiento;

    @NotNull(message = "El nivel de urgencia es obligatorio")
    private NivelUrgencia nivelUrgencia;

    @Builder.Default
    private Boolean completada = false;

    @Builder.Default
    private Boolean notificacionActiva = true;

    @Builder.Default
    private Boolean isFavorita = false;
}
