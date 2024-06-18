package ihm;

import controleur.Controleur;
import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;
	private int        largeur, hauteur;
	private Image      imgPlateau, imgDepart;

	public PanelPlateau(Controleur ctrl, int largeur, int hauteur)
	{
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getImagePlateauVierge());
		this.imgDepart  = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getImageDepart());

		this.addMouseListener( new GereSouris() );
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Arial", Font.BOLD, 10));

		g2.drawImage(this.imgPlateau, 0, 0, this.largeur, this.hauteur-25, 15, 15, this.imgPlateau.getWidth(this)-15, this.imgPlateau.getHeight(this)-15, this);

		String       url;
		Image        img;
		Sommet       smt;
		int          x1, y1, x2, y2, mx, my;
		List<Route>  lstRoutes;
		List<Sommet> lstSommets;

		
		lstRoutes = this.ctrl.getLstRoutes();
		g2.setColor(Color.DARK_GRAY);
		g2.setStroke(new BasicStroke(4));

		for (Route r: lstRoutes)
		{
			x1 = r.getSmtDep().getX() + Jeu.LARGEUR_SOMMET/2;
			y1 = r.getSmtDep().getY() + Jeu.HAUTEUR_SOMMET/2;
			x2 = r.getSmtArr().getX() + Jeu.LARGEUR_SOMMET/2;
			y2 = r.getSmtArr().getY() + Jeu.HAUTEUR_SOMMET/2;
			
			g2.fillOval(x1-5, y1-5, 10, 10);
			g2.fillOval(x2-5, y2-5, 10, 10);

			g2.drawLine(x1, y1, x2, y2);

			if (r.getNbSection() == 2)
			{
				mx = (x1+x2)/2;
				my = (y1+y2)/2;
				g2.fillOval(mx-5, my-5, 10, 10);
			}

			if (r.aProprietaire())
			{
				url = "../theme/distrib_images_2/"+this.ctrl.getImagePionJoueur(r.getProprietaire().getNum());
				img = getToolkit().getImage(url);

				mx = (x1+x2)/2;
				my = (y1+y2)/2;

				if (r.getNbSection() == 1)
				{
					g2.drawImage(img, mx-Jeu.RAYON_PION, my-Jeu.RAYON_PION, mx+Jeu.RAYON_PION, my+Jeu.RAYON_PION, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
				}
				else
				{
					x1 = (mx+x1)/2;
					y1 = (my+y1)/2;
					g2.drawImage(img, x1-Jeu.RAYON_PION, y1-Jeu.RAYON_PION, x1+Jeu.RAYON_PION, y1+Jeu.RAYON_PION, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
					x2 = (mx+x2)/2;
					y2 = (my+y2)/2;
					g2.drawImage(img, x2-Jeu.RAYON_PION, y2-Jeu.RAYON_PION, x2+Jeu.RAYON_PION, y2+Jeu.RAYON_PION, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
				}
			}
		}

		lstSommets = this.ctrl.getLstSommets();
		
		// Déssiner Sommet de Départ
		smt = lstSommets.get(0);
		g2.drawImage (this.imgDepart, smt.getX(), smt.getY(), smt.getX()+Jeu.LARGEUR_SOMMET, smt.getY()+Jeu.LARGEUR_SOMMET, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);

		
		for(int i = 1; i < lstSommets.size(); i++)
		{
			smt = lstSommets.get(i);
			
			if(!smt.aProprietaire())
			{
				// Sommet
				url = "../theme/distrib_images_2/transparent/" + this.ctrl.getVocab(0) +"_"+ smt.getCouleur().getNom() + ".png";
				img = getToolkit().getImage(url);
				g2.drawImage (img, smt.getX(), smt.getY(), smt.getX()+Jeu.LARGEUR_SOMMET, smt.getY()+Jeu.HAUTEUR_SOMMET, 0, 0, img.getWidth(this), img.getHeight(this), this);
				g2.setColor(Color.BLACK);
				g2.drawString(""+smt.getValeur(), smt.getX()+13, smt.getY()+19);

				// Ressource
				url = "../theme/distrib_images_2/ressources/" + smt.getRessource().getCouleur().getNom().toUpperCase() + ".png";
				img = getToolkit().getImage(url);
				x1  = smt.getX()+Jeu.LARGEUR_SOMMET/2-Jeu.LARGEUR_SOMMET/2;
				y1  = smt.getY()+Jeu.HAUTEUR_SOMMET-Jeu.LARGEUR_SOMMET;
				x2  = x1+Jeu.LARGEUR_SOMMET;
				y2  = y1+Jeu.LARGEUR_SOMMET;
				g2.drawImage (img, x1, y1, x2, y2, 0, 0, img.getWidth(this), img.getHeight(this), this);
			}
			else
			{
				url = "../theme/distrib_images_2/transparent/" + this.ctrl.getVocab(0) + "_" + smt.getCouleur().getNom() + "_clair.png";
				img = getToolkit().getImage(url);
				g2.drawImage (img, smt.getX(), smt.getY(), smt.getX()+Jeu.LARGEUR_SOMMET, smt.getY()+Jeu.HAUTEUR_SOMMET, 0, 0, img.getWidth(this), img.getHeight(this), this);
			}
 			
		}
	}

	private class GereSouris extends MouseAdapter
	{
		Sommet[] sommetsActifs = new Sommet[2];
		
		public void mousePressed(MouseEvent e)
		{
			this.sommetsActifs[0] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
			System.out.println("s1 : "+this.sommetsActifs[0]);
		}

		
		public void mouseReleased(MouseEvent e) 
		{
			List<List<Sommet>> lstTrajets;
			int[]              scores;
			
			scores = new int[2];
			this.sommetsActifs[1] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
			System.out.println("s2 : "+this.sommetsActifs[1]);
			
			if ( this.sommetsActifs[0] != null && this.sommetsActifs[1] != null )
			{
				
				boolean retour = PanelPlateau.this.ctrl.prendreSommet(this.sommetsActifs[0], this.sommetsActifs[1]);
				System.out.println(retour);

				if(retour)
				{
					PanelPlateau.this.repaint();
					lstTrajets = PanelPlateau.this.ctrl.plusCourtsChemins(this.sommetsActifs[1]);

					if(lstTrajets.size() == 1)
					{
						scores = PanelPlateau.this.ctrl.calculerScoresTrajet(lstTrajets.get(0));
					}
					else
					{
						scores = PanelPlateau.this.ctrl.calculerScoresTrajet(lstTrajets.get(0));
					}

					System.out.println(PanelPlateau.this.ctrl.getJoueurActif().getNom());
					System.out.println(scores[0]);
					System.out.println(scores[1]);

					PanelPlateau.this.ctrl.ajouterEtape(this.sommetsActifs[0], this.sommetsActifs[1], null);
					PanelPlateau.this.ctrl.incrementerNumTour();
				}

				PanelPlateau.this.ctrl.majIHM();
			}
			this.sommetsActifs[0] = this.sommetsActifs[1] = null;
		}
	}
}