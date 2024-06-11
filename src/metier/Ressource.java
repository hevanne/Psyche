package metier;

public class Ressource implements IRessource
{
	private String nom;
	private Couleur coul;

	public Ressource(String nom, Couleur coul)
	{
		this.nom = nom;
		this.coul = coul;
	}

	public String getNom() { return this.nom; }

	public Couleur getCouleur() { return this.coul; }

	public String toString()
	{
		return "Ressource : " + this.nom + " " + this.coul;
	}

	public IRessource getType()
	{
		return this;
	}
}