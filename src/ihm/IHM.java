package ihm;

import controleur.Controleur;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class IHM 
{
	public static final int RAYON_SOMMET     = 12;
	public static final int RAYON_IRESSOURCE = 12;
	public static final int LARGEUR_SOMMET   = 32;
	public static final int HAUTEUR_SOMMET   = 56;
	public static final int RAYON_PION       = 10;
	public static final int RAYON_RESSOURCE  = 36;

	private Controleur ctrl;
	
	private int largeurEcran,   hauteurEcran;
	private int largeurJoueur,  hauteurJoueur;
	private int largeurPlateau, hauteurPlateau;

	private FramePlateau  framePlateau;
	private FrameJoueur[] tabFrameJoueur;

	public IHM(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.largeurEcran   = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth ();
		this.hauteurEcran   = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		this.largeurPlateau = this.hauteurEcran-50;
		this.hauteurPlateau = this.hauteurEcran-50;
		this.largeurJoueur  = (this.largeurEcran-this.largeurPlateau)/2;
		this.hauteurJoueur  = this.hauteurEcran-300;

		this.tabFrameJoueur = new FrameJoueur[2];

		for(int i = 0; i < 2; i++)
		{
			this.tabFrameJoueur[i] = new FrameJoueur(this.ctrl, i, this.largeurJoueur, this.hauteurJoueur);
			this.tabFrameJoueur[i].setTitle(this.ctrl.getJoueur(i).getNom());
			this.tabFrameJoueur[i].setSize(this.largeurJoueur, this.hauteurJoueur);
			this.tabFrameJoueur[i].setResizable(false);
			this.tabFrameJoueur[i].setVisible( true );
			this.tabFrameJoueur[i].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
       
		this.framePlateau = new FramePlateau(this.ctrl, this.largeurPlateau, this.hauteurPlateau, false);
		this.framePlateau.setTitle("Plateau principal");
		this.framePlateau.setSize(this.largeurPlateau, this.hauteurPlateau);
		this.framePlateau.setResizable(false);
		this.framePlateau.setVisible( true );
		this.framePlateau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.tabFrameJoueur[0].setLocation(0,0);
		this.tabFrameJoueur[1].setLocation(this.largeurEcran - this.largeurJoueur, 0);
		this.framePlateau.setLocation(this.largeurEcran/2 - this.largeurPlateau/2, 0);
	}

	// Accesseurs
	public FramePlateau getFramePlateau()
	{
		return this.framePlateau;
	}


	// Autres Méthodes
	public void majTout()
	{
		this.tabFrameJoueur[0].repaint();
		this.tabFrameJoueur[1].repaint();
		this.framePlateau.repaint();

		if(this.ctrl.estFinJeu())
			new FrameScore(ctrl);
	}
}
