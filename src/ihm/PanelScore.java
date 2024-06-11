package ihm;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class PanelScore extends JPanel
{
	private JPanel pnlTitre;
	private JPanel pnlScore;

	public PanelScore()
	{
		this.setLayout(new BorderLayout());

		/* Création des composants */

		this.pnlTitre = new JPanel();
		this.pnlScore = new JPanel();

		this.pnlScore.setLayout(new GridLayout(23, 3));

		/* Positionnement des composants */

		this.pnlTitre.add(new JLabel("Fiche de score"));

		//première ligne

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("Corporation Solaire"));
		this.pnlScore.add(new JLabel("Syndicat Astral"));

		//deuxième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//troisième ligne

		this.pnlScore.add(new JLabel("Points Route"));
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(pnlScore, BorderLayout.CENTER);

		/* Activation des composants */
	}
}