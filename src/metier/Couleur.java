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
