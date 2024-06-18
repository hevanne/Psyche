package ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import controleur.Controleur;

public class PanelEtape extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private FrameEtape frameEtape;
	private JTextField txtNumEtape;
	private JButton    btnValider;

	public PanelEtape(Controleur ctrl,FrameEtape frameEtape)
	{	
		JPanel panelInfo, panelAction;
		
		this.ctrl       = ctrl;
		this.frameEtape = frameEtape;

		this.setLayout(new BorderLayout(5, 5));
		
		/* Création des composants */
		panelInfo   = new JPanel(new GridLayout(1, 2, 5, 5));
		panelAction = new JPanel();

		this.txtNumEtape = new JTextField();
		this.btnValider = new JButton("Valider");

		/* Positionnement des composants */
		this.add(panelInfo,   BorderLayout.CENTER);
		this.add(panelAction, BorderLayout.SOUTH);

		panelInfo.add(new JLabel("Numéro de l'étape : ", SwingConstants.RIGHT));
		panelInfo.add(this.txtNumEtape);

		panelAction.add(this.btnValider);

		/* Activation des composants */
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnValider)
		{
			try {
				int numEtape = Integer.parseInt(this.txtNumEtape.getText());
				this.ctrl.parcourirEtape(numEtape);
				this.frameEtape.dispose();
			} catch (Exception ex) {System.out.println(ex);}
		}
	}
}
