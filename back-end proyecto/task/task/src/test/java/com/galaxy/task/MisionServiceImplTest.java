package com.galaxy.task;

import com.galaxy.task.dto.EstadisticasDTO;
import com.galaxy.task.repository.MisionRepository;
import com.galaxy.task.service.impl.MisionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MisionServiceImplTest {

    private MisionRepository misionRepository;
    private MisionServiceImpl misionService;

    @BeforeEach
    void setUp() {
        misionRepository = Mockito.mock(MisionRepository.class);
        misionService = new MisionServiceImpl(misionRepository);
    }

    @Test
    void obtenerEstadisticas_deberiaRetornarCalculosCorrectos() {

        // Mock de datos
        when(misionRepository.count()).thenReturn(10L);
        when(misionRepository.countByCompletada(true)).thenReturn(7L);
        when(misionRepository.countByCompletada(false)).thenReturn(3L);
        when(misionRepository.countByIsFavorita(true)).thenReturn(4L);

        // Ejecutar método
        EstadisticasDTO dto = misionService.obtenerEstadisticas();

        // Validaciones
        assertEquals(10L, dto.getTotal());
        assertEquals(7L, dto.getCompletadas());
        assertEquals(3L, dto.getPendientes());
        assertEquals(4L, dto.getFavoritas());

        assertEquals(0.7f, dto.getPorcentajeCompletado());
        assertEquals(0.3f, dto.getPorcentajePendiente());
    }

    @Test
    void obtenerEstadisticas_conTotalCero_retornaPorcentajesEnCero() {

        when(misionRepository.count()).thenReturn(0L);
        when(misionRepository.countByCompletada(true)).thenReturn(0L);
        when(misionRepository.countByCompletada(false)).thenReturn(0L);
        when(misionRepository.countByIsFavorita(true)).thenReturn(0L);

        EstadisticasDTO dto = misionService.obtenerEstadisticas();

        assertEquals(0L, dto.getTotal());
        assertEquals(0f, dto.getPorcentajeCompletado());
        assertEquals(0f, dto.getPorcentajePendiente());
    }
}
