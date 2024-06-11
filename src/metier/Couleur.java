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
