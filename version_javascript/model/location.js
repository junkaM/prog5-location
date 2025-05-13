import { ObjetLocationnable } from '../model/objet_locationnable.js';

export class Location {
  #objet;
  #date;

  constructor(objet, date) {
    if (!(objet instanceof ObjetLocationnable) || !date) {
      throw new Error("L'objet ou la date ne peut pas être nul");
    }
    this.#objet = objet;
    this.#date = date;
  }

  get objet() {
    return this.#objet;
  }

  get date() {
    return this.#date;
  }
}