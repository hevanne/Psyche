package ihm;

import controleur.Controleur;
import metier.Joueur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private Controleur ctrl;
	private PanelJoueur pnlJoueur;

	public FrameJoueur(Controleur ctrl, Joueur joueur)
	{
		this.setTitle(joueur.getNom());
		this.setLocation(50,50);
		this.setSize(553, 435);

		/* Création des composants */

		this.pnlJoueur = new PanelJoueur(ctrl, joueur);

		/* Positionnement des composants */

		this.add(pnlJoueur);

		/* Activation des composants */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}