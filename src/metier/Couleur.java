<<<<<<< HEAD
import java.awt.Color;

public enum Couleur
{
	ROUGE (255,0  ,0  ),
	VERT  (0  ,255,0  ),
	BLEU  (0  ,0  ,255),
	JAUNE (255,255,0  ),
	ORANGE (255,128,0  ),
	VIOLET(255,0  ,255),
	CYAN  (0  ,255,255),
	BLANC (255,255,255),
	BRUN (88,41,0),
	NOIR  (0  ,0  ,0  );

	private int r, v, b;

	Couleur ( int r, int v, int b )
	{
	this.r = r;
	this.v = v;
	this.b = b;
	}

	public Color getColor()
	{
		return new Color (this.r, this.v , this.b);
	}

	public static int getNbCouleur()
	{

		return 0;
	}

	public static Couleur valueOf(int ordinal)
	{

		return null;
	}

}
=======
package metier;

import java.awt.Color;

public class Couleur
{
	private String nom;
	private int[]  rgb;

	public Couleur(String nom, int r, int g, int b)
	{
		this.nom = nom;
		this.rgb = new int[3];
		this.rgb[0] = r;
		this.rgb[1] = g;
		this.rgb[2] = b;
	}

	public Color getColor() { return new Color(this.rgb[0], this.rgb[1], this.rgb[2]); }
	
	public String getNom() { return this.nom; }

	public int[] getRGB() { return this.rgb; }

	public String toString()
	{
		return this.nom + " (" + this.rgb[0] + ", " + this.rgb[1] + ", " + this.rgb[2] + ")";
	}
}
>>>>>>> 2e8690cab9548bef4476d326d0a93b6d9f9e6c77
