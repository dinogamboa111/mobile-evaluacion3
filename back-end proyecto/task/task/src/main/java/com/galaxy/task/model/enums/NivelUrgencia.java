package com.galaxy.task.model.enums;

import lombok.Getter;


@Getter
public enum NivelUrgencia {
    CRITICA("Crítica", "🚨", 0xFFFF4444L),
    ALTA("Alta", "⚡", 0xFFFF8800L),
    MEDIA("Media", "🌟", 0xFFFFBB33L),
    BAJA("Baja", "💫", 0xFF00C851L),
    RUTINA("Rutina", "🛸", 0xFF33B5E5L);

    private final String displayName;
    private final String emoji;
    private final Long colorValue;

    NivelUrgencia(String displayName, String emoji, Long colorValue) {
        this.displayName = displayName;
        this.emoji = emoji;
        this.colorValue = colorValue;
    }
}