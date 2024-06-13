package metier;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{
	private static int nbJoueurs;

	private int numJoueur;
	private int nbPossessions;
	private int nbPiece;
	private int score;
	private int scoreRoute;

	private String nomJouer;

	private List<Sommet> lstSommet;

	private Ressource[][] tabRessources;

	private int tabScore[];
	
	// Constructeurs
	public Joueur (String nomJouer)
	{
		this.nomJouer      = nomJouer;
		this.numJoueur     = ++Joueur.nbJoueurs;

		this.lstSommet     = new ArrayList<Sommet>();
		this.nbPossessions = 0;
		this.nbPiece       = 0;
		this.score         = 0;
		this.scoreRoute    = 0;

		this.tabRessources = new Ressource[4][8];
		this.tabScore = new int[this.tabRessources[0].length];
	}

	// Accesseurs
	public int getNum           () { return this.numJoueur;     }
	public int getNbPossessions () { return this.nbPossessions; }
	public int getNbPieces      () { return this.nbPiece;       }
	public int getScore         () { return this.score;         }
	
	public String getNom() { return this.nomJouer; }

	public Ressource getRessource (int i, int j)
	{
		return this.tabRessources[i][j];
	}

	// Autres Méthodes
	public void ajouterSommet (Sommet sommet)
	{
		this.lstSommet.add(sommet);
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
								this.tabScore[y]++;
								return true;
							}
						}
					}
				}
				else
				{
					this.tabRessources[0][y] = tmpRessource;
					this.tabScore[y]++;
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
	public void varierScoreRoute(int val)
	{
		if(val > 0) this.scoreRoute += val;
	}

	public void calculerScore ()
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

	public void varierNbPossessions (int nb)
	{
		if(this.nbPossessions + nb >= 0)
			this.nbPossessions += nb;
	}

	public void triTabRessource()
	{
		for (int i=0; i<this.tabScore.length; i++)
		{
			for (int j = 0; j < this.tabScore.length - 1; j++) {
				// Si l'élément actuel est plus grand que l'élément suivant
				if (tabScore[j] > tabScore[j + 1]) {
					// Échange des éléments
					int temp = tabScore[j];
					tabScore[j] = tabScore[j + 1];
					tabScore[j + 1] = temp;
					this.permuter(j, (j+1));
				}
			} 
		}
	}

	private void permuter(int i, int j)
	{
		for (int cpt=0; cpt<this.tabRessources.length; cpt++)
		{
			Ressource tmp = this.tabRessources[cpt][i];
			this.tabRessources[cpt][i] = this.tabRessources[cpt][j];
			this.tabRessources[cpt][j] = tmp;
		}
	}

	public String toString()
	{
		String retour = "";
		String ligne  = "+-----+-----+-----+-----+-----+-----+-----+-----+";
		String tmp    = "";

		for (int i = 3; i >=0; i--)
		{
			retour += ligne + '\n' + '|';
			tmp = "";
			for (int j = 7; j >= 0; j--)
			{
				if (this.tabRessources[i][j] != null)
					tmp += " " + String.format("%3s",this.tabRessources[i][j].getNom().substring(0, 2)) + " |";
				else
					tmp += " XXX |";
			}
			retour += tmp + '\n';
		}
		retour += ligne + "\n";

		for (int i = 0; i < this.tabScore.length; i++)
			retour += this.tabScore[i] + " | ";
		return retour;
	}
}
