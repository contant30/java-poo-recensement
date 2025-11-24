package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exception.ExceptionVille;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

        int min ;
        int max;

        try {
            min = Integer.parseInt(saisieMin) * 1000;
        } catch (NumberFormatException e) {
            throw new ExceptionVille("Erreur : la population minimum doit être un nombre.");
        }

        try {
            max = Integer.parseInt(saisieMax) * 1000;
        } catch (NumberFormatException e) {
            throw new ExceptionVille("Erreur : la population maximum doit être un nombre.");
        }

        // Vérification min/max valides
        if (min < 0) {
            throw new ExceptionVille("Erreur : la population minimum ne peut pas être négative.");
        }

        if (max < 0) {
            throw new ExceptionVille("Erreur : la population maximum ne peut pas être négative.");
        }

        if (min > max) {
            throw new ExceptionVille("Erreur : la population minimum ne peut pas être supérieure à la population maximum.");
        }

        List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
    }

}
