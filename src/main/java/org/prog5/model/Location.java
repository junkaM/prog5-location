package org.prog5.model;

import java.time.LocalDate;

public class Location {
    private final ObjetLocationnable objet;
    private final LocalDate date;

    public Location(ObjetLocationnable objet, LocalDate date) {
        if (objet == null || date == null) {
            throw new IllegalArgumentException("L'objet ou la date ne peut pas être nul");
        }
        this.objet = objet;
        this.date = date;
    }

    public ObjetLocationnable obtenirObjet() {
        return objet;
    }

    public LocalDate obtenirDate() {
        return date;
    }
}