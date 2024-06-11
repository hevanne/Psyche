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

	public int getValeur() { return this.valeur; }
	
	public Couleur getCouleur() { return this.couleur; }

	public String toString()
	{
		return "Piece : " + this.valeur + " " + this.couleur;
	}

	public IRessource getType()
	{
		return this;
	}
}