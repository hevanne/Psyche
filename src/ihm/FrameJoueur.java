package ihm;

import controleur.Controleur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private Controleur  ctrl;
	private int         largeur, hauteur;
	private PanelJoueur panelJoueur;

	public FrameJoueur(Controleur ctrl, int numJoueur, int largeur, int hauteur)
	{
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;

		/* Création des composants */

		this.panelJoueur = new PanelJoueur(ctrl, numJoueur, largeur, hauteur);

		/* Positionnement des composants */

		this.add(panelJoueur);

		/* Activation des composants */

	}
}