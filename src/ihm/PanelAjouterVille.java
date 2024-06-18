/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */

package ihm;
import controleur.Controleur;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelAjouterVille extends JPanel implements ActionListener
{

	private JPanel panelLbl;
	private JPanel panelTxt;

	private JLabel lblVal, lblCouleur, lblX, lblY, lblSuppr;
	private JTextField txtVal, txtCoul, txtX, txtY, txtSuppr;
	private JButton btnAjouter, btnSupprimer;
	private Controleur ctrl;
	private PanelTableVille pnlVille;

	public PanelAjouterVille(Controleur ctrl, PanelTableVille pnlVille)
	{
		this.ctrl = ctrl;
		this.pnlVille = pnlVille;
		this.setLayout(new BorderLayout());

		/* Création des compostants */
		this.panelLbl = new JPanel();
		this.panelTxt = new JPanel();

		this.panelLbl.setLayout(new GridLayout(8,1));
		this.panelTxt.setLayout(new GridLayout(8,1));

		this.lblVal = new JLabel ("Val:");
		this.lblVal.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCouleur = new JLabel ("Coul:");
		this.lblCouleur.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblX = new JLabel ("X:");
		this.lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblY = new JLabel ("Y:");
		this.lblY.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblSuppr = new JLabel ("Num de la ville à supprimer:");

		this.txtVal = new JTextField(20);
		this.txtCoul = new JTextField(20);
		this.txtX   = new JTextField(20);
		this.txtY   = new JTextField(20);
		this.txtSuppr = new JTextField(20);

		this.btnAjouter = new JButton("Ajouter");
		this.btnSupprimer = new JButton("Supprimer");
		

		/* Placement des composants */
		this.panelLbl.add(this.lblVal);
		this.panelLbl.add(this.lblCouleur);
		this.panelLbl.add(this.lblX);
		this.panelLbl.add(this.lblY);

		this.panelTxt.add(this.txtVal);
		this.panelTxt.add(this.txtCoul);
		this.panelTxt.add(this.txtX);
		this.panelTxt.add(this.txtY);
		this.panelTxt.add(this.btnAjouter);
		this.panelTxt.add(this.lblSuppr);
		this.panelTxt.add(this.txtSuppr);
		this.panelTxt.add(this.btnSupprimer);

		this.add(this.panelLbl,BorderLayout.WEST);
		this.add(this.panelTxt,BorderLayout.CENTER);

		/* Activation des composants */

		this.btnAjouter.addActionListener(this);
		this.btnSupprimer.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnAjouter)
		{
			try {
				this.pnlVille.ajouterVille(Integer.parseInt(this.txtVal.getText()),Integer.parseInt(this.txtCoul.getText()),
															Integer.parseInt(this.txtX.getText()), Integer.parseInt(this.txtY.getText()));
			} catch (NumberFormatException event) {}
		}

		if (e.getSource() == this.btnSupprimer)
		{
			try {
				this.pnlVille.supprimerVille(Integer.parseInt(this.txtSuppr.getText()));
			} catch (NumberFormatException event) {}
		}
	}
}