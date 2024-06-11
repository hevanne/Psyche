package metier;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
	private String nomSmt  ;
	private static int nbSommet = 0;
	private int    num;

	private IRessource ressource;
	private int x;
	private int y;
	private Joueur proprietaire;
	private List<Route> lstRoute = new ArrayList<Route>();


	private  Sommet (int x, int y, IRessource ressource)
	{
		this.x = x;
		this.y = y;
		this.num = nbSommet++;
		this.proprietaire = null;
		this.ressource = ressource;
	}

	public static Sommet nvSommet (int x, int y, IRessource ressource)
	{
		if (x>0 && y>0)
		{
			return new Sommet(x,y, ressource);
		}
		return null;
	}

	public int getX () { return this.x; }
	public int getY () { return this.y; }
	public void setProprietaire(Joueur proprietaire)
	{
		if (this.proprietaire == null)
		{
			this.proprietaire = proprietaire;
		}
	}

	public Route getRoute (int i) 
	{
		return this.lstRoute.get(i);
	}

	public Joueur getProprietaire()
	{
		return this.proprietaire;
	}

	public void ajouterRoute(Route route)
	{
		this.lstRoute.add(route);
	}
}
