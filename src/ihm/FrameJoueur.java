package ihm;

import controleur.Controleur;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private Controleur ctrl;
	private PanelJoueur pnlJoueur;

	public FrameJoueur(Controleur ctrl, String joueur)
	{
		this.setTitle(joueur);
		this.setLocation(50,50);
		this.setSize(553, 397);

		/* Création des composants */

		this.pnlJoueur = new PanelJoueur(ctrl, joueur);

		/* Positionnement des composants */

		this.add(pnlJoueur);

		/* Activation des composants */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}