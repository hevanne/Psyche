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

	public FrameTrajet(Controleur ctrl, List<List<Sommet>> lstTrajets)
	{
		this.ctrl       = ctrl;
		this.lstTrajets = lstTrajets;
		this.setTitle("Trajet");

		this.setLocation(100, 100);
		this.setResizable(false);
		this.setVisible( true );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panelTrajet = new PanelTrajet(this.ctrl, this, lstTrajets);
		this.add(this.panelTrajet);

		this.pack();
	}
}
