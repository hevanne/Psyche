package controleur;

import metier.*;
import ihm.*;


import java.util.List;

public class Controleur
{
	private Jeu metier;
	private IHM ihm;
	private FrameVille frameVille;
	private FrameRoute frameRoute;

	// Constructeurs
	public Controleur()
	{

		this.metier = new Jeu(this);
		this.ihm    = new IHM(this);

		this.majIHM();
	}

	public Controleur(int numScenario)
	{

		this.metier = new Jeu(numScenario);
		this.ihm    = new IHM(this);

		this.majIHM();
	}

	// Accesseurs
	public IHM getIHM(){ return this.ihm;}
	public int    getNumTour    () { return this.metier.getNumTour    (); }
	public Joueur getJoueurActif() { return this.metier.getJoueurActif(); }
	public Sommet getDepart     () { return this.metier.getDepart     (); }

	public Joueur getJoueur (int i) { return this.metier.getJoueur(i); }
	public Sommet getSommet (int i) { return this.metier.getSommet(i); }
	public Sommet getSommet (int x, int y) { return this.metier.getSommet(x, y); }
	public String getVocab  (int i) { return this.metier.getVocab(i);  }

	public List<Sommet> getLstSommets () { return this.metier.getLstSommets (); }
	public List<Route>  getLstRoutes  () { return this.metier.getLstRoutes  (); }

	public String getImagePlateauVierge () { return this.metier.getImagePlateauVierge(); }
	public String getImageDepart        () { return this.metier.getImageDepart       (); } 
	public String getImagePiece         () { return this.metier.getImagePiece        ();}
	public String getImagePlateauJoueur (int numJoueur) { return this.metier.getImagePlateauJoueur(numJoueur); } 
	public String getImagePionJoueur    (int numJoueur) { return this.metier.getImagePionJoueur   (numJoueur); } 

	// Autres Méthodes
	public boolean estFinJeu () { return this.metier.estFinJeu(); }
	
	public List<Sommet> prendreSommet (Sommet smtDep, Sommet smtArr) 
	{ 
		return this.metier.prendreSommet(smtDep, smtArr); 
	}

	public void incrementerNumTour () 
	{ 
		if(!this.metier.estFinJeu())
		{
			this.metier.incrementerNumTour();
		}
		else
		{
			this.afficherScore();
		}
		this.majIHM();
	} 

	public void selectionnerEtape () 
	{ 
		new FrameEtape(this, this.ihm.getXPrincipal(), this.ihm.getYPrincipal()); 
	}

	public void selectionnerTrajet (List<List<Sommet>> lstTrajets) 
	{
		new FrameTrajet(this, lstTrajets, this.ihm.getXPrincipal(), this.ihm.getYPrincipal());
	}

	public void affecterPrpRoute(List<Sommet> lstTrajets)
	{
		this.metier.affecterPrpRoute(lstTrajets);
	}

	public void parcourirEtape (int numEtape) 
	{ 
		this.metier.parcourirEtape(numEtape);
		this.majIHM();
	}

	public void ajouterEtape (Sommet smtDep, Sommet smtArr, Integer indiceTrajetChoisi) 
	{ 
		this.metier.ajouterEtape(smtDep, smtArr, indiceTrajetChoisi); 
	}

	public List<List<Sommet>> getTrajets(Sommet smtDep, Sommet smtArr, boolean routePrp) 
	{ 
		return this.metier.getTrajets(smtDep, smtArr, routePrp);
	}

	public void ajouterScoresTrajet(List<Sommet> trajet, Sommet smtFin) 
	{ 
		this.metier.ajouterScoresTrajet(trajet, smtFin); 
	}

	public void afficherScore     () { new FrameScore(this); }

	public void majIHM()
	{
		this.metier.getJoueur(0).triTabRessource();
		this.metier.getJoueur(1).triTabRessource();
		this.ihm.majTout();
	}


	public void setModifier()
	{
		this.frameVille = new FrameVille(this);
		this.frameRoute = new FrameRoute(this);
	}

	public void setJouer()
	{
		if (this.frameVille != null && this.frameRoute != null)
		{
			this.frameVille.dispose();
			this.frameRoute.dispose();
			this.ihm.getFramePlateau().getPanelPlateau().repaint();
		}

	}

	public FrameRoute getFrameRoute()
	{
		return this.frameRoute;
	}

	public void majSommet(List<Sommet> lstS)
	{
		System.out.println("ajout de ville");
		this.metier.ajouterVille(lstS);
		this.ihm.getFramePlateau().getPanelPlateau().repaint();
	}

	public void majRoute(String sommetD, String sommetA, String tronc)
	{
		this.metier.ajouterRoute(sommetD, sommetA, tronc);
		this.ihm.getFramePlateau().getPanelPlateau().repaint();
	}

	public Couleur getCouleur(int i)
	{
		return this.metier.getCouleur( i);
	}

	public void resetNumSommet()
	{
		this.metier.resetNumSommet();
	}
}