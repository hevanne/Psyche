package ihm;

import controleur.Controleur;

import java.awt.*;
import javax.swing.*;

public class FrameJoueur extends JFrame
{
	private Controleur  ctrl;
	private int         numJoueur;
	private int         largeur, hauteur;

	private PanelJoueur panelJoueur;
	private JPanel      panelJoueurInfo;
	private JLabel      lblScoreRoute, lblNbPion;

	public FrameJoueur(Controleur ctrl, int numJoueur, int largeur, int hauteur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;
		this.largeur   = largeur;
		this.hauteur   = hauteur;

		/* Création des composants */
		this.panelJoueur     = new PanelJoueur (this.ctrl, this.numJoueur, this.largeur, this.hauteur);
		this.panelJoueurInfo = new JPanel      (new GridLayout(1, 4, 5, 5));

		this.lblScoreRoute = new JLabel( ""+this.ctrl.getJoueur(this.numJoueur).getScoreRoute() );
		this.lblNbPion     = new JLabel( ""+this.ctrl.getJoueur(this.numJoueur).getNbPions()    );

		/* Positionnement des composants */
		this.add( this.panelJoueurInfo, BorderLayout.NORTH  );
		this.add( this.panelJoueur    , BorderLayout.CENTER );

		this.panelJoueurInfo.add( new JLabel("Score route : ", SwingConstants.RIGHT) );
		this.panelJoueurInfo.add( this.lblScoreRoute);
		this.panelJoueurInfo.add( new JLabel("Nb Pion : ",     SwingConstants.RIGHT) );
		this.panelJoueurInfo.add( this.lblNbPion);

		/* Activation des composants */

	}

	public void majInfo()
	{
		this.lblScoreRoute.setText ( ""+this.ctrl.getJoueur(this.numJoueur).getScoreRoute() );
		this.lblNbPion.setText     ( ""+this.ctrl.getJoueur(this.numJoueur).getNbPions()    );
	}
}