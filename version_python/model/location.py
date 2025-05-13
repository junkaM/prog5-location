from typing import Optional
from datetime import date
from model.objet_locationnable import ObjetLocationnable

class Location:
    def __init__(self, objet: Optional[ObjetLocationnable], date: Optional[date]):
        if objet is None or date is None:
            raise ValueError("L'objet ou la date ne peut pas être nul")
        self._objet = objet
        self._date = date

    @property
    def objet(self) -> ObjetLocationnable:
        return self._objet

    @property
    def date(self) -> date:
        return self._date