package metier;

public class Ressource implements IRessource
{
	private String  nom;
	private Couleur coul;
	private boolean doubler;

	public Ressource(String nom, Couleur coul, boolean doubler)
	{
		this.nom     = nom;
		this.coul    = coul;
		this.doubler = doubler;
	}

	// Accesseurs
	public char    getType    () { return 'R';          }
	public String  getNom     () { return this.nom;     }
	public Couleur getCouleur () { return this.coul;    }
	public boolean getDoubler () { return this.doubler; }

	// Autres Méthodes
	public String toString()
	{
		return "R : " + this.nom + " " + this.coul;
	}
}