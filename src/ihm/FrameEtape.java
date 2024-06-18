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
		this.setTitle("Etape");
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelEtape = new PanelEtape(this.ctrl, this);
		this.add(this.panelEtape);

		this.pack();
	}
}
