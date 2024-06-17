package ihm;

import controleur.Controleur;
import metier.*;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

public class PanelPlateau extends JPanel
{
	private static final int RAYON_SOMMET = 10;

	private List<Point>   points   = new ArrayList<>();
	private List<Segment> segments = new ArrayList<>();

	private Controleur ctrl;

	public PanelPlateau(Controleur ctrl)
	{
		/* Création des composants */
		this.ctrl= ctrl;
		
		/* Positionnement des composants */
		this.initPlateau();

		/* Activation des composants */

		this.addMouseListener( new GereSouris() );
	}



	public void initPlateau()
	{
		points.clear();
		this.repaint();

		for(Sommet s : this.ctrl.getLstSommet())
		{
			System.out.println(s);
			this.points.add(new Point(s.getX(), s.getY(),10,10, s.getCouleur()));
		}

		for(Route r : this.ctrl.getLstRoute())
		{
			System.out.println(r);
			this.segments.add(new Segment(r.getSmtDep(), r.getSmtArr(), r.getNbSection()));
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		List<Point> pointsCopy = new ArrayList<>(points);

		// Définir la couleur pour les points

		// dessine les points déjà enregistrés
		for(Point p : pointsCopy)
		{
			g2.setColor(p.couleur.getColor());
			g2.fillOval(p.x - RAYON_SOMMET, p.y - RAYON_SOMMET, RAYON_SOMMET * 2, RAYON_SOMMET * 2);
		}
	}

	public static double distance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
	}

	private class GereSouris extends MouseAdapter
	{
		private Point pTemp1, pTemp2;

		public void mouseClicked(MouseEvent e)
		{
			super.mouseClicked(e);

			for (Point p : points)
			{
				if (PanelPlateau.distance(e.getX(), e.getY(), p.getX(), p.getY()) <= PanelPlateau.RAYON_SOMMET)
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
		int x, y, w, h;
		Couleur couleur;

		Point(int x, int y, int w, int h, Couleur couleur)
		{
			this.x = x;
			this.y = y;
			this.couleur = couleur;
			this.w = w;
			this.h = h;
		}

		public int getX() { return x; }
		public int getY() { return y; }
		public int getW() { return w; }
		public int getH() { return h; }
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
}