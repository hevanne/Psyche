package ihm;

import controleur.Controleur;
import metier.*;

import javax.swing.*;
import javax.swing.text.Segment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.BasicStroke;

public class PanelPlateau extends JPanel
{

	//private List<Point>   points   = new ArrayList<>();
	//private List<Segment> segments = new ArrayList<>();

	private Controleur ctrl;

	private Image imgPlateau;

	public PanelPlateau(Controleur ctrl)
	{
		/* Création des composants */
		this.ctrl= ctrl;
		
		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getImagePlateauVierge());

		/* Positionnement des composants */
		//this.initPlateau();

		/* Activation des composants */

		this.addMouseListener( new GereSouris() );

		this.repaint();
	}



	public void initPlateau()
	{
		
		/*
		points.clear();
		this.repaint();

		for(Sommet s : this.ctrl.getLstSommet())
		{
			System.out.println(s);
			this.points.add(new Point(s.getX(), s.getY(), s.getCouleur()));
		}

		for(Route r : this.ctrl.getLstRoute())
		{
			System.out.println(r);
			this.segments.add(new Segment(r.getSmtDep(), r.getSmtArr(), r.getNbSection()));
		}
		*/
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgPlateau, 0, 0, this );

		List<Sommet> lstSommets = this.ctrl.getLstSommets();
		
		// Déssiner Sommet de Départ
		g2.setColor(lstSommets.get(0).getCouleur().getColor());
		g2.fillOval(lstSommets.get(0).getX() - Sommet.RAYON_SOMMET, 
		            lstSommets.get(0).getY() - Sommet.RAYON_SOMMET, 
					Sommet.RAYON_SOMMET * 2, 
					Sommet.RAYON_SOMMET * 2);

		for(int i = 1; i < lstSommets.size(); i++)
		{
			Sommet s = lstSommets.get(i);
			
			if(!s.aProprietaire())
			{
				g2.setColor(s.getCouleur().getColor());
				g2.fillOval(s.getX() - Sommet.RAYON_SOMMET, 
				            s.getY() - Sommet.RAYON_SOMMET, 
				            Sommet.RAYON_SOMMET * 2, 
				            Sommet.RAYON_SOMMET * 2);

				g2.setColor(s.getRessource().getCouleur().getColor());
				g2.fillOval(s.getX() - Sommet.RAYON_IRESSOURCE, 
				            s.getY() + Sommet.RAYON_IRESSOURCE, 
				            Sommet.RAYON_IRESSOURCE * 2, 
				            Sommet.RAYON_IRESSOURCE * 2);
			}
			else
			{
				g2.setColor(s.getCouleur().getColor());
				g2.drawOval(s.getX() - Sommet.RAYON_SOMMET, 
				            s.getY() - Sommet.RAYON_SOMMET, 
				            Sommet.RAYON_SOMMET * 2, 
				            Sommet.RAYON_SOMMET * 2);

				g2.setColor(s.getRessource().getCouleur().getColor());
				g2.drawOval(s.getX() - Sommet.RAYON_IRESSOURCE, 
				            s.getY() + Sommet.RAYON_IRESSOURCE, 
				            Sommet.RAYON_IRESSOURCE * 2, 
				            Sommet.RAYON_IRESSOURCE * 2);
			}

			List<Route> lstRoute = this.ctrl.getLstRoutes();
			g2.setColor(Color.DARK_GRAY);
			float epaisseur = 5.0f;
			g2.setStroke(new BasicStroke(epaisseur));

			for (Route r: lstRoute)
			{
				int mx, my;
				g2.drawLine(r.getSmtDep().getX(), r.getSmtDep().getY(),r.getSmtArr().getX(), r.getSmtArr().getY());

				if (r.getNbSection() == 2)
				{
					mx = (r.getSmtDep().getX()+r.getSmtArr().getX())/2;
					my = (r.getSmtDep().getY()+r.getSmtArr().getY())/2;
					g2.fillOval(mx-10, my-10,20,20);
				}
			}
		}

		/*
		List<Point> pointsCopy = new ArrayList<>(points);

		// Définir la couleur pour les points
		g2.drawImage ( this.imgPlateau, 0, 0, this );

		// dessine les points déjà enregistrés
		for(Point p : pointsCopy)
		{
			g2.setColor(p.couleur.getColor());
			g2.fillOval(p.x - RAYON, p.y - RAYON, RAYON * 2, RAYON * 2);
		}
		*/
	}

	private class GereSouris extends MouseAdapter
	{
		Sommet[] sommetsActifs = new Sommet[2];
		
		public void mousePressed(MouseEvent e)
		{
			this.sommetsActifs[0] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
			//System.out.println("s1 : "+this.sommetsActifs[0]);
		}

		
		public void mouseReleased(MouseEvent e) 
		{
			List<List<Sommet>> lstTrajets;
			int[]              scores;
			
			scores = new int[2];
			this.sommetsActifs[1] = PanelPlateau.this.ctrl.getSommet( e.getX(), e.getY() );
			//System.out.println("s2 : "+this.sommetsActifs[1]);
			
			if ( this.sommetsActifs[0] != null && this.sommetsActifs[1] != null )
			{
				
				boolean retour = PanelPlateau.this.ctrl.prendreSommet(this.sommetsActifs[0], this.sommetsActifs[1]);
				//System.out.println(retour);

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
					//System.out.println(scores[0]);
					//System.out.println(scores[1]);
					
					PanelPlateau.this.ctrl.ajouterEtape(this.sommetsActifs[0], this.sommetsActifs[1], null);
					PanelPlateau.this.ctrl.incrementerNumTour();
				}

			}

			this.sommetsActifs[0] = this.sommetsActifs[1] = null;
		}
	}

	

	/*
	private class GereSouris extends MouseAdapter
	{
		private Point pTemp1, pTemp2;

		public void mouseClicked(MouseEvent e)
		{
			super.mouseClicked(e);

			for (Point p : points)
			{
				if (PanelPlateau.distance(e.getX(), e.getY(), p.getX(), p.getY()) <= PanelPlateau.RAYON)
				{
					System.out.println("point : "+p);
					if (pTemp1 == null)
					{
						pTemp1 = p;
					}
					else
					{
						pTemp2 = p;
					}
				}
			}

			if (pTemp1 != null && pTemp2 != null)
			{
				Sommet sommetTmp1, sommetTmp2;
				sommetTmp1 = PanelPlateau.this.ctrl.getSommet(pTemp1.getX(), pTemp1.getY());
				sommetTmp2 = PanelPlateau.this.ctrl.getSommet(pTemp2.getX(), pTemp2.getY());

				System.out.println(sommetTmp1);
				System.out.println(sommetTmp2);

				Route r = sommetTmp1.getRoute(sommetTmp2);

				System.out.println(r);
			}
		}
	}

	private static class Point // classe interne pour stocker les sommets
	{
		int x, y;
		Couleur couleur;

		Point(int x, int y, Couleur couleur)
		{
			this.x = x;
			this.y = y;
			this.couleur = couleur;
		}

		public int getX() { return x; }
		public int getY() { return y; }
	}

	private static class Segment // classe interne pour stocker les routes
	{
		Sommet dep, arr;
		int nbSections;

		Segment(Sommet dep, Sommet arr, int nbSections)
		{
			this.dep = dep;
			this.arr = arr;
			this.nbSections = nbSections;
		}

		public Sommet getDep() { return dep; }
		public Sommet getArr() { return arr; }

		public int getNbSections() { return nbSections; }
	}
	*/
}