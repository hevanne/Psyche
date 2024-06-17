package ihm;

import controleur.Controleur;
import metier.Joueur;
import metier.Ressource;

import java.awt.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Controleur ctrl;
	
	private int numJoueur;

	private Image imgPlateau;


	private final int ECART_VERTICAL = 55;
	private final int ECART_HORIZONTAL = 55;

	public PanelJoueur(Controleur ctrl, int numJoueur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;

		this.setLayout(null);

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getJoueur(this.numJoueur).getImage());

		/* Création des composants */
		
		/* Positionnement des composants */
		
		/* Activation des composants */
	}

	public void paintComponent(Graphics g)
	{
		String url = "../theme/distrib_images_2/ressources/";
		String png = ".png";

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgPlateau, 0, 0, this );

		for(int i = 0; i < this.ctrl.getJoueur(numJoueur).getTabRessources().length-1; i++)
			for(int j = 0; j < this.ctrl.getJoueur(numJoueur).getTabRessources()[i].length-1; j++)
			{
				Image img = getToolkit().getImage(url + this.ctrl.getJoueur(numJoueur).getRessource(0,0).getCouleur().getNom().toUpperCase() + png);
				g2.drawImage ( img, 66 + i * ECART_VERTICAL, 220 - j * ECART_HORIZONTAL, this );
			}
	}

	public void majPlateau() { this.repaint(); }
}