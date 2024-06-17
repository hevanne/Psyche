package ihm;

import controleur.Controleur;

import javax.swing.*;

public class FramePlateau extends JFrame
{
	private Controleur ctrl;
	private PanelPlateau pnlPlateau;

	public FramePlateau(Controleur ctrl)
	{
		this.setTitle("Plateau");
		this.setLocation(50,50);
		this.setSize(1269, 1122);
		this.setJMenuBar( new BarreMenu(ctrl) );

		/* Création des composants */

		this.pnlPlateau = new PanelPlateau(ctrl);

		/* Positionnement des composants */

		this.add(this.pnlPlateau);

		/* Activation des composants */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}