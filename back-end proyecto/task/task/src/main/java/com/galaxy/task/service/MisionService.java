package com.galaxy.task.service;

import java.util.List;

import com.galaxy.task.dto.EstadisticasDTO;
import com.galaxy.task.dto.MisionDTO;
import com.galaxy.task.model.enums.NivelUrgencia;
import com.galaxy.task.model.enums.PlanetaCategoria;

public interface MisionService {



    List<MisionDTO> obtenerTodasLasMisiones();

    List<MisionDTO> obtenerMisionesPendientes();

    List<MisionDTO> obtenerMisionesCompletadas();

    List<MisionDTO> obtenerMisionesFavoritas();

    List<MisionDTO> obtenerMisionesFavoritasPendientes();

    List<MisionDTO> obtenerMisionesPorPlaneta(PlanetaCategoria planeta);

    List<MisionDTO> obtenerMisionesPorUrgencia(NivelUrgencia urgencia);



    MisionDTO obtenerMisionPorId(Long id);

    MisionDTO crearMision(MisionDTO misionDTO);

    MisionDTO actualizarMision(Long id, MisionDTO misionDTO);

    void eliminarMision(Long id);

    void eliminarTodasLasCompletadas();




    MisionDTO toggleCompletada(Long id);

    MisionDTO toggleFavorita(Long id);



    EstadisticasDTO obtenerEstadisticas();
}
