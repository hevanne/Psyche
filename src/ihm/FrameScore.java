package ihm;

import javax.swing.*;

public class FrameScore extends JFrame
{
	private PanelScore pnlScore;

	public FrameScore()
	{
		this.setTitle("Score");
		this.setLocation(50,50);
		this.setSize(600, 500);

		/* Création des composants */

		this.pnlScore = new PanelScore();

		/* Positionnement des composants */

		this.add(pnlScore);

		/* Activation des composants */

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FrameScore();
	}
}