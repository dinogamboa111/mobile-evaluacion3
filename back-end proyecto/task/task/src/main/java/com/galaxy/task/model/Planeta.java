package com.galaxy.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del planeta es obligatorio")
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false, length = 500)
    private String descripcion;

    @NotNull(message = "La distancia al Sol es obligatoria")
    @Column(name = "distancia_sol", nullable = false)
    private Double distanciaSol;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false, length = 20)
    private String tipo;

    @NotBlank(message = "El color es obligatorio")
    @Column(name = "color_hex", nullable = false, length = 7)
    private String colorHex;

    @NotBlank(message = "La imagen es obligatoria")
    @Column(nullable = false, length = 50)
    private String imagen;
}