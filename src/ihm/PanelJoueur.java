package ihm;

import controleur.Controleur;
import metier.Joueur;

import java.awt.*;

import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Controleur ctrl;
	
	private int numJoueur;

	private Image imgPlateau;

	public PanelJoueur(Controleur ctrl, int numJoueur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getJoueur(this.numJoueur).getImage());

		/* Création des composants */
		
		/* Positionnement des composants */
		
		/* Activation des composants */
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgPlateau, 0, 0, this );
	}


}