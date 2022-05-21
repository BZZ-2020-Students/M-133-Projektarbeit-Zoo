package dev.muetzilla.m133projektarbeitzoo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GehegeArt {
    TROCKENGEHEGE,
    SUESSWASSERGEHEGE,
    SALZWASSERGEHEGE,
    ABGEDECKTESGEHEGE
}
