package ihm;

import controleur.Controleur;

import javax.swing.*;

public class FramePlateau extends JFrame
{
	private Controleur   ctrl;
	private int          largeur, hauteur;
	private PanelPlateau panelPlateau;

	public FramePlateau(Controleur ctrl, int largeur, int hauteur)
	{    
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.setJMenuBar( new BarreMenu(ctrl) );

		/* Création des composants */

		this.panelPlateau = new PanelPlateau(ctrl, this.largeur, this.hauteur);

		/* Positionnement des composants */

		this.add(this.panelPlateau);

		/* Activation des composants */

	}
}