package ihm;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.Color;

public class PanelScore extends JPanel
{
	private JPanel pnlTitre;
	private JPanel pnlScore;

	private final Color clrJauneFonce = new Color(223,194,69);
	private final Color clrJauneClair = new Color(255,242,204);


	public PanelScore()
	{
		this.setLayout(new BorderLayout());

		/* Création des composants */

		this.pnlTitre = new JPanel();
		this.pnlScore = new JPanel();


		this.pnlScore.setLayout(new GridLayout(23, 3));

		/* Positionnement des composants */

		this.pnlTitre.add(new JLabel("Fiche de score"));
		this.pnlTitre.setBackground(this.clrJauneFonce);

		//première ligne

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("Corporation Solaire", null));
		this.pnlScore.add(creerCase("Syndicat Astral", null));

		//deuxième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//troisième ligne

		this.pnlScore.add(creerCase("Point Route", clrJauneClair));
		this.pnlScore.add(creerCase("80", clrJauneClair));
		this.pnlScore.add(creerCase("77", clrJauneClair));

		//quatrième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//cinquième ligne

		this.pnlScore.add(creerCase("Point des Mines", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));

		//sixième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("5", null));
		this.pnlScore.add(creerCase("4", null));

		//septième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("4", null));
		this.pnlScore.add(creerCase("8", null));

		//huitième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("2", null));
		this.pnlScore.add(creerCase("4", null));

		//neuvième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("6", null));
		this.pnlScore.add(creerCase("8", null));

		//dixième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("5", null));
		this.pnlScore.add(creerCase("0", null));

		//onzième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("4", null));
		this.pnlScore.add(creerCase("5", null));

		//douzième ligne

		this.pnlScore.add(creerCase("S/Total", clrJauneClair));
		this.pnlScore.add(creerCase("26", clrJauneClair));
		this.pnlScore.add(creerCase("29", clrJauneClair));

		//treizième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//quatorzième ligne

		this.pnlScore.add(creerCase("Plateau Individuel", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));

		//quinzième ligne

		this.pnlScore.add(creerCase("Score Pièces", null));
		this.pnlScore.add(creerCase("4", null));
		this.pnlScore.add(creerCase("9", null));

		//seizième ligne

		this.pnlScore.add(creerCase("Sommes des Colonnes", null));
		this.pnlScore.add(creerCase("26", null));
		this.pnlScore.add(creerCase("32", null));

		//dix-septième ligne

		this.pnlScore.add(creerCase("Scores des Lignes", null));
		this.pnlScore.add(creerCase("36", null));
		this.pnlScore.add(creerCase("27", null));

		//dix-huitième ligne

		this.pnlScore.add(creerCase("S/Total", null));
		this.pnlScore.add(creerCase("66", null));
		this.pnlScore.add(creerCase("68", null));

		//dix-neuvième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//vingtième ligne

		this.pnlScore.add(creerCase("Jetons Possession restants", null));
		this.pnlScore.add(creerCase("4", null));
		this.pnlScore.add(creerCase("2", null));

		//vingt-et-unième ligne

		this.pnlScore.add(creerCase("bonus(10)", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));

		//vingt-deuxième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//vingt-troisième ligne

		this.pnlScore.add(creerCase("Total", clrJauneFonce));
		this.pnlScore.add(creerCase("182", clrJauneFonce));
		this.pnlScore.add(creerCase("174", clrJauneFonce));

		this.add(pnlTitre, BorderLayout.NORTH);
		this.add(pnlScore, BorderLayout.CENTER);

		/* Activation des composants */
	}

	public JPanel creerCase(String values,Color clr)
	{
		JPanel pnl = new JPanel();
		JLabel lbl = new JLabel(values);
		pnl.setBorder(new LineBorder(Color.BLACK));
		pnl.add(lbl);
		pnl.setBackground(clr);
		return pnl;
	}
}