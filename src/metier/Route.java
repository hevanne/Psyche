package metier;

public class Route {
	private Sommet smtDep;
	private Sommet smtArr;
	private int    nbSection;
	private Joueur proprietaire;

	// Constructeurs
	private Route (Sommet smtDep, Sommet smtArr,int nbSection)
	{
		this.smtDep       = smtDep;
		this.smtArr       = smtArr;
		this.nbSection    = nbSection;
		this.proprietaire = null;
	}

	// Factory
	public static Route nvRoute(Sommet smtDep, Sommet smtArr,int nbSection)
	{
		Route route = null;

		if ( smtDep    != null   && smtArr != null 
		  && nbSection >= 1      && nbSection <= 2
		  && smtDep    != smtArr )
		{
			route = new Route(smtDep, smtArr, nbSection);
			smtDep.ajouterRoute(route);
			smtArr.ajouterRoute(route);
		}

		return route;
	}

	// Accesseurs
	public int    getNbSection   () { return this.nbSection    ;}
	public Sommet getSmtDep      () { return this.smtDep       ;}
	public Sommet getSmtArr      () { return this.smtArr       ;}
	public Joueur getProprietaire() { return this.proprietaire ;}

	// Modificateurs
	public void setProprietaire(Joueur proprietaire)
	{
		if (this.proprietaire == null && proprietaire != null)
			this.proprietaire = proprietaire;
	}

	// Autres Méthodes
	public boolean aProprietaire() { return this.proprietaire != null; }
	
}
