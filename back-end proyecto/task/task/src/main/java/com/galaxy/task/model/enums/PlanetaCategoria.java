package com.galaxy.task.model.enums;

import lombok.Getter;


@Getter
public enum PlanetaCategoria {
    MERCURIO("Mercurio", "☿️"),
    VENUS("Venus", "♀️"),
    TIERRA("Tierra", "🌍"),
    MARTE("Marte", "♂️"),
    JUPITER("Júpiter", "♃"),
    SATURNO("Saturno", "♄"),
    URANO("Urano", "♅"),
    NEPTUNO("Neptuno", "♆"),
    LUNA("Luna", "🌙"),
    SISTEMA_SOLAR("Sistema Solar", "🌌");

    private final String displayName;
    private final String emoji;

    PlanetaCategoria(String displayName, String emoji) {
        this.displayName = displayName;
        this.emoji = emoji;
    }
}