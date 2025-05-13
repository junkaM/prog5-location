package org.prog5.model;

public class ObjetLocationnable {
    private final String nom;

    public ObjetLocationnable(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide ou nul");
        }
        this.nom = nom.trim();
    }

    public String obtenirNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}