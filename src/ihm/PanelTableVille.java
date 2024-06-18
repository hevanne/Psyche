/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */
package ihm;

import controleur.Controleur;
import metier.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;



public class PanelTableVille extends JPanel implements ActionListener
{
	private JTable tblGrilleDonnees;
	private int nbVille = 0;
	private Object[][] tabDonnees;
	private JScrollPane spGrilleDonnees;
	private String[]   tabEntetes = {   "Numero"  , "Valeur", "Couleur"    , "X", "Y" };
	private JButton btnModifier;
	private Controleur ctrl;
	private  DefaultTableModel model = new DefaultTableModel();

	public PanelTableVille(Controleur ctrl)
	{
		this.nbVille = 0;
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.btnModifier = new JButton("Sauvegarder");

		for (int i=0; i<this.tabEntetes.length; i++)
		{
			this.model.addColumn(this.tabEntetes[i]);
		}

		this.tabDonnees = new Object[1][4];

		this.tblGrilleDonnees    = new JTable ( this.model );
 		this.spGrilleDonnees     = new JScrollPane( tblGrilleDonnees );
		this.tblGrilleDonnees.setFillsViewportHeight(false);
		this.spGrilleDonnees.setPreferredSize(new Dimension(400, 200));

		// positionnement des composants
		this.add ( spGrilleDonnees, BorderLayout.CENTER );
		this.add(this.btnModifier,BorderLayout.SOUTH);
		this.majVille();

		//Activation des composants

		this.btnModifier.addActionListener(this);
		
	}

	public void ajouterVille(int val, int coul, int x, int y)
	{
		this.model.addRow(new String[]{this.nbVille+"",val+"", coul+"", x+"", y+""});
		this.nbVille++;
		System.out.println("ajout de ville");

	}

	public void majVille()
	{
		for(Sommet s: this.ctrl.getLstSommets())
		{
			String couleur="";
			switch (s.getCouleur().getNom()) {
				case "Jaune":couleur = "1";	
							break;
				case "Bleu":couleur = "2";	
							break;
				case "Gris":couleur = "3";	
							break;
				case "Vert":couleur = "4";	
							break;
				case "Rouge":couleur = "5";	
							break;
				case "Marron":couleur = "6";	
							break;
			
				default:
					break;
			}
			this.nbVille++;
			this.model.addRow(new String[]{s.getNum()+"",s.getValeur()+"", couleur,s.getX()+"", s.getY()+""});
		}
	}

	public void supprimerVille(int i)
	{
        if (i >= 0 && i < model.getRowCount()) {
            model.removeRow(i);
		}


	}

	public void actionPerformed(ActionEvent e)
	{
		
	}
}