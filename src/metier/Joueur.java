package metier;

import java.util.ArrayList;
import java.util.List;

public class Joueur
{
	private static int nbJoueurs;

	private int numJoueur;
	private int nbPions;
	private int nbPiece;
	private int score;
	private int scoreRoute;

	private String nomJouer;

	private List<Sommet> lstSommets;

	private Ressource[][] tabRessources;

	private int tabScore[];
	
	// Constructeurs
	public Joueur (String nomJouer)
	{
		this.nomJouer      = nomJouer;
		this.numJoueur     = ++Joueur.nbJoueurs;

		this.lstSommets    = new ArrayList<Sommet>();
		this.tabRessources = new Ressource[4][8];
		this.tabScore      = new int[this.tabRessources[0].length];

		this.initJoueur();
	}

	// Accesseurs
	public int getNum        () { return this.numJoueur;  }
	public int getNbPions    () { return this.nbPions;    }
	public int getNbPieces   () { return this.nbPiece;    }
	public int getScore      () { return this.score;      }
	public int getScoreRoute () { return this.scoreRoute; }

	public String getNom() { return this.nomJouer; }

	public Ressource getRessource (int i, int j) { return this.tabRessources[i][j]; }

	public Ressource[][] getTabRessources () { return this.tabRessources; }

	public List<Sommet> getLstSommets () { return this.lstSommets; }
	public Sommet getSommet (int i) { return this.lstSommets.get(i); }

	// Autres Méthodes
	public void initJoueur()
	{
		this.lstSommets.clear();
		
		this.nbPions    = 25;
		this.nbPiece    = 0;
		this.score      = 0;
		this.scoreRoute = 0;

		for(int i = 0; i < this.tabRessources.length; i++)
			for(int j = 0; j < this.tabRessources.length; j++)
				this.tabRessources[i][j] = null;
		
		for(int i = 0; i < this.tabScore.length; i++)
			this.tabScore[i] = 0;
	}

	public void ajouterSommet (Sommet sommet)
	{
		this.lstSommets.add(sommet);
	}

	public boolean ajouterRessource (IRessource r)
	{
		if (r.getType() == 'R')
		{
			Ressource tmpRessource = (Ressource) r;

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

		if (r.getType() == 'P')
		{
			Piece tmpPiece = (Piece) r;

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

	public int calculerScorePiece()
	{
		int scorePiece = 0;

		switch (this.nbPiece) 
		{
			case 2  -> scorePiece += 4;	
			case 3  -> scorePiece += 9;
			case 4  -> scorePiece += 16;
			case 5  -> scorePiece += 25;
			case 6  -> scorePiece += 36;
			case 7  -> scorePiece += 49;
			case 8  -> scorePiece += 64;
			default -> scorePiece += 0;
		}

		return scorePiece;
	}

	public int calculerScoreColonne()
	{
		int scoreColonne = 0;

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
				case 2  -> scoreColonne += 2;	
				case 3  -> scoreColonne += 10;
				case 4  -> scoreColonne += 20;
				default -> scoreColonne += 0;
			}
		}

		return scoreColonne;
	}

	public int calculerScoreLigne()
	{
		int scoreLigne = 0;

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
				case 2  -> scoreLigne += 2;	
				case 3  -> scoreLigne += 5;
				case 4  -> scoreLigne += 9;
				case 5  -> scoreLigne += 14;
				case 6  -> scoreLigne += 20;
				case 7  -> scoreLigne += 32;
				case 8  -> scoreLigne += 46;
				default -> scoreLigne += 0;
			}
		}

		return scoreLigne;
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

	public void varierNbPions (int nb)
	{
		this.nbPions += nb;
	}

	public void triTabRessource()
	{
		int[] resourceCounts = new int[tabRessources[0].length];

		// Calculer le nombre de ressources dans chaque colonne
		for (int j = 0; j < tabRessources[0].length; j++)
		{
			int count = 0;
			for (int i = 0; i < tabRessources.length; i++)
			{
				if (tabRessources[i][j] != null)
					count++;
			}
			resourceCounts[j] = count;
		}

		// Tri à bulles des colonnes en fonction du nombre de ressources
		boolean swapped;
		for (int i = 0; i < resourceCounts.length - 1; i++)
		{
			swapped = false;
			for (int j = 0; j < resourceCounts.length - 1 - i; j++)
			{
				if (resourceCounts[j] < resourceCounts[j + 1])
				{
					// Échange des compteurs
					int tempCount = resourceCounts[j];
					resourceCounts[j] = resourceCounts[j + 1];
					resourceCounts[j + 1] = tempCount;
					// Échange des colonnes correspondantes dans tabRessources
					permuter(j, j + 1);
					swapped = true;
				}
			}
			if (!swapped) break;
		}
	}

	private void permuter(int i, int j)
	{
		for (int cpt = 0; cpt < tabRessources.length; cpt++)
		{
			Ressource tmp = tabRessources[cpt][i];
			tabRessources[cpt][i] = tabRessources[cpt][j];
			tabRessources[cpt][j] = tmp;
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
	
	public int getHauteRessource(String nomCouleur)
	{
		int res = 0;

		for (Sommet smt : this.lstSommets)
		{
			if (smt.getCouleur().getNom().equals(nomCouleur))
			{
				if (smt.getValeur() > res)
				{
					res = smt.getValeur();
				}
			}
		}
		return res;
	}
}
