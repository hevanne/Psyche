package ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controleur.Controleur;
import metier.Sommet;

import java.util.List;

public class PanelTrajet extends JPanel implements ActionListener
{
	
	private Controleur         ctrl;
	private FrameTrajet         frameTrajet;
	private List<List<Sommet>> lstTrajets;


	private JComboBox<String> ddlstTrajets;
	private JButton           btnValider;

	public PanelTrajet(Controleur ctrl, FrameTrajet frameTrajet, List<List<Sommet>> lstTrajets)
	{	
		JPanel   panelInfo, panelAction;
		String[] tabTrajets;
		
		this.ctrl       = ctrl;
		this.frameTrajet = frameTrajet;
		this.lstTrajets = lstTrajets;

		this.setLayout(new BorderLayout(5, 5));
		
		/* Création des composants */
		panelInfo   = new JPanel(new GridLayout(1, 2, 5, 5));
		panelAction = new JPanel();

		tabTrajets = new String[this.lstTrajets.size()];
		for(int i = 0; i < this.lstTrajets.size(); i++)
			tabTrajets[i] = this.lstTrajets.get(i).toString();

		this.ddlstTrajets = new JComboBox<String>(tabTrajets);
		this.ddlstTrajets.setSelectedIndex(0);
		this.btnValider = new JButton("Valider");

		/* Positionnement des composants */
		this.add(panelInfo,   BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.SOUTH);

		panelInfo.add(new JLabel("Trajet choisi : ", SwingConstants.RIGHT));
		panelInfo.add(this.ddlstTrajets);

		panelAction.add(this.btnValider);

		/* Activation des composants */
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnValider)
		{
			this.ctrl.ajouterScoresTrajet(this.lstTrajets.get(this.ddlstTrajets.getSelectedIndex()));
			this.frameTrajet.dispose();
		}
	}
}
