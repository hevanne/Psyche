package ihm;

import java.awt.*;
import javax.swing.*;

import controleur.Controleur;

public class PanelJoueurInfo extends JPanel
{
	private Controleur ctrl;
	private int        numJoueur;
	private JLabel     lblScoreRoute, lblNbPion;

	public PanelJoueurInfo(Controleur ctrl, int numJoueur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;

		this.setLayout(new GridLayout(1, 4, 5, 5));

		/* Création des composants */

		this.lblScoreRoute = new JLabel(""+this.ctrl.getJoueur(this.numJoueur).getScoreRoute());
		this.lblNbPion     = new JLabel(""+this.ctrl.getJoueur(this.numJoueur).getNbPions());

		/* Positionnement des composants */
		this.add(new JLabel("Score route : ", SwingConstants.RIGHT));
		this.add(this.lblScoreRoute);
		this.add(new JLabel("Nb Pion : ", SwingConstants.RIGHT));
		this.add(this.lblNbPion);
	}

	public void majInfo()
	{
		this.lblScoreRoute.setText ( ""+this.ctrl.getJoueur(this.numJoueur).getScoreRoute() );
		this.lblNbPion.setText     ( ""+this.ctrl.getJoueur(this.numJoueur).getNbPions()    );
	}
}
