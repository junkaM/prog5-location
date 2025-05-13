export class ObjetLocationnable {
  #nom;

  constructor(nom) {
    if (!nom || !nom.trim()) {
      throw new Error("Le nom ne peut pas être vide ou nul");
    }
    this.#nom = nom.trim();
  }

  get nom() {
    return this.#nom;
  }

  toString() {
    return this.#nom;
  }
}