package ihm;

import controleur.Controleur;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import metier.Sommet;
import metier.*;
import java.awt.Color;

public class PanelPlateau extends JPanel
{
	private List<Point> points = new ArrayList<>();
	private Controleur ctrl;
	private static final int DIAMETRE = 10;
	public PanelPlateau(Controleur ctrl)
	{
		/* Création des composants */
		this.ctrl= ctrl;
		
		/* Positionnement des composants */
		this.initPlateau();
		/* Activation des composants */

		this.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				super.mouseClicked(e);

				for (Point p : points)
				{
					if (e.getX() >= p.getX() && e.getX() <= p.getX() + p.getW() &&
						e.getY() >= p.getY() && e.getY() <= p.getY() + p.getH())

					{
						System.out.println(p);
					}
				}
			}
		});
	}

	public void initPlateau()
	{
		points.clear();
		this.repaint();

		for(Sommet s: this.ctrl.getLstSommet())
		{
			System.out.println(s);
			this.points.add(new Point(s.getX(), s.getY(),10,10, s.getCouleur()));
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
			g2.fillOval(p.x - DIAMETRE / 2, p.y - DIAMETRE / 2, DIAMETRE, DIAMETRE);
			g2.setColor(Color.BLACK);
			g2.drawString("", p.x + 5, p.y - 5);
		}
	}

	private static class Point // classe internes pour stocker les points
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


}