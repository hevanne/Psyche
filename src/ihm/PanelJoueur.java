package ihm;

import controleur.Controleur;
import metier.Jeu;
import metier.Joueur;
import metier.Ressource;
import metier.Sommet;

import java.awt.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Controleur ctrl;
	private int        numJoueur;
	private int        largeur, hauteur;
	private Image      imgPlateau, imgPiece;

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

		for(int i = 0; i < this.ctrl.getJoueur(numJoueur).getLstSommets().size(); i++)
		{
			Sommet smt = this.ctrl.getJoueur(numJoueur).getSommet(i);
			if(smt != null)
				{
					url = "../theme/distrib_images_2/opaque/" + this.ctrl.getVocab(0) +"_"+ smt.getCouleur().getNom() + ".png";
					img = getToolkit().getImage(url);
					x = 30 + i%6 * 50;
					y = 300 + 60 * (int) Math.floor(i/6);
					g2.drawImage(img, x, y, x+IHM.LARGEUR_SOMMET, y+IHM.HAUTEUR_SOMMET, 0, 0, img.getWidth(this), img.getHeight(this), this);
					g2.setColor(Color.BLACK);
					g2.drawString(""+smt.getValeur(), x+13, y+19);
				}
		}

		for(int i = 0; i < this.ctrl.getJoueur(numJoueur).getTabRessources().length-1; i++)
			for(int j = 0; j < this.ctrl.getJoueur(numJoueur).getTabRessources()[i].length-1; j++)
			{
				Ressource res = this.ctrl.getJoueur(numJoueur).getRessource(i, j);
				if(res != null)
				{
					url = "../theme/distrib_images_2/ressources/" + res.getCouleur().getNom().toUpperCase() + ".png";
					img = getToolkit().getImage(url);
					x = 40  + j * IHM.RAYON_RESSOURCE;
					y = 143 - i * IHM.RAYON_RESSOURCE;
					g2.drawImage(img, x, y, x+IHM.RAYON_RESSOURCE, y+IHM.RAYON_RESSOURCE, 0, 0, img.getWidth(this), img.getHeight(this),  this);
				}
			}
	
		for(int i = 0; i < this.ctrl.getJoueur(this.numJoueur).getNbPieces(); i++)
		{
			x = 40 + IHM.RAYON_RESSOURCE*i;
			y = hauteur - 349;
			g2.drawImage(this.imgPiece, x, y, x+IHM.RAYON_RESSOURCE, y+IHM.RAYON_RESSOURCE, 0, 0, this.imgPiece.getWidth(this), this.imgPiece.getHeight(this),  this);
		}
	}
}