package metier;

public class Piece implements IRessource
{
	private int valeur;
	private Couleur couleur;

	public Piece(int valeur, Couleur coul)
	{
		this.valeur = valeur;
		this.couleur = coul;
	}

	// Accesseurs
	public char    getType    () { return 'P';          }
	public int     getValeur  () { return this.valeur;  }
	public Couleur getCouleur () { return this.couleur; }

	// Autres Méthodes
	public String toString()
	{
		return "P : " + this.valeur + " " + this.couleur;
	}
}