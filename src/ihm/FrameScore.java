package ihm;

import controleur.Controleur;

import javax.swing.*;

public class FrameScore extends JFrame
{
	private PanelScore pnlScore;

	private Controleur ctrl;

	public FrameScore(Controleur ctrl)
	{
		this.setTitle("Score");
		this.setLocation(50,50);
		this.setSize(600, 600);

		this.ctrl = ctrl;

		/* Création des composants */

		this.pnlScore = new PanelScore();

		/* Positionnement des composants */

		this.add(pnlScore);

		/* Activation des composants */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}