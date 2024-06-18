package ihm;

import controleur.Controleur;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class IHM 
{

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
       
		this.framePlateau = new FramePlateau(this.ctrl, this.largeurPlateau, this.hauteurPlateau);
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


	// Autres Méthodes
	public void majTout()
	{
		this.tabFrameJoueur[0].repaint();
		this.tabFrameJoueur[1].repaint();
		this.framePlateau.repaint();
	}
}
