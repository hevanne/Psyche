package ihm;

import controleur.Controleur;

import java.awt.*;
import javax.swing.*;

public class FramePlateau extends JFrame
{
	private Controleur   ctrl;
	private int          largeur, hauteur;

	private PanelPlateau panelPlateau;
	private JPanel       panelPlateauInfo;
	private JLabel       lblNumTour, lblJoueurActif;

	public FramePlateau(Controleur ctrl, int largeur, int hauteur, boolean bool)
	{    
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.setJMenuBar( new BarreMenu(this.ctrl) );

		/* Création des composants */

		this.panelPlateau     = new PanelPlateau(ctrl, this.largeur, this.hauteur);
		this.panelPlateauInfo = new JPanel      (new GridLayout(1, 4, 5, 5));

		this.lblNumTour       = new JLabel( ""+this.ctrl.getNumTour()              );
		this.lblJoueurActif   = new JLabel( ""+this.ctrl.getJoueurActif().getNom() );

		/* Positionnement des composants */

		this.add( this.panelPlateauInfo, BorderLayout.NORTH  );
		this.add( this.panelPlateau    , BorderLayout.CENTER );

		this.panelPlateauInfo.add( new JLabel("Num Tour : ", SwingConstants.RIGHT) );
		this.panelPlateauInfo.add( this.lblNumTour);
		this.panelPlateauInfo.add( new JLabel("Joueur actif : ",     SwingConstants.RIGHT) );
		this.panelPlateauInfo.add( this.lblJoueurActif);

		/* Activation des composants */

	}

	public void majInfo()
	{
		this.lblNumTour.setText     ( ""+this.ctrl.getNumTour()              );
		this.lblJoueurActif.setText ( ""+this.ctrl.getJoueurActif().getNom() );
	}

	public PanelPlateau getPanelPlateau()
	{
		return this.panelPlateau;
	}
}