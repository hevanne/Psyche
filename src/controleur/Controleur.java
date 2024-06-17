package controleur;

import metier.*;

import java.util.List;

import ihm.*;

public class Controleur
{
	private Jeu jeu;
	
	private FramePlateau framePlateau;
	private FrameJoueur frameJoueur1;
	private FrameJoueur frameJoueur2;

	//private CUI cui;

	// Constructeurs
	public Controleur()
	{
		this.jeu = new Jeu();

		this.framePlateau = new FramePlateau(this);

		this.frameJoueur1 = new FrameJoueur(this, 0);
		this.frameJoueur2 = new FrameJoueur(this, 1);

		//this.cui = new CUI(this);
	}

	// Accesseurs
	public int    getNumTour    () { return this.jeu.getNumTour    (); }
	public Joueur getJoueurActif() { return this.jeu.getJoueurActif(); }

	public Joueur getJoueur (int i) { return this.jeu.getJoueur(i); }
	public Sommet getSommet (int i) { return this.jeu.getSommet(i); }
	public Sommet getSommet (int x, int y) { return this.jeu.getSommet(x, y); }
	public String getVocab  (int i) { return this.jeu.getVocab(i);  }

	public List<Sommet> getLstSommets () { return this.jeu.getLstSommets (); }
	public List<Route>  getLstRoutes  () { return this.jeu.getLstRoutes  (); }

	public String getImagePlateauVierge () { return this.jeu.getImagePlateauVierge(); }


	// Autres Méthodes
	public boolean estFinJeu () { return this.jeu.estFinJeu(); }

 	public boolean prendreSommet(Sommet smtDep, Sommet smtArr) { return this.jeu.prendreSommet(smtDep, smtArr); }

	public void incrementerNumTour () { this.jeu.incrementerNumTour(); }

	public void afficherScore() { new FrameScore(this); }

	public List<List<Sommet>> plusCourtsChemins(Sommet smtArr) { return this.jeu.plusCourtsChemins(smtArr);}

	public int[] calculerScoresTrajet(List<Sommet> trajet) { return this.jeu.calculerScoresTrajet(trajet); }

	public void parcourirEtape (int etape) 
	{ 
		this.jeu.parcourirEtape(etape); 
		this.majIHM();
	}
	public void ajouterEtape   (Sommet smtDep, Sommet smtArr, Integer indiceTrajetChoisi) 
	{ 
		this.jeu.ajouterEtape(smtDep, smtArr, indiceTrajetChoisi); 
	}

	public void majIHM()
	{
		this.frameJoueur1.repaint();
		this.frameJoueur2.repaint();
		this.framePlateau.repaint();
	}
}