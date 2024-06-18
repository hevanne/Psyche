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

		this.metier = new Jeu();
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
	
	public List<List<Sommet>> prendreSommet (Sommet smtDep, Sommet smtArr) 
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

	public void selectionnerTrajet (List<List<Sommet>> lstTrajets) 
	{
		new FrameTrajet(this, lstTrajets);
	}

	public void affecterPrpRoute(List<Sommet> lstTrajets)
	{
		this.metier.affecterPrpRoute(lstTrajets);
	}

	public void parcourirEtape (int etape) 
	{ 
		this.metier.parcourirEtape(etape); 
		this.majIHM();
	}

	public void ajouterEtape (Sommet smtDep, Sommet smtArr, Integer indiceTrajetChoisi) 
	{ 
		this.metier.ajouterEtape(smtDep, smtArr, indiceTrajetChoisi); 
	}

	public List<List<Sommet>> plusCourtsTrajets(Sommet smtDep, Sommet smtArr) 
	{ 
		return this.metier.plusCourtsTrajets(smtDep, smtArr);
	}

	public List<List<Sommet>> trajetsSommetDepart(Sommet smt) 
	{ 
		return this.metier.trajetsSommetDepart(smt);
	}

	public void ajouterScoresTrajet(List<Sommet> trajet) 
	{ 
		this.metier.ajouterScoresTrajet(trajet); 
	}

	public void afficherScore     () { new FrameScore(this); }
	public void selectionnerEtape () { new FrameEtape(this); }

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
			this.frameRoute.dispose();
			this.frameVille.dispose();

	}

	public void majSommet(int num, int val, int coul, int x, int y)
	{
		System.out.println("ajout de ville num : " + num);
		this.metier.ajouterVille(num, val, coul, x, y);
		
	}
}