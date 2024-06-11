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

			// Joueurs
			while(scFic.hasNextLine())
			{
				s = scFic.nextLine();
				if(!"".equals(s) && !"#".equals(s.substring(0,1)) && s.contains("joueur"))
				{
					if(s.contains("\""))
						this.lstJoueurs.add(new Joueur(s.substring(s.indexOf("\"")+1, s.length()-1).trim()));
					else
						this.lstJoueurs.add(new Joueur(s.substring(s.indexOf("=")+1).trim()));
				}
			}
			System.out.println(this.lstJoueurs);
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
