package ihm;

import controleur.Controleur;
import metier.Joueur;

import java.awt.*;

import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Joueur joueur;

	private Image imgPlateau;

	public PanelJoueur(Controleur ctrl, Joueur joueur)
	{
		this.joueur = joueur;

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+joueur.getImage());

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