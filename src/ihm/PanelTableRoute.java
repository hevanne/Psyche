/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */

 package ihm;

 import controleur.Controleur;
 import metier.*;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class PanelTableRoute extends JPanel implements ActionListener
{
	private JTable tblRoute;
	private int nbRoute = 0;
	private JScrollPane spGrilleRoute;
	private JButton btnSauvegarder;
	private String[] tabEntetes = { " Ville Dep", "Ville Arr", "Tronçons" };
	private Controleur ctrl;
	private  DefaultTableModel model = new DefaultTableModel();

	public PanelTableRoute(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		/* Création des composants */

		for (int i=0; i<this.tabEntetes.length; i++)
		{
			this.model.addColumn(this.tabEntetes[i]);
		}

		this.initRoute();
		this.tblRoute = new JTable(this.model);
		this.spGrilleRoute = new JScrollPane(tblRoute);
		this.tblRoute.setFillsViewportHeight(false);
		this.spGrilleRoute.setPreferredSize(new Dimension(400, 200));
		this.btnSauvegarder = new JButton("Sauvegarder");

		/* Positionnement des composants */

		this.add(spGrilleRoute, BorderLayout.CENTER);
		this.add(btnSauvegarder, BorderLayout.SOUTH);
		/* Activation des composants */
		this.btnSauvegarder.addActionListener(this);
	}



	public void ajouterRoute(String sommetDep, String sommetArr, String nbTronc)
	{
		this.model.addRow(new String[]{sommetDep, sommetArr, nbTronc});
		this.nbRoute++;
		System.out.println("ajout de route");
	}

	public void initRoute()
	{
		for (Route r: this.ctrl.getLstRoutes())
		{
			this.model.addRow(new String[]{r.getSmtDep().getNom(), r.getSmtArr().getNom(), r.getNbSection()+""});
		}
	}
	

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.btnSauvegarder)
		{
			for (int i=0; i<this.model.getRowCount(); i++)
			{
				this.ctrl.majRoute(model.getValueAt(i, 0)+"",
									model.getValueAt(i, 1)+"",
									model.getValueAt(i, 2)+"");
			}
		}
	}
}