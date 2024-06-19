package ihm;

import controleur.Controleur;
import metier.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.List;

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;
	private int        largeur, hauteur;
	private Image      imgPlateau, imgDepart;

	private boolean modif;

	public PanelPlateau(Controleur ctrl, int largeur, int hauteur, boolean modif)
	{
		this.ctrl    = ctrl;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.modif = modif;

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
			x1 = r.getSmtDep().getX() + IHM.LARGEUR_SOMMET/2;
			y1 = r.getSmtDep().getY() + IHM.HAUTEUR_SOMMET/2;
			x2 = r.getSmtArr().getX() + IHM.LARGEUR_SOMMET/2;
			y2 = r.getSmtArr().getY() + IHM.HAUTEUR_SOMMET/2;
			
			g2.fillOval(x1-5, y1-5, 10, 10);
			g2.fillOval(x2-5, y2-5, 10, 10);

			g2.drawLine(x1, y1, x2, y2);

			if (r.getNbSection() == 2)
			{
				mx = (x1+x2)/2;
				my = (y1+y2)/2;
				g2.fillOval(mx-5, my-5, 10, 10);
			}

			// Pion
			if (r.aProprietaire())
			{
				url = "../theme/distrib_images_2/"+this.ctrl.getImagePionJoueur(r.getProprietaire().getNum());
				img = getToolkit().getImage(url);

				mx = (x1+x2)/2;
				my = (y1+y2)/2;

				if (r.getNbSection() == 1)
				{
					g2.drawImage(img, mx-IHM.RAYON_PION, my-IHM.RAYON_PION, mx+IHM.RAYON_PION*2, my+IHM.RAYON_PION*2, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
				}
				else
				{
					x1 = (mx+x1)/2;
					y1 = (my+y1)/2;
					g2.drawImage(img, x1-IHM.RAYON_PION, y1-IHM.RAYON_PION, x1+IHM.RAYON_PION*2, y1+IHM.RAYON_PION*2, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
					x2 = (mx+x2)/2;
					y2 = (my+y2)/2;
					g2.drawImage(img, x2-IHM.RAYON_PION, y2-IHM.RAYON_PION, x2+IHM.RAYON_PION*2, y2+IHM.RAYON_PION*2, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);
				}
			}
		}

		lstSommets = this.ctrl.getLstSommets();
		
		// Déssiner Sommet de Départ
		smt = lstSommets.get(0);
		g2.drawImage (this.imgDepart, smt.getX(), smt.getY(), smt.getX()+IHM.LARGEUR_SOMMET, smt.getY()+IHM.LARGEUR_SOMMET, 0, 0, this.imgDepart.getWidth(this), this.imgDepart.getHeight(this), this);

		
		for(int i = 1; i < lstSommets.size(); i++)
		{
			smt = lstSommets.get(i);
			
			if(!smt.aProprietaire())
			{
				// Sommet
				url = "../theme/distrib_images_2/transparent/" + this.ctrl.getVocab(0) +"_"+ smt.getCouleur().getNom() + ".png";
				img = getToolkit().getImage(url);
				g2.drawImage (img, smt.getX(), smt.getY(), smt.getX()+IHM.LARGEUR_SOMMET, smt.getY()+IHM.HAUTEUR_SOMMET, 0, 0, img.getWidth(this), img.getHeight(this), this);
				g2.setColor(Color.BLACK);
				g2.drawString(""+smt.getValeur(), smt.getX()+13, smt.getY()+19);

				// Ressource
				url = "../theme/distrib_images_2/ressources/" + smt.getRessource().getCouleur().getNom().toUpperCase() + ".png";
				img = getToolkit().getImage(url);
				x1  = smt.getX()+IHM.LARGEUR_SOMMET/2-IHM.LARGEUR_SOMMET/2;
				y1  = smt.getY()+IHM.HAUTEUR_SOMMET-IHM.LARGEUR_SOMMET;
				x2  = x1+IHM.LARGEUR_SOMMET;
				y2  = y1+IHM.LARGEUR_SOMMET;
				g2.drawImage (img, x1, y1, x2, y2, 0, 0, img.getWidth(this), img.getHeight(this), this);
			}
			else
			{
				url = "../theme/distrib_images_2/transparent/" + this.ctrl.getVocab(0) + "_" + smt.getCouleur().getNom() + "_clair.png";
				img = getToolkit().getImage(url);
				g2.drawImage (img, smt.getX(), smt.getY(), smt.getX()+IHM.LARGEUR_SOMMET, smt.getY()+IHM.HAUTEUR_SOMMET, 0, 0, img.getWidth(this), img.getHeight(this), this);
			}
 			
		}
	}

	private boolean BModif()
	{
		return this.modif;
	}

	public void setModif()
	{
		this.modif = true;
		System.out.println("Le plateau peut est modifié");
	}

	public void setJouer()
	{
		this.modif = false;
		System.out.println("Vous pouvez jouer");
	}

	private class GereSouris extends MouseAdapter
	{
		Sommet[] sommetsActifs = new Sommet[2];
		
		public void mousePressed(MouseEvent e)
		{
			if(!PanelPlateau.this.BModif())
			{
				this.sommetsActifs[0] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
				System.out.println("s1 : "+this.sommetsActifs[0]);
			}
		}

		
		public void mouseReleased(MouseEvent e) 
		{
			
			if(!PanelPlateau.this.BModif())
			{
				List<List<Sommet>> lstTrajets;
				List<Sommet>       trajet;
				
				this.sommetsActifs[1] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
				System.out.println("s2 : "+this.sommetsActifs[1]);
				
				if ( this.sommetsActifs[0] != null && this.sommetsActifs[1] != null && !PanelPlateau.this.ctrl.estFinJeu())
				{
					
					trajet = PanelPlateau.this.ctrl.prendreSommet(this.sommetsActifs[0], this.sommetsActifs[1]);
					System.out.println("Sommet pris");
					System.out.println(trajet);
					System.out.println(trajet != null && trajet.size() != 0);
					
					if(trajet != null && trajet.size() != 0)
					{
						// Affectation du Joueur actif aux routes composant le trajet
						PanelPlateau.this.ctrl.affecterPrpRoute(trajet);
						
						// Calculs et ajout des scores
						lstTrajets = PanelPlateau.this.ctrl.getTrajets(this.sommetsActifs[1], PanelPlateau.this.ctrl.getDepart(), true);
						System.out.println("Trajet Sommet Depart");
						System.out.println(lstTrajets);
						
						if(lstTrajets.size() == 1)
							PanelPlateau.this.ctrl.ajouterScoresTrajet(lstTrajets.get(0), lstTrajets.get(0).get(0));
						else
							PanelPlateau.this.ctrl.selectionnerTrajet(lstTrajets);

						PanelPlateau.this.ctrl.incrementerNumTour();
					}
				}
				this.sommetsActifs[0] = this.sommetsActifs[1] = null;
			}
		}
	}
}