package ihm;

import controleur.Controleur;
import metier.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;

import java.awt.Color;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class PanelScore extends JPanel
{
	private Controleur ctrl;

	private Joueur j1, j2;

	private JPanel pnlTitre;
	private JPanel pnlScore;

	private final Color clrJauneFonce = new Color(223,194,69);
	private final Color clrJauneClair = new Color(255,242,204);

	public PanelScore(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.j1 = this.ctrl.getJoueur(0);
		this.j2 = this.ctrl.getJoueur(1);

		calculerNbPossessions(j1);
		calculerNbPossessions(j2);

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
		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/pion_joueur_1.png","Corporation Solaire" ,null));
		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/pion_joueur_2.png","Syndicat Astral" ,null));

		//deuxième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//troisième ligne

		this.pnlScore.add(creerCase("Point Route", clrJauneClair));
		this.pnlScore.add(creerCase("" + this.j1.getScoreRoute(), clrJauneClair));
		this.pnlScore.add(creerCase("" + this.j2.getScoreRoute(), clrJauneClair));

		//quatrième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//cinquième ligne

		this.pnlScore.add(creerCase("Point des Mines", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));
		this.pnlScore.add(creerCase("", clrJauneClair));

		//sixième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Jaune.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Jaune"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Jaune"), null));

		//septième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Bleu.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Bleu"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Bleu"), null));

		//huitième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Gris.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Gris"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Gris"), null));

		//neuvième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Vert.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Vert"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Vert"), null));

		//dixième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Rouge.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Rouge"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Rouge"), null));

		//onzième ligne vide

		this.pnlScore.add(creerCaseImg("../theme/distrib_images_2/icon/Mine_Marron.png","" ,null));
		this.pnlScore.add(creerCase("" + j1.getHauteRessource("Marron"), null));
		this.pnlScore.add(creerCase("" + j2.getHauteRessource("Marron"), null));

		//douzième ligne

		this.pnlScore.add(creerCase("S/Total", clrJauneClair));
		this.pnlScore.add(creerCase("" + calculerSTotalCouleurMine(j1), clrJauneClair));
		this.pnlScore.add(creerCase("" + calculerSTotalCouleurMine(j2), clrJauneClair));

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
		this.pnlScore.add(creerCase("" + this.j1.calculerScorePiece(), null));
		this.pnlScore.add(creerCase("" + this.j2.calculerScorePiece(), null));

		//seizième ligne

		this.pnlScore.add(creerCase("Sommes des Colonnes", null));
		this.pnlScore.add(creerCase("" + this.j1.calculerScoreColonne(), null));
		this.pnlScore.add(creerCase("" + this.j2.calculerScoreColonne(), null));

		//dix-septième ligne

		this.pnlScore.add(creerCase("Scores des Lignes", null));
		this.pnlScore.add(creerCase("" + this.j1.calculerScoreLigne(), null));
		this.pnlScore.add(creerCase("" + this.j2.calculerScoreLigne(), null));

		//dix-huitième ligne

		this.pnlScore.add(creerCase("S/Total", null));
		this.pnlScore.add(creerCase("" + calculerSTotalPlateauIndividuel(this.j1), null));
		this.pnlScore.add(creerCase("" + calculerSTotalPlateauIndividuel(this.j2), null));

		//dix-neuvième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//vingtième ligne

		this.pnlScore.add(creerCase("Jetons Possession restants", null));
		this.pnlScore.add(creerCase("" + (25 - this.j1.getNbPossessions()), null));
		this.pnlScore.add(creerCase("" + (25 - this.j2.getNbPossessions()), null));

		//vingt-et-unième ligne

		this.pnlScore.add(creerCase("Bonus(10)", clrJauneClair));
		this.pnlScore.add(creerCase("" + bonus(this.j1, this.j2), clrJauneClair));
		this.pnlScore.add(creerCase("" + bonus(this.j2, this.j1), clrJauneClair));

		//vingt-deuxième ligne vide

		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));
		this.pnlScore.add(creerCase("", null));

		//vingt-troisième ligne

		this.pnlScore.add(creerCase("Total", clrJauneFonce));
		this.pnlScore.add(creerCase("" + scoreTotal(j1), clrJauneFonce));
		this.pnlScore.add(creerCase("" + scoreTotal(j2), clrJauneFonce));

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

	public JPanel creerCaseImg(String img, String lbl, Color clr)
	{
		JPanel pnl = new JPanel();
		
		ImageIcon icon = new ImageIcon(img);
		Image imgIcon = icon.getImage();
		Image newImg = imgIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImg);

		JLabel lblImg = new JLabel(newIcon);
		JLabel lblNom = new JLabel(lbl);

		pnl.setBorder(new LineBorder(Color.BLACK));
		pnl.add(lblImg);
		pnl.add(lblNom);
		pnl.setBackground(clr);
		return pnl;
	}

	private int bonus(Joueur j1, Joueur j2)
	{
		if(j1.getNbPossessions() <= j2.getNbPossessions())
			return 10;

		return 0;
	}

	private int calculerNbPossessions(Joueur j)
	{
		List<Route> lstRoutes = this.ctrl.getLstRoutes();

		for(Route r : lstRoutes)
		{
			if(r.aProprietaire() && r.getProprietaire().equals(j))
				j.varierNbPossessions(r.getNbSection());
		}

		return j.getNbPossessions();
	}

	private int calculerSTotalPlateauIndividuel(Joueur j)
	{
		return j.calculerScorePiece() + j.calculerScoreColonne() + j.calculerScoreLigne();
	}

	private int calculerSTotalCouleurMine(Joueur j)
	{
		return j.getHauteRessource("Jaune") + j.getHauteRessource("Marron") + j.getHauteRessource("Vert") +
			   j.getHauteRessource("Gris")  + j.getHauteRessource("Bleu")   + j.getHauteRessource("Rouge");
	}

	private int scoreTotal(Joueur j)
	{
		if(j == this.j1)
			return j.getScoreRoute() + calculerSTotalCouleurMine(j) + calculerSTotalPlateauIndividuel(j) + bonus(this.j1, this.j2);

		if(j == this.j2)
			return j.getScoreRoute() + calculerSTotalCouleurMine(j) + calculerSTotalPlateauIndividuel(j) + bonus(this.j2, this.j1);

		return 0;
	}
}
