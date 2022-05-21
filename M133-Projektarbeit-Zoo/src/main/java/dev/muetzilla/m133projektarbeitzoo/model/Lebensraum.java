package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Lebensraum {
    JUNGLE,
    SAVANNE,
    OZEAN,
    MISCHWALD,
    TUNDRA,
    SEE
}
