from typing import Optional, List
from datetime import date
from model.objet_locationnable import ObjetLocationnable
from model.location import Location

class ServiceLocation:
    def __init__(self):
        self._locations: List[Location] = []

    def est_disponible(self, objet: Optional[ObjetLocationnable], date: Optional[date]) -> bool:
        if objet is None or date is None:
            raise ValueError("L'objet ou la date ne peut pas être nul")
        return not any(
            location.objet.nom.lower() == objet.nom.lower() and location.date == date
            for location in self._locations
        )

    def louer_objet(self, objet: Optional[ObjetLocationnable], date: Optional[date]) -> None:
        from datetime import datetime
        if date is None or date < datetime.now().date():
            raise ValueError("La date est invalide ou dans le passé")
        if not self.est_disponible(objet, date):
            raise RuntimeError(f"L'objet '{objet.nom}' est déjà loué pour le {date}")
        
        self._locations.append(Location(objet, date))
        print(f"Location confirmée pour '{objet.nom}' le {date}")