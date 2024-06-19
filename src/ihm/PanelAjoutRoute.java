/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */
package ihm;

import controleur.Controleur;
import metier.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelAjoutRoute extends JPanel implements ActionListener
{
	private JPanel pnlLabel;
	private JPanel pnlAction;

	private JLabel lblVilleDep, lblVilleArr, lblTroncons;

	private JComboBox<String> cmbVilleDep;
	private JComboBox<String> cmbVilleArr;

	private JTextField txtTroncons;

	private JButton btnAjouter;

	private String[] villes;

	private Controleur ctrl;
	private PanelTableRoute pnlTableRoute;

	public PanelAjoutRoute(Controleur ctrl, PanelTableRoute pnlTableRoute)
	{
		this.ctrl = ctrl;
		this.pnlTableRoute = pnlTableRoute;
		this.setLayout(new BorderLayout());

		/* Création des composants */
		this.villes = new String[0];

		this.pnlLabel = new JPanel();
		this.pnlAction = new JPanel();

		this.pnlLabel.setLayout(new GridLayout(4, 1));
		this.pnlAction.setLayout(new GridLayout(4, 3));

		this.lblVilleDep = new JLabel("Sommet Dep : ");
		this.lblVilleDep.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblVilleArr = new JLabel("Sommet Arr : ");
		this.lblVilleArr.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblTroncons = new JLabel("Tronçons : ");
		this.lblTroncons.setHorizontalAlignment(SwingConstants.RIGHT);

		this.cmbVilleDep = new JComboBox<>(villes);
		this.cmbVilleArr = new JComboBox<>(villes);
		this.initSommet();

		this.txtTroncons = new JTextField(20);

		this.btnAjouter = new JButton("Ajouter");

		/* Positionnement des composants */

		this.pnlLabel.add(this.lblVilleDep);
		this.pnlLabel.add(this.lblVilleArr);
		this.pnlLabel.add(this.lblTroncons);

		this.pnlAction.add(this.cmbVilleDep);
		this.pnlAction.add(this.cmbVilleArr);
		this.pnlAction.add(this.txtTroncons);
		this.pnlAction.add(this.btnAjouter);

		this.add(this.pnlLabel, BorderLayout.WEST);
		this.add(this.pnlAction, BorderLayout.CENTER);

		/* Activation des composants */

		this.btnAjouter.addActionListener(this);
	}

	public void ajouterSommet(String sommet)
	{
		cmbVilleDep.addItem(sommet);
		cmbVilleArr.addItem(sommet);
	}

	public void initSommet()
	{
		for (Sommet s: this.ctrl.getLstSommets())
		{
			cmbVilleDep.addItem(s.getNom());
			cmbVilleArr.addItem(s.getNom());
		}
	}


	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAjouter)
		{
			if (e.getSource() == this.btnAjouter)
			{
				try {
					if (Integer.parseInt(this.txtTroncons.getText())<=2 && Integer.parseInt(this.txtTroncons.getText())>0)
						this.pnlTableRoute.ajouterRoute(this.cmbVilleDep.getSelectedItem()+"", this.cmbVilleArr.getSelectedItem()+"", this.txtTroncons.getText());

				} catch (NumberFormatException event) {}
			}
		}
	}
}