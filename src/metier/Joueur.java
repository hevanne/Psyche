package metier;

import java.util.List;

public class Joueur {
	private List<Mine>    lstMine     ;
	private int           nbJetons    ;
	private int           nbPiece     ;
	private Ressource[][] tabRessource;
	private int           nbPoint     ;

	public Joueur ()
	{

	}

	public List<Mine>    getMines      () { return this.lstMine   ; }
	public int           getNbJetons   () { return this.nbJetons    ; }
	public int           getNbPieces   () { return this.nbPiece     ; }
	public Ressource[][] getRessources () { return this.tabRessource; }
	public int           getNbPoint    () { return this.nbPoint     ; }

	public boolean ajouterMine (Mine smt)
	{
		return true;
	}

	public boolean ajouterRessource (Ressource res)
	{
		return true;
	}

	public boolean varierNbPoint (int valeur)
	{
		return true;
	}

	public boolean varierNbJeton (int valeur)
	{
		return true;
	}

	public boolean varierNbPiece (int valeur)
	{
		return true;
	}
} 
