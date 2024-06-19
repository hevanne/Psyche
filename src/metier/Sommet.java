package metier;

import java.util.ArrayList;
import java.util.List;

public class Sommet
{	
	private static int nbSommet = 0;
	private int num;

	private String nom;
	private int valeur;
	private Couleur couleur;
	private int x;
	private int y;
	private Joueur proprietaire;
	private IRessource ressource;
	private List<Route> lstRoutes = new ArrayList<Route>();


	private Sommet (int valeur, Couleur couleur,int x, int y)
	{
		this.num     = nbSommet++;
		this.valeur  = valeur;
		this.couleur = couleur;

		this.nom     =   String.format("%02d", this.num) 
		               + this.couleur.getNom().charAt(0) 
					   + this.valeur;

		this.x       = x;
		this.y       = y;

		this.proprietaire = null;
		this.ressource = null;
	}

	public static Sommet nvSommet (int valeur, Couleur couleur, int x, int y)
	{
		if (valeur >= 0 && couleur != null && x >= 0 && y >= 0)
			return new Sommet(valeur, couleur, x,y);

		return null;
	}

	// Accesseurs
	public int    getNum () { return this.num; }
	public int    getX   () { return this.x;   }
	public int    getY   () { return this.y;   }
	public String getNom () { return this.nom; }

	public Route getRoute (int    i  ) { return this.lstRoutes.get(i); }
	public Route getRoute (Sommet smt)
	{
		for(Route r : this.lstRoutes)
			if(r.getSmtDep() == smt || r.getSmtArr() == smt) return r;
		return null;
	}

	public int          getValeur       () { return this.valeur;       }
	public Couleur      getCouleur      () { return this.couleur;      }
	public Joueur       getProprietaire () { return this.proprietaire; }
	public IRessource   getRessource    () { return this.ressource;    }

	public List<Sommet> getVoisins      () 
	{
		List<Sommet> retour;
		Route        r;
		Sommet       autreSmt;

		retour = new ArrayList<Sommet>();

		for(int i = 0; i < this.lstRoutes.size(); i++)
		{
			r = this.lstRoutes.get(i);

			if  (r.getSmtDep() == this) 
				autreSmt = r.getSmtArr();
			else
				autreSmt = r.getSmtDep();

			retour.add(autreSmt);

		}

		return retour;
	}

	// Modificateurs
	public boolean setProprietaire(Joueur proprietaire)
	{
		if (    this.proprietaire == null 
		     && proprietaire      != null
			 && proprietaire.ajouterRessource(this.ressource))
		{
			proprietaire.ajouterSommet(this);
			this.proprietaire = proprietaire;
			return true;
		}
		return false;
	}

	public static void resetNum()
	{
		nbSommet = 0;
	}

	public void setRessource(IRessource ressource)
	{
		if (this.proprietaire == null)
			this.ressource = ressource;
	}

	// Ajout d'un joueur invisible au sommet de départ pour éviter des bugs
	public void setDepart()
	{
		if(this.num == 0) this.proprietaire = new Joueur("Départ");
	}

	// Autres Méthodes
	public void    reinit        () { this.proprietaire = null;             }
	public void    ajouterRoute  (Route route) { this.lstRoutes.add(route); }
	public boolean aProprietaire () { return this.proprietaire != null;     }
	public boolean estDepart     () { return this.num == 0;                 }

	public String  toString      () { return this.nom.substring(2);                      }
}
