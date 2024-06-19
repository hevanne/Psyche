package ihm;

import controleur.Controleur;

import java.awt.BorderLayout;

import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private Controleur      ctrl;
	private int             largeur, hauteur;
	private PanelJoueur     panelJoueur;
	private PanelJoueurInfo panelJoueurInfo;

	public FrameJoueur(Controleur ctrl, int numJoueur, int largeur, int hauteur)
	{
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;

		/* Création des composants */

		this.panelJoueur     = new PanelJoueur     (ctrl, numJoueur, largeur, hauteur);
		this.panelJoueurInfo = new PanelJoueurInfo (ctrl, numJoueur);

		/* Positionnement des composants */

		this.add( panelJoueurInfo, BorderLayout.NORTH  );
		this.add( panelJoueur    , BorderLayout.CENTER );

		/* Activation des composants */

	}

	public void majInfo()
	{
		this.panelJoueurInfo.majInfo();
	}
}