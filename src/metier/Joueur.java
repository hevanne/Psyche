package metier;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private static int                   nbJoueurs    ;
	private        int                   numJoueur    ;
	private        String                nom          ;
	private        List<Sommet>          lstSommet    ;
	private        int                   nbPossessions;
	private        int                   nbPiece      ;
	private        Ressource[][] tabRessources;
	private        int                   score        ;

	public Joueur ()
	{
		this.numJoueur = ++Joueur.nbJoueurs;
		this.lstSommet = new ArrayList<Sommet>();
		this.nbPossessions = 0;
		this.nbPiece = 0;
		this.score = 0;
		this.tabRessources = new Ressources[4][8];
	}

	public int           getNbPossessions () { return this.nbPossessions; }
	public int           getNbPieces      () { return this.nbPiece      ; }
	public int           getScore         () { return this.score        ; }

	public Ressource getRessources (int i, int j)
	{
		return this.tabRessources[i][j];
	}

	public boolean ajouterSommet (Sommet sommet)
	{
		this.lstSommet.add(sommet);
		return true;
	}

	public boolean ajouterRessource (IRessource ressource)
	{
		if (r.getType() instanceof Ressource)
		{
			System.out.println("ajout de Ressource");
		}

		if (r.getType() instanceof Piece)
		{
			System.out.println("ajout de Piece");
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

} 
