package com.galaxy.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.task.dto.EstadisticasDTO;
import com.galaxy.task.dto.MisionDTO;
import com.galaxy.task.exception.ResourceNotFoundException;
import com.galaxy.task.model.Mision;
import com.galaxy.task.model.enums.NivelUrgencia;
import com.galaxy.task.model.enums.PlanetaCategoria;
import com.galaxy.task.repository.MisionRepository;
import com.galaxy.task.service.MisionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MisionServiceImpl implements MisionService {

    private final MisionRepository misionRepository;



    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerTodasLasMisiones() {
        return misionRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesPendientes() {
        return misionRepository.findMisionesPendientes().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesCompletadas() {
        return misionRepository.findByCompletadaOrderByFechaLanzamientoAsc(true).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesFavoritas() {
        return misionRepository.findByIsFavoritaOrderByFechaLanzamientoAsc(true).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesFavoritasPendientes() {
        return misionRepository.findMisionesFavoritasPendientes().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesPorPlaneta(PlanetaCategoria planeta) {
        return misionRepository.findByPlanetaOrderByFechaLanzamientoAsc(planeta).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MisionDTO> obtenerMisionesPorUrgencia(NivelUrgencia urgencia) {
        return misionRepository.findByNivelUrgenciaOrderByFechaLanzamientoAsc(urgencia).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }



    @Override
    @Transactional(readOnly = true)
    public MisionDTO obtenerMisionPorId(Long id) {
        Mision mision = misionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Misión no encontrada con id: " + id));
        return convertirADTO(mision);
    }

    @Override
    public MisionDTO crearMision(MisionDTO misionDTO) {
    Mision mision = convertirAEntidad(misionDTO);
    System.out.println("Guardando misión: " + mision);
    Mision misionGuardada = misionRepository.save(mision);
    return convertirADTO(misionGuardada);
}

    @Override
    public MisionDTO actualizarMision(Long id, MisionDTO misionDTO) {
        Mision misionExistente = misionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Misión no encontrada con id: " + id));

        misionExistente.setNombreMision(misionDTO.getNombreMision());
        misionExistente.setObjetivo(misionDTO.getObjetivo());
        misionExistente.setPlaneta(misionDTO.getPlaneta());
        misionExistente.setFechaLanzamiento(misionDTO.getFechaLanzamiento());
        misionExistente.setHoraLanzamiento(misionDTO.getHoraLanzamiento());
        misionExistente.setNivelUrgencia(misionDTO.getNivelUrgencia());
        misionExistente.setCompletada(misionDTO.getCompletada());
        misionExistente.setNotificacionActiva(misionDTO.getNotificacionActiva());
        misionExistente.setIsFavorita(misionDTO.getIsFavorita());

        return convertirADTO(misionRepository.save(misionExistente));
    }

    @Override
    public void eliminarMision(Long id) {
        if (!misionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Misión no encontrada con id: " + id);
        }
        misionRepository.deleteById(id);
    }

    @Override
    public void eliminarTodasLasCompletadas() {
        List<Mision> completadas = misionRepository.findByCompletadaOrderByFechaLanzamientoAsc(true);
        misionRepository.deleteAll(completadas);
    }

    

    @Override
    public MisionDTO toggleCompletada(Long id) {
        Mision mision = misionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Misión no encontrada con id: " + id));

        mision.setCompletada(!mision.getCompletada());
        return convertirADTO(misionRepository.save(mision));
    }

    @Override
    public MisionDTO toggleFavorita(Long id) {
        Mision mision = misionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Misión no encontrada con id: " + id));

        mision.setIsFavorita(!mision.getIsFavorita());
        return convertirADTO(misionRepository.save(mision));
    }

    

    @Override
    @Transactional(readOnly = true)
    public EstadisticasDTO obtenerEstadisticas() {
        Long total = misionRepository.count();
        Long completadas = misionRepository.countByCompletada(true);
        Long pendientes = misionRepository.countByCompletada(false);
        Long favoritas = misionRepository.countByIsFavorita(true);

        Float porcentajeCompletado = total > 0 ? (completadas.floatValue() / total) : 0f;
        Float porcentajePendiente = total > 0 ? (pendientes.floatValue() / total) : 0f;

        return EstadisticasDTO.builder()
                .total(total)
                .completadas(completadas)
                .pendientes(pendientes)
                .favoritas(favoritas)
                .porcentajeCompletado(porcentajeCompletado)
                .porcentajePendiente(porcentajePendiente)
                .build();
    }

    

    private MisionDTO convertirADTO(Mision mision) {
        return MisionDTO.builder()
                .id(mision.getId())
                .nombreMision(mision.getNombreMision())
                .objetivo(mision.getObjetivo())
                .planeta(mision.getPlaneta())
                .fechaLanzamiento(mision.getFechaLanzamiento())
                .horaLanzamiento(mision.getHoraLanzamiento())
                .nivelUrgencia(mision.getNivelUrgencia())
                .completada(mision.getCompletada())
                .notificacionActiva(mision.getNotificacionActiva())
                .isFavorita(mision.getIsFavorita())
                .build();
    }

    private Mision convertirAEntidad(MisionDTO dto) {
        return Mision.builder()
                .nombreMision(dto.getNombreMision())
                .objetivo(dto.getObjetivo())
                .planeta(dto.getPlaneta())
                .fechaLanzamiento(dto.getFechaLanzamiento())
                .horaLanzamiento(dto.getHoraLanzamiento())
                .nivelUrgencia(dto.getNivelUrgencia())
                .completada(dto.getCompletada() != null ? dto.getCompletada() : false)
                .notificacionActiva(dto.getNotificacionActiva() != null ? dto.getNotificacionActiva() : true)
                .isFavorita(dto.getIsFavorita() != null ? dto.getIsFavorita() : false)
                .build();
    }
}
