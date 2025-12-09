package com.galaxy.task.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadisticasDTO {
    private Long total;
    private Long completadas;
    private Long pendientes;
    private Long favoritas;
    private Float porcentajeCompletado;
    private Float porcentajePendiente;
}