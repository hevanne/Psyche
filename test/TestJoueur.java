package test;

import metier.*;

public class TestJoueur
{
	public static void main(String[] args)
	{
		Joueur j1 = new Joueur("Joueur");

		j1.ajouterRessource(new Piece(1, new Couleur("BLEU", 255, 255, 255)));

		j1.ajouterRessource(new Ressource("Fer", new Couleur("BLEU", 255, 255, 255)));

		j1.calculerScore();

		System.out.println(j1);
	}
}