package org.prog5;


import org.prog5.model.ObjetLocationnable;
import org.prog5.service.ServiceLocation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner LECTEUR = new Scanner(System.in);
    private static final ServiceLocation SERVICE_LOCATION = new ServiceLocation();

    private static final ObjetLocationnable VOITURE = new ObjetLocationnable("voiture");
    private static final ObjetLocationnable TENTE = new ObjetLocationnable("tente");
    private static final ObjetLocationnable VELO = new ObjetLocationnable("vélo");
    public static void main(String[] args) {



            afficherMessageBienvenue();
            executerBouclePrincipale();
        }

        private static void afficherMessageBienvenue() {
            System.out.println("Bienvenue dans le système de location.");
            System.out.println("Objets disponibles : voiture, tente, vélo");
        }

        private static void executerBouclePrincipale() {
            while (true) {
                try {
                    ObjetLocationnable objet = demanderObjet();
                    LocalDate date = demanderDate();
                    SERVICE_LOCATION.louerObjet(objet, date);
                } catch (IllegalArgumentException | IllegalStateException | DateTimeParseException e) {
                    System.out.println("Erreur : " + e.getMessage());
                }

                if (!demanderContinuation()) {
                    System.out.println("Merci d'avoir utilisé l'application.");
                    break;
                }
            }
        }

        private static ObjetLocationnable demanderObjet() {
            System.out.print("Quel objet voulez-vous louer ? ");
            String nom = LECTEUR.nextLine().trim().toLowerCase();

            return switch (nom) {
                case "voiture" -> VOITURE;
                case "tente" -> TENTE;
                case "vélo", "velo" -> VELO;
                default -> throw new IllegalArgumentException("Objet inconnu : " + nom);
            };
        }

        private static LocalDate demanderDate() {
            System.out.print("Entrez la date (AAAA-MM-JJ) : ");
            String dateSaisie = LECTEUR.nextLine().trim();
            return LocalDate.parse(dateSaisie);
        }

        private static boolean demanderContinuation() {
            System.out.print("Voulez-vous effectuer une autre location ? (o/n) : ");
            String reponse = LECTEUR.nextLine().trim().toLowerCase();
            return reponse.equals("o");
        }
}