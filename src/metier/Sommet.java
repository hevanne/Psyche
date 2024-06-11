package metier;

public class Sommet {
	private String nomSmt  ;
	private int    nbSommet;
	private int    num;

	private int x;
	private int y;

	private Sommet (int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Sommet nvSommet (int x, int y)
	{
		return null;
	}

	public int getX () { return this.x; }
	public int getY () { return this.y; }

	public Route getRoute (int i) 
	{
		return null;
	}
}
