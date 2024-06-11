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
		Scanner scFic;
		String  s = "";
		
		try {
			scFic = new Scanner(new FileInputStream ( "../data/theme.txt" ));
			while(scFic.hasNextLine())
			{
				s = scFic.nextLine();
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
