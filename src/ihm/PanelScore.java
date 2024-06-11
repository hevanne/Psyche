package ihm;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.Color;

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
		this.pnlTitre.setBackground(Color.YELLOW);

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
		this.pnlScore.add(new JLabel("80"));
		this.pnlScore.add(new JLabel("77"));

		//quatrième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//cinquième ligne

		this.pnlScore.add(new JLabel("Points des Mines"));
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//sixième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("5"));
		this.pnlScore.add(new JLabel("4"));

		//septième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("4"));
		this.pnlScore.add(new JLabel("8"));

		//huitième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("2"));
		this.pnlScore.add(new JLabel("4"));

		//neuvième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("6"));
		this.pnlScore.add(new JLabel("8"));

		//dixième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("5"));
		this.pnlScore.add(new JLabel("0"));

		//onzième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel("4"));
		this.pnlScore.add(new JLabel("5"));

		//douzième ligne

		this.pnlScore.add(new JLabel("S/Total"));
		this.pnlScore.add(new JLabel("26"));
		this.pnlScore.add(new JLabel("29"));

		//treizième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//quatorzième ligne

		this.pnlScore.add(new JLabel("Plateau individuel"));
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//quinzième ligne

		this.pnlScore.add(new JLabel("Score Pièces"));
		this.pnlScore.add(new JLabel("4"));
		this.pnlScore.add(new JLabel("9"));

		//seizième ligne

		this.pnlScore.add(new JLabel("Score des Colonnes"));
		this.pnlScore.add(new JLabel("26"));
		this.pnlScore.add(new JLabel("32"));

		//dix-septième ligne

		this.pnlScore.add(new JLabel("Score des Lignes"));
		this.pnlScore.add(new JLabel("36"));
		this.pnlScore.add(new JLabel("27"));

		//dix-huitième ligne

		this.pnlScore.add(new JLabel("S/Total"));
		this.pnlScore.add(new JLabel("66"));
		this.pnlScore.add(new JLabel("68"));

		//dix-neuvième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//vingtième ligne

		this.pnlScore.add(new JLabel("Jeton Possession restants"));
		this.pnlScore.add(new JLabel("4"));
		this.pnlScore.add(new JLabel("2"));

		//vingt-et-unième ligne

		this.pnlScore.add(new JLabel("Bonus (10)"));
		this.pnlScore.add(new JLabel("10"));
		this.pnlScore.add(new JLabel("0"));

		//vingt-deuxième ligne vide

		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());
		this.pnlScore.add(new JLabel());

		//vingt-troisième ligne

		this.pnlScore.add(new JLabel("Total"));
		this.pnlScore.add(new JLabel("182"));
		this.pnlScore.add(new JLabel("174"));

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(pnlScore, BorderLayout.CENTER);

		/* Activation des composants */
	}
}