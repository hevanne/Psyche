package ihm;

import controleur.Controleur;
import metier.Joueur;

import java.awt.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private Controleur ctrl;
	
	private int numJoueur;

	private Image imgPlateau;


	private final int ECART_VERTICAL = 55;
	private final int ECART_HORIZONTAL = 55;

	public PanelJoueur(Controleur ctrl, int numJoueur)
	{
		this.ctrl      = ctrl;
		this.numJoueur = numJoueur;

		this.setLayout(null);

		this.imgPlateau = getToolkit().getImage("../theme/distrib_images_2/"+this.ctrl.getJoueur(this.numJoueur).getImage());

		JLabel test = new JLabel(new ImageIcon("../theme/distrib_images_2/ressources/AG.png"));
		JLabel test2 = new JLabel(new ImageIcon("../theme/distrib_images_2/ressources/AL.png"));
		JLabel test3 = new JLabel(new ImageIcon("../theme/distrib_images_2/ressources/AU.png"));

		test.setBounds(56, 210,65, 65);
		test2.setBounds(56 + ECART_HORIZONTAL, 210,65, 65);
		test3.setBounds(56, 210 - ECART_VERTICAL,65, 65);
		
		this.add(test);
		this.add(test2);
		this.add(test3);

		/* Création des composants */
		
		/* Positionnement des composants */
		
		/* Activation des composants */
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage ( this.imgPlateau, 0, 0, this );
	}
}