package ihm;

import javax.swing.*;
import controleur.Controleur;
import metier.Sommet;
import java.util.List;

public class FrameTrajet extends JFrame
{
	private Controleur         ctrl;
	private List<List<Sommet>> lstTrajets;
	private PanelTrajet        panelTrajet;

	public FrameTrajet(Controleur ctrl, List<List<Sommet>> lstTrajets, int x, int y)
	{
		this.ctrl       = ctrl;
		this.lstTrajets = lstTrajets;
		this.setTitle("Trajet");

		this.setLocation(x, y);
		this.setResizable(false);
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelTrajet = new PanelTrajet(this.ctrl, this, this.lstTrajets);
		this.add(this.panelTrajet);

		this.pack();
	}
}
