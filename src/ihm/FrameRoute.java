/*
 * SAE 2.05 - Exercice 3
 * Cnaepelnickx Evan, Du Luye, Laparre Quentin, Lemaire Clément
 * 03/06/2024
 */
package ihm;

 import controleur.Controleur;

import javax.swing.*;

public class FrameRoute extends JFrame
{
	private Controleur ctrl;
	private PanelRoute pnlRoute;

	public FrameRoute(Controleur ctrl) 
	{
		this.ctrl = ctrl;
		this.setTitle ("Route");
		this.setSize  (800,300);
		this.setLocation(700, 500);

		this.pnlRoute = new PanelRoute(this.ctrl);
		this.add(pnlRoute);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public PanelRoute getPanelRoute()
	{
		return this.pnlRoute;
	}

}