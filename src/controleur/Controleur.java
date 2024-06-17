package controleur;

import metier.*;

import java.util.List;

import ihm.*;

public class Controleur
{
	private Jeu jeu;

	private String joueur1;
	private String joueur2;

	private FramePlateau framePlateau;
	private FrameJoueur frameJoueur1;
	private FrameJoueur frameJoueur2;

	//private CUI cui;

	// Constructeurs
	public Controleur()
	{
		this.jeu = new Jeu();

		this.framePlateau = new FramePlateau(this);

		this.frameJoueur1 = new FrameJoueur(this, this.getJoueur(0).getNom());
		this.frameJoueur2 = new FrameJoueur(this, this.getJoueur(1).getNom());

		//this.cui = new CUI(this);
	}

	// Accesseurs
	public int    getNumTour    () { return this.jeu.getNumTour    (); }
	public Joueur getJoueurActif() { return this.jeu.getJoueurActif(); }

	public Joueur getJoueur (int i)          { return this.jeu.getJoueur(i);       }
	public String getVocab  (int i)          { return this.jeu.getVocab(i);        }
	public Sommet getSommet (String symbole) { return this.jeu.getSommet(symbole); }

	public List<Sommet> getLstSommet() {return this.jeu.getLstSommet();}

	// Autres Méthodes
	public boolean estFinJeu () { return this.jeu.estFinJeu(); }
 	public boolean prendreSommet(Sommet smtDep, Sommet smtArr) { return this.jeu.prendreSommet(smtDep, smtArr); }
	public void incrementerNumTour () { this.jeu.incrementerNumTour(); }

	public void afficherScore() { new FrameScore(this); }
	public List<List<Sommet>> plusCourtsChemins(Sommet smtArr) { return this.jeu.plusCourtsChemins(smtArr);}

	public int[] calculerScoresTrajet(List<Sommet> trajet) { return this.jeu.calculerScoresTrajet(trajet); }
		
}