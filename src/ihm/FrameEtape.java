package ihm;

import javax.swing.*;

import controleur.Controleur;

public class FrameEtape extends JFrame
{
	private Controleur ctrl;
	private PanelEtape panelEtape;

	public FrameEtape(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.panelEtape = new PanelEtape(this.ctrl);
		this.add(this.panelEtape);
		this.setVisible(true);
		this.pack();
	}
}
