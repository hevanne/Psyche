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

public class PanelRoute extends JPanel
{
	private PanelTableRoute pnlTableRoute;
	private PanelAjoutRoute pnlAjoutRoute;

	private Controleur ctrl;

	public PanelRoute(Controleur ctrl)
	{
		this.ctrl = ctrl;

		/* Création des composants */

		this.pnlTableRoute = new PanelTableRoute(this.ctrl);
		this.pnlAjoutRoute = new PanelAjoutRoute(this.ctrl, this.pnlTableRoute);

		this.add(this.pnlTableRoute, BorderLayout.CENTER);
		this.add(this.pnlAjoutRoute, BorderLayout.EAST);
	}

	public PanelAjoutRoute getPanelAjoutRoute()
	{
		return this.pnlAjoutRoute;
	}
}