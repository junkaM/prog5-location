from typing import Optional
from datetime import date
from model.objet_locationnable import ObjetLocationnable
from service.service_location import ServiceLocation

class Main:
    _VOITURE = ObjetLocationnable("voiture")
    _TENTE = ObjetLocationnable("tente")
    _VELO = ObjetLocationnable("vélo")
    _SERVICE_LOCATION = ServiceLocation()

    @classmethod
    def executer(cls) -> None:
        cls._afficher_message_bienvenue()
        cls._executer_boucle_principale()

    @classmethod
    def _afficher_message_bienvenue(cls) -> None:
        print("Bienvenue dans le système de location.")
        print("Objets disponibles : voiture, tente, vélo")

    @classmethod
    def _executer_boucle_principale(cls) -> None:
        while True:
            try:
                objet = cls._demander_objet()
                date = cls._demander_date()
                cls._SERVICE_LOCATION.louer_objet(objet, date)
            except (ValueError, RuntimeError) as e:
                print(f"Erreur : {e}")

            if not cls._demander_continuation():
                print("Merci d'avoir utilisé l'application.")
                break

    @classmethod
    def _demander_objet(cls) -> ObjetLocationnable:
        nom = input("Quel objet voulez-vous louer ? ").strip().lower()
        match nom:
            case "voiture":
                return cls._VOITURE
            case "tente":
                return cls._TENTE
            case "vélo" | "velo":
                return cls._VELO
            case _:
                raise ValueError(f"Objet inconnu : {nom}")

    @classmethod
    def _demander_date(cls) -> date:
        from datetime import datetime
        date_saisie = input("Entrez la date (AAAA-MM-JJ) : ").strip()
        try:
            return datetime.strptime(date_saisie, "%Y-%m-%d").date()
        except ValueError:
            raise ValueError("Format de date invalide, utilisez AAAA-MM-JJ")

    @classmethod
    def _demander_continuation(cls) -> bool:
        reponse = input("Voulez-vous effectuer une autre location ? (o/n) : ").strip().lower()
        return reponse == "o"

if __name__ == "__main__":
    Main.executer()