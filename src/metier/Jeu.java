package metier;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jeu {

	private List<Image> lstImage = new ArrayList<Image>();
	private boolean finJeu;
	private int numTour;
	private List<Sommet> lstSommet = new ArrayList<Sommet>();

	public Jeu()
	{
		this.lireTheme();
	}

	private void lireTheme ()
	{
		String[] fichiers = new String[]{ "joueur.txt",
		                                  "couleur.txt",
										  "jeton.txt",
		                                  "map.txt",
										  "image.txt"   };
		Scanner scFic, ScLig;

		// Joueurs
		try {
			scFic = new Scanner(new FileInputStream ( "../data/"+fichiers[0] ));
			while(scFic.hasNextLine())
			{

			}
			scFic.close();
		} catch (Exception e) {}

		// Couleurs
		try {
			scFic = new Scanner(new FileInputStream ( "../data/"+fichiers[1] ));
			while(scFic.hasNextLine())
			{

			}
			scFic.close();
		} catch (Exception e) {}

		// Jetons
		try {
			scFic = new Scanner(new FileInputStream ( "../data/"+fichiers[3] ));
			while(scFic.hasNextLine())
			{

			}
			scFic.close();
		} catch (Exception e) {}

		// Map
		try {
			scFic = new Scanner(new FileInputStream ( "../data/"+fichiers[4] ));
			while(scFic.hasNextLine())
			{

			}
			scFic.close();
		} catch (Exception e) {}

		// Images
		try {
			scFic = new Scanner(new FileInputStream ( "../data/"+fichiers[4] ));
			while(scFic.hasNextLine())
			{

			}
			scFic.close();
		} catch (Exception e) {}
	}

	public void lancerJeu()
	{

		this.finJeu = false;
		this.numTour = 0;

	}

	public boolean estFinJeu()
	{
		for (Sommet s: this.lstSommet)
		{
			if (s.getProprietaire() == null)
			{
				this.finJeu = true;
			}
		}

		return this.finJeu;
	}

	public boolean prendreMine()
	{
		return false;
	}
}
