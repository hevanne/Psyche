package controleur;

import metier.*;
import ihm.*;

public class Controleur
{
	private Jeu jeu;

	private FramePlateau framePlateau;
	private FrameJoueur frameJoueur;


	public Controleur()
	{
		this.jeu = new Jeu();

		this.framePlateau = new FramePlateau(this);
		this.frameJoueur = new FrameJoueur(this);
	}

	public static void main(String[] args)
	{
		Controleur ctrl = new Controleur();
		System.out.println("Hello World");
	}
}