package controleur;

import metier.*;
import ihm.*;

public class Controleur
{
	private Jeu jeu;

	private String joueur1;
	private String joueur2;

	private FramePlateau framePlateau;
	private FrameJoueur frameJoueur1;
	private FrameJoueur frameJoueur2;


	public Controleur()
	{
		this.jeu = new Jeu();

		this.framePlateau = new FramePlateau(this);
		this.frameJoueur1 = new FrameJoueur(this, joueur1);
		this.frameJoueur2 = new FrameJoueur(this, joueur2);
	}

	public boolean prendreSommet(Sommet smtDep, Sommet smtArr) { return this.jeu.prendreSommet(smtDep, smtArr); }

	public void tourSuivant()
	{
		if(!this.jeu.estFinJeu())
			this.jeu.tourSuivant();
		else
			this.afficherScore();
	}

	private void afficherScore() { new FrameScore(this); }

	public static void main(String[] args) { new Controleur(); }
}