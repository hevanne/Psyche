package ihm;

import java.util.List;
import java.util.Scanner;

import controleur.Controleur;
import metier.Sommet;

public class CUI 
{
	private Controleur ctrl;

	public CUI(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.init();
	}

	public void init()
	{
		Scanner  sc = new Scanner(System.in);
		boolean  bOk;
		String[] saisie;

		while(!this.ctrl.estFinJeu())
		{
			this.ctrl.incrementerNumTour();

			System.out.println("------------- | Debut Tour | ---------------");
			System.out.println("Tour n°" + this.ctrl.getNumTour());
			System.out.println(this.ctrl.getJoueurActif().getNom() + " joue");

			saisie = new String[3];

			
			bOk = false;
			System.out.println("Saisissez votre action : ");
			// prendre un sommet : P nom_smtDep nom_smtArr;
			// cf map.txt pour syntaxe symbole
			do
			{
				for(int i = 0; i < saisie.length; i++)
					saisie[i] = sc.next();
				
				bOk = this.gereAction(saisie[0].charAt(0), saisie[1], saisie[2]);
				if(!bOk)
				{
					System.out.println("ERREUR : resaissez");
					System.out.println("Format : char_action String_1 String_2");
				}
			}
			while(!bOk);


	
			System.out.println("------------- |  Fin Tour  | ---------------");
			System.out.println();
			System.out.println();
		}

		sc.close();
		this.ctrl.afficherScore();
	}

	public boolean gereAction(char action, String s1, String s2)
	{
		Sommet             smtDep, smtArr;
		List<List<Sommet>> lstTrajets;
		int[]              scores;
		boolean            retour;

		retour = false;

		if(action == 'P')
		{
			smtDep = this.ctrl.getSommet(Integer.parseInt(s1.substring(0, 2)));
			smtArr = this.ctrl.getSommet(Integer.parseInt(s2.substring(0, 2)));
			retour = this.ctrl.prendreSommet(smtDep, smtArr);

			if(retour == true)
			{
				System.out.println(this.ctrl.getVocab(0) + " " + smtArr + " est pris(e) par " + this.ctrl.getJoueurActif().getNom());
				System.out.println(this.ctrl.getJoueurActif());

				lstTrajets = this.ctrl.plusCourtsChemins(smtArr);

				if(lstTrajets.size() == 1) 
					scores = this.ctrl.calculerScoresTrajet(lstTrajets.get(0));
				else
				{
					String str;
					for(int i = 0; i < lstTrajets.size(); i++)
					{
						str = "" + (i+1) + " : " + lstTrajets.get(i);
						System.out.println(str);
					}
					System.out.println("Sélectionner le numéro du trajet souhaité : ");
					
					Scanner scTrajet  = new Scanner (System.in);
					int     numTrajet = scTrajet.nextInt();
					scTrajet.close();

					scores = this.ctrl.calculerScoresTrajet(lstTrajets.get(numTrajet));
				}

				for(int i = 0; i < scores.length; i++) 
					System.out.println(scores[i]);
			}
		}

		return retour;
	}
}