from typing import Optional

class ObjetLocationnable:
    def __init__(self, nom: Optional[str]):
        if nom is None or not nom.strip():
            raise ValueError("Le nom ne peut pas être vide ou nul")
        self._nom = nom.strip()

    @property
    def nom(self) -> str:
        return self._nom

    def __str__(self) -> str:
        return self._nom