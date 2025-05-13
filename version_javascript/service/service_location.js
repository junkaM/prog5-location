import { ObjetLocationnable } from '../model/objet_locationnable.js';
import { Location } from '../model/location.js';
import { isSameDay } from 'date-fns';

export class ServiceLocation {
  #locations = [];

  estDisponible(objet, date) {
    if (!(objet instanceof ObjetLocationnable) || !date) {
      throw new Error("L'objet ou la date ne peut pas être nul");
    }
    return !this.#locations.some(
      (location) =>
        location.objet.nom.toLowerCase() === objet.nom.toLowerCase() &&
        isSameDay(location.date, date)
    );
  }

  louerObjet(objet, date) {
    const today = new Date();
    if (!date || date < today) {
      throw new Error("La date est invalide ou dans le passé");
    }
    if (!this.estDisponible(objet, date)) {
      throw new Error(`L'objet '${objet.nom}' est déjà loué pour le ${date.toISOString().split('T')[0]}`);
    }

    this.#locations.push(new Location(objet, date));
    console.log(`Location confirmée pour '${objet.nom}' le ${date.toISOString().split('T')[0]}`);
  }
}