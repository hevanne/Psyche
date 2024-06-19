package ihm;

import javax.swing.*;

import controleur.Controleur;

public class FrameEtape extends JFrame
{
	private Controleur ctrl;
	private PanelEtape panelEtape;

	public FrameEtape(Controleur ctrl, int x, int y)
	{
		this.ctrl = ctrl;
		this.setTitle("Etape");
		this.setLocation(x, y);
		this.setResizable(false);
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelEtape = new PanelEtape(this.ctrl, this);
		this.add(this.panelEtape);

		this.pack();
	}
}
