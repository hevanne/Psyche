package metier;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

	private List<Image> lstImage = new ArrayList<Image>();
	private boolean finJeu;
	private int numTour;
	private List<Sommet> lstSommet = new ArrayList<Sommet>();

	public Jeu()
	{
	}

	public void lireTheme ()
	{

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
		
	}
}
