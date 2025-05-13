package org.prog5.service;




import org.prog5.model.Location;
import org.prog5.model.ObjetLocationnable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceLocation {
    private final List<Location> locations;

    public ServiceLocation() {
        this.locations = new ArrayList<>();
    }

    public boolean estDisponible(ObjetLocationnable objet, LocalDate date) {
        if (objet == null || date == null) {
            throw new IllegalArgumentException("L'objet ou la date ne peut pas être nul");
        }
        return locations.stream()
                .noneMatch(location -> location.obtenirObjet().obtenirNom().equalsIgnoreCase(objet.obtenirNom())
                        && location.obtenirDate().equals(date));
    }

    public void louerObjet(ObjetLocationnable objet, LocalDate date) {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date est invalide ou dans le passé");
        }
        if (!estDisponible(objet, date)) {
            throw new IllegalStateException("L'objet '" + objet.obtenirNom() + "' est déjà loué pour le " + date);
        }

        locations.add(new Location(objet, date));
        System.out.println("Location confirmée pour '" + objet.obtenirNom() + "' le " + date);
    }
}