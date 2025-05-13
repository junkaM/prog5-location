import { createInterface } from 'readline';
import { ObjetLocationnable } from './model/objet_locationnable.js';
import { ServiceLocation } from './service/service_location.js';
import { parse } from 'date-fns';

class Main {
  static #VOITURE = new ObjetLocationnable("voiture");
  static #TENTE = new ObjetLocationnable("tente");
  static #VELO = new ObjetLocationnable("vélo");
  static #SERVICE_LOCATION = new ServiceLocation();
  static #rl = createInterface({
    input: process.stdin,
    output: process.stdout
  });

  static async executer() {
    await this.#afficherMessageBienvenue();
    await this.#executerBouclePrincipale();
    this.#rl.close();
  }

  static async #afficherMessageBienvenue() {
    console.log("Bienvenue dans le système de location.");
    console.log("Objets disponibles : voiture, tente, vélo");
  }

  static async #executerBouclePrincipale() {
    while (true) {
      try {
        const objet = await this.#demanderObjet();
        const date = await this.#demanderDate();
        this.#SERVICE_LOCATION.louerObjet(objet, date);
      } catch (e) {
        console.log(`Erreur : ${e.message}`);
      }

      if (!(await this.#demanderContinuation())) {
        console.log("Merci d'avoir utilisé l'application.");
        break;
      }
    }
  }

  static #demanderObjet() {
    return new Promise((resolve, reject) => {
      this.#rl.question("Quel objet voulez-vous louer ? ", (nom) => {
        nom = nom.trim().toLowerCase();
        switch (nom) {
          case "voiture":
            resolve(this.#VOITURE);
            break;
          case "tente":
            resolve(this.#TENTE);
            break;
          case "vélo":
          case "velo":
            resolve(this.#VELO);
            break;
          default:
            reject(new Error(`Objet inconnu : ${nom}`));
        }
      });
    });
  }

  static #demanderDate() {
    return new Promise((resolve, reject) => {
      this.#rl.question("Entrez la date (AAAA-MM-JJ) : ", (dateSaisie) => {
        dateSaisie = dateSaisie.trim();
        const parsedDate = parse(dateSaisie, 'yyyy-MM-dd', new Date());
        if (isNaN(parsedDate)) {
          reject(new Error("Format de date invalide, utilisez AAAA-MM-JJ"));
        } else {
          resolve(parsedDate);
        }
      });
    });
  }

  static #demanderContinuation() {
    return new Promise((resolve) => {
      this.#rl.question("Voulez-vous effectuer une autre location ? (o/n) : ", (reponse) => {
        resolve(reponse.trim().toLowerCase() === "o");
      });
    });
  }
}

Main.executer();