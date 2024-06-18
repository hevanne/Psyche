package ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.*;

import controleur.Controleur;

public class PanelEtape extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JLabel     lblNumEtape;
	private JTextField txtNumEtape;
	private JButton    btnValider;

	public PanelEtape(Controleur ctrl)
	{
		this.setLayout(new GridLayout(1,3));

		this.ctrl = ctrl;
		
		this.lblNumEtape = new JLabel("Numéro de l'étape : ", SwingConstants.RIGHT);
		this.txtNumEtape = new JTextField();
		this.btnValider = new JButton("Valider");

		this.add(this.lblNumEtape);
		this.add(this.txtNumEtape);
		this.add(this.btnValider);

		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnValider)
		{
			try {
				int numEtape = Integer.parseInt(this.txtNumEtape.getText());
				this.ctrl.parcourirEtape(numEtape);
			} catch (Exception ex) {System.out.println(ex);}
		}
	}
}
