package metier;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jeu {

	private int numTour;
	private boolean finJeu;
	private List<Joueur> lstJoueurs;
	private List<Sommet> lstSommet;
	private List<Image> lstImage;

	public Jeu()
	{
		this.numTour    = 0;
		this.finJeu     = false;
		this.lstJoueurs = new ArrayList<Joueur>();
		this.lstSommet  = new ArrayList<Sommet>();
		this.lstImage   = new ArrayList<Image>();
		this.lireTheme();
	}

	private void lireTheme ()
	{
		Scanner scFic;
		String  s = "";

		try {
			scFic = new Scanner(new FileInputStream ( "../data/theme.txt" ));

			// Joueur
			/*
			while(scFic.hasNextLine())
			{
				//s = scFic.nextLine();
			}
			*/
			scFic.close();
		} catch (Exception e) {System.out.println(e);}
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
