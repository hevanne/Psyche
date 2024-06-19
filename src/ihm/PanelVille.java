/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */
package ihm;

import controleur.Controleur;

import javax.swing.*;
import java.awt.BorderLayout;

public class PanelVille extends JPanel
{
	private PanelTableVille pnlVille;
	private PanelAjouterVille pnlAjout;
	Controleur ctrl;

	public PanelVille(Controleur ctrl)
	{
		/* Création des composants */

		this.ctrl = ctrl;

		this.pnlVille = new PanelTableVille(this.ctrl);
		this.pnlAjout = new PanelAjouterVille(this.ctrl, this.pnlVille);
		
		this.add(this.pnlVille, BorderLayout.CENTER);
		this.add(this.pnlAjout, BorderLayout.EAST);
	}

	public PanelTableVille getPanelTableVille()
	{
		return this.pnlVille;
	}
}