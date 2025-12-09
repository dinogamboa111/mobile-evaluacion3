package com.galaxy.task.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double distanciaSol;
    private String tipo;
    private String colorHex;
    private String imagen;
}