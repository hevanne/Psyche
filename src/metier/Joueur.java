package metier;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{
	private static int                   nbJoueurs    ;

	private int numJoueur;
	private int nbPossessions;
	private int nbPiece;
	private int score;

	private String nom;

	private List<Sommet> lstSommet;

	private Ressource[][] tabRessources;
	
	public Joueur (String nom)
	{
		this.nom       = nom;
		this.numJoueur = ++Joueur.nbJoueurs;
		this.lstSommet = new ArrayList<Sommet>();
		this.nbPossessions = 0;
		this.nbPiece = 0;
		this.score = 0;
		this.tabRessources = new Ressource[4][8];
	}

	public int getNbPossessions () { return this.nbPossessions; }
	public int getNbPieces      () { return this.nbPiece      ; }
	public int getScore         () { return this.score        ; }

	public Ressource getRessource (int i, int j)
	{
		return this.tabRessources[i][j];
	}

	public boolean ajouterSommet (Sommet sommet)
	{
		this.lstSommet.add(sommet);
		return true;
	}

	public boolean ajouterRessource (IRessource r)
	{
		if (r.getType() instanceof Ressource)
		{
			Ressource tmpRessource = (Ressource) r.getType();

			for (int y=0; y<this.tabRessources[0].length; y++)
			{
				if (this.tabRessources[0][y] != null)
				{
					if (this.tabRessources[0][y].getNom().equals(tmpRessource.getNom()))
					{
						for (int i=0; i<this.tabRessources.length; i++)
						{
							if (this.tabRessources[i][y] == null)
							{
								this.tabRessources[i][y] = tmpRessource;
								return true;
							}
						}
					}
				}
				else
				{
					this.tabRessources[0][y] = tmpRessource;
					return true;
				}
			}
			return false;
		}

		if (r.getType() instanceof Piece)
		{
			Piece tmpPiece = (Piece) r.getType();

			if (this.nbPiece + tmpPiece.getValeur() <= 8)
			{
				this.nbPiece += tmpPiece.getValeur();
				return true;
			}
			else
			{
				return false;
			}
		}

		return false;
	}

	public void incrementerPiece () { this.nbPiece++; }

	public void CalculerScore ()
	{
		this.score = 0;

		switch (this.nbPiece) 
		{
			case 2  -> this.score += 4;	
			case 3  -> this.score += 9;
			case 4  -> this.score += 16;
			case 5  -> this.score += 25;
			case 6  -> this.score += 36;
			case 7  -> this.score += 49;
			case 8  -> this.score += 64;
			default -> this.score += 0;
		}

		for (int y=0; y<this.tabRessources[0].length; y++)
		{
			int cpt=0;
			for (int x=0; x<tabRessources.length; x++)
			{
				if (tabRessources[x][y] != null)
					cpt++;
			}
			switch (cpt) 
			{
				case 2  -> this.score += 2;	
				case 3  -> this.score += 10;
				case 4  -> this.score += 20;
				default -> this.score += 0;
			}
		}

		for (int x=0; x<tabRessources.length; x++)
		{
			int cpt=0;
			for (int y=0; y<tabRessources[x].length; y++)
			{
				if (tabRessources[x][y] != null)
				{
					cpt++;
				}
					
			}
			switch (cpt) 
			{
				case 2  -> this.score += 2;	
				case 3  -> this.score += 5;
				case 4  -> this.score += 9;
				case 5  -> this.score += 14;
				case 6  -> this.score += 20;
				case 7  -> this.score += 32;
				case 8  -> this.score += 46;
				default -> this.score += 0;
			}
		}
	}

	public boolean varierNbPossessions (int nb)
	{
		this.nbPossessions += nb;
		return true;
	}

	public void triTabRessource()
	{
		for(int i = this.tabRessources.length-1; i > 0; i--)
		{
			for(int j = i; j > this.tabRessources.length-1-i; j--)
			{
				if(this.tabRessources[j].length > this.tabRessources[j-1].length)
				{
					Ressource[] tmp = this.tabRessources[j-1];
					this.tabRessources[j-1] = this.tabRessources[j];
					this.tabRessources[j] = tmp;
				}
			}
		}
	}

	public String toString()
	{
		return "(" + this.numJoueur + ") " + this.nom;
	}
}
