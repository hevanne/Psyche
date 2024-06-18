package ihm;

import controleur.Controleur;
import metier.Jeu;
import metier.Joueur;
import metier.Ressource;

import java.awt.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Controleur ctrl;
	private int        numJoueur;
	private int        largeur, hauteur;
	private Image      imgPlateau, imgPiece;


	private static final int ECART_VERTICAL   = 55;
	private static final int ECART_HORIZONTAL = 55;
	private static final int RAYON_RESSOURCE      = 36;

	public PanelJoueur(Controleur ctrl, int numJoueur, int largeur, int hauteur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;
		this.largeur   = largeur;
		this.hauteur   = hauteur;

		this.setLayout(null);

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getImagePlateauJoueur(this.numJoueur));
		this.imgPiece   = getToolkit().getImage("../theme/distrib_images_2/ressources/" + this.ctrl.getImagePiece());
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage (this.imgPlateau, 
		              0, 0, this.largeur, this.hauteur - 300,
		              0, 0, this.imgPlateau.getWidth(null), this.imgPlateau.getHeight(null), 
		              null);

		Image  img;
		String url;
		int    x, y;

		for(int i = 0; i < this.ctrl.getJoueur(numJoueur).getTabRessources().length-1; i++)
			for(int j = 0; j < this.ctrl.getJoueur(numJoueur).getTabRessources()[i].length-1; j++)
			{
				Ressource res = this.ctrl.getJoueur(numJoueur).getRessource(i, j);
				if(res != null)
				{
					url = "../theme/distrib_images_2/ressources/" + res.getCouleur().getNom().toUpperCase() + ".png";
					img = getToolkit().getImage(url);
					x = 40  + j * PanelJoueur.RAYON_RESSOURCE;
					y = 143 - i * PanelJoueur.RAYON_RESSOURCE;
					g2.drawImage(img, x, y, x+PanelJoueur.RAYON_RESSOURCE, y+PanelJoueur.RAYON_RESSOURCE, 0, 0, img.getWidth(this), img.getHeight(this),  this);
				}
			}
	
		for(int i = 0; i < this.ctrl.getJoueur(this.numJoueur).getNbPieces(); i++)
		{
			x = 40 + PanelJoueur.RAYON_RESSOURCE*i;
			y = hauteur - 349;
			g2.drawImage(this.imgPiece, x, y, x+PanelJoueur.RAYON_RESSOURCE, y+PanelJoueur.RAYON_RESSOURCE, 0, 0, this.imgPiece.getWidth(this), this.imgPiece.getHeight(this),  this);
		}
	}
}