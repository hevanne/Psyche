package metier;

public class Route {
	private Sommet smtDep;
	private Sommet smtArr;
	private int    nbSection;
	private Joueur proprietaire;

	private Route (Sommet smtDep, Sommet smtArr,int nbSection)
	{
		this.smtDep       = smtDep;
		this.smtArr       = smtArr;
		this.nbSection    = nbSection;
		this.proprietaire = null;
	}

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

	public int    getNbSection   () { return this.nbSection    ;}
	public Sommet getSmtDep      () { return this.smtDep       ;}
	public Sommet getSmtArr      () { return this.smtArr       ;}
	public Joueur getProprietaire() { return this.proprietaire ;}

	public void setProprietaire(Joueur proprietaire)
	{
		if (this.proprietaire == null && proprietaire != null)
			this.proprietaire = proprietaire;
	}
}
