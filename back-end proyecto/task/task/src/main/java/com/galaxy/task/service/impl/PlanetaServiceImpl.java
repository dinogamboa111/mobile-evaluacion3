package com.galaxy.task.service.impl;

import com.galaxy.task.dto.PlanetaDTO;
import com.galaxy.task.exception.ResourceNotFoundException;
import com.galaxy.task.model.Planeta;
import com.galaxy.task.repository.PlanetaRepository;
import com.galaxy.task.service.PlanetaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanetaServiceImpl implements PlanetaService {

    private final PlanetaRepository planetaRepository;

    
    @Override
    @Transactional(readOnly = true)
    public List<PlanetaDTO> obtenerTodosLosPlanetas() {
        return planetaRepository.findAllByOrderByDistanciaSolAsc()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    
    @Override
    @Transactional(readOnly = true)
    public List<PlanetaDTO> obtenerPlanetasPorTipo(String tipo) {
        return planetaRepository.findByTipoOrderByDistanciaSolAsc(tipo)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public PlanetaDTO obtenerPlanetaPorId(Long id) {
        Planeta planeta = planetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Planeta no encontrado con id: " + id
                ));

        return convertirADTO(planeta);
    }


    @Override
    public PlanetaDTO crearPlaneta(PlanetaDTO dto) {
        Planeta planeta = convertirAEntidad(dto);
        Planeta guardado = planetaRepository.save(planeta);
        return convertirADTO(guardado);
    }

    
    @Override
    public PlanetaDTO actualizarPlaneta(Long id, PlanetaDTO dto) {
        Planeta planetaExistente = planetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Planeta no encontrado con id: " + id
                ));

        planetaExistente.setNombre(dto.getNombre());
        planetaExistente.setTipo(dto.getTipo());
        planetaExistente.setDistanciaSol(dto.getDistanciaSol());

        Planeta actualizado = planetaRepository.save(planetaExistente);
        return convertirADTO(actualizado);
    }


    @Override
    public void eliminarPlaneta(Long id) {
        if (!planetaRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Planeta no encontrado con id: " + id
            );
        }
        planetaRepository.deleteById(id);
    }


    private PlanetaDTO convertirADTO(Planeta planeta) {
    return PlanetaDTO.builder()
            .id(planeta.getId())
            .nombre(planeta.getNombre())
            .tipo(planeta.getTipo())
            .distanciaSol(planeta.getDistanciaSol())
            .descripcion(planeta.getDescripcion())      
            .colorHex(planeta.getColorHex())            
            .imagen(planeta.getImagen())                
            .build();
}

    private Planeta convertirAEntidad(PlanetaDTO dto) {
    return Planeta.builder()
            .nombre(dto.getNombre())
            .tipo(dto.getTipo())
            .distanciaSol(dto.getDistanciaSol())
            .descripcion(dto.getDescripcion())          
            .colorHex(dto.getColorHex())                
            .imagen(dto.getImagen())                    
            .build();
}

    @Override
    @Transactional(readOnly = true)
    public PlanetaDTO obtenerPlanetaPorNombre(String nombre) {
    Planeta planeta = planetaRepository.findByNombre(nombre)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Planeta no encontrado con nombre: " + nombre
            ));

    return convertirADTO(planeta);
    }

    @Override
    public void precargarPlanetasIniciales() {

        if (planetaRepository.count() > 0) {
            return; 
        }

        List<Planeta> planetas = List.of(

                Planeta.builder()
                        .nombre("Mercurio")
                        .descripcion("El planeta más cercano al Sol")
                        .distanciaSol(57.9)
                        .tipo("Rocoso")
                        .colorHex("#8C8C8C")
                        .imagen("mercurio")
                        .build(),

                Planeta.builder()
                        .nombre("Venus")
                        .descripcion("El planeta más caliente del sistema solar")
                        .distanciaSol(108.2)
                        .tipo("Rocoso")
                        .colorHex("#FFC649")
                        .imagen("venus")
                        .build(),

                Planeta.builder()
                        .nombre("Tierra")
                        .descripcion("Nuestro hogar en el cosmos")
                        .distanciaSol(149.6)
                        .tipo("Rocoso")
                        .colorHex("#4A90E2")
                        .imagen("tierra")
                        .build(),

                Planeta.builder()
                        .nombre("Marte")
                        .descripcion("El planeta rojo")
                        .distanciaSol(227.9)
                        .tipo("Rocoso")
                        .colorHex("#CD5C5C")
                        .imagen("marte")
                        .build(),

                Planeta.builder()
                        .nombre("Júpiter")
                        .descripcion("El gigante gaseoso")
                        .distanciaSol(778.5)
                        .tipo("Gaseoso")
                        .colorHex("#DAA520")
                        .imagen("jupiter")
                        .build(),

                Planeta.builder()
                        .nombre("Saturno")
                        .descripcion("El señor de los anillos")
                        .distanciaSol(1434.0)
                        .tipo("Gaseoso")
                        .colorHex("#F4E4C1")
                        .imagen("saturno")
                        .build(),

                Planeta.builder()
                        .nombre("Urano")
                        .descripcion("El gigante de hielo")
                        .distanciaSol(2871.0)
                        .tipo("Gaseoso")
                        .colorHex("#4FD0E7")
                        .imagen("urano")
                        .build(),

                Planeta.builder()
                        .nombre("Neptuno")
                        .descripcion("El último planeta")
                        .distanciaSol(4495.0)
                        .tipo("Gaseoso")
                        .colorHex("#4169E1")
                        .imagen("neptuno")
                        .build(),

                Planeta.builder()
                        .nombre("Luna")
                        .descripcion("Satélite natural de la Tierra")
                        .distanciaSol(0.384)
                        .tipo("Satélite")
                        .colorHex("#E0E0E0")
                        .imagen("luna")
                        .build(),

                Planeta.builder()
                        .nombre("Sistema Solar")
                        .descripcion("Todo nuestro sistema planetario")
                        .distanciaSol(0.0)
                        .tipo("Sistema")
                        .colorHex("#1A1A2E")
                        .imagen("sistema_solar")
                        .build()
        );

        planetaRepository.saveAll(planetas);
    }

}
