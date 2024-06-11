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

	public Ressource getRessources (int i, int j)
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

			for (int y=0; y<this.tabEpice[0].length; y++)
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

	public int calculerScore ()
	{
		return 0;
	}

	public boolean varierNbPossessions (int nb)
	{
		this.nbPossessions += nb;
		return true;
	}

	public String toString()
	{
		return "(" + this.numJoueur + ") " + this.nom;
	}
}