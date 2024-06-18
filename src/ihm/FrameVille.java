/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */
package ihm;

import javax.swing.*;
import controleur.Controleur;

public class FrameVille extends JFrame
{
	Controleur ctrl;
	private PanelVille panelville;

	public FrameVille(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setTitle ("Sommet");
		this.setSize  (700,300);
		this.setLocation(5, 500);

		this.panelville = new PanelVille(ctrl);

		this.add(this.panelville);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/*public PanelVille getPanelVille()
	{
		//return this.panelville;

		return null;
	} */
}