package ihm;

import controleur.Controleur;
import metier.Joueur;

import java.awt.Image;

import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private String joueur;

	private Image imgPlateau;

	public PanelJoueur(Controleur ctrl, String joueur)
	{
		this.joueur = joueur;


		this.imgPlateau = getToolkit().getImage(Joueur.getImage());

		/* Création des composants */
		
		/* Positionnement des composants */
		
		/* Activation des composants */
	}


}