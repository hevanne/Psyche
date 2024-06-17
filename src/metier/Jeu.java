package metier;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Jeu
{
	private int      numTour;
	private String[] vocab;
	private String[] images;

	private List<Joueur>     lstJoueurs;
	private List<Couleur>    lstCouleurs;
	private List<IRessource> lstRessources;
	private List<Sommet>     lstSommets;
	private List<Route>      lstRoutes;

	public Jeu()
	{
		this.vocab   = new String[]{"Sommet","Ressource","Piece","Route"};
		this.images  = new String[5];

		this.lstJoueurs    = new ArrayList<Joueur>();
		this.lstCouleurs   = new ArrayList<Couleur>();
		this.lstRessources = new ArrayList<IRessource>();
		this.lstSommets    = new ArrayList<Sommet>();
		this.lstRoutes     = new ArrayList<Route>();

		this.nouveauJeu();
	}

	// Accesseurs
	public Sommet getDepart      () { return this.lstSommets.get(0); }
	public int    getNumTour     () { return this.numTour;                 }
	public int    getNbJoueur    () { return this.lstJoueurs.size();       }
	public Joueur getJoueurActif () { return this.lstJoueurs.get((this.numTour+1) % (this.lstJoueurs.size())); }

	public Joueur getJoueur (int indice)     { return this.lstJoueurs.get(indice); }
	public String getVocab  (int indice)     { return this.vocab[indice];          }
	public Sommet getSommet (String symbole)
	{
		char   couleur;
		int    valeur;

		couleur = symbole.charAt(0);
		valeur  = Integer.parseInt(symbole.substring(1));

		for(Sommet smt : this.lstSommets)
			if(   smt.getCouleur().getNom().charAt(0) == couleur 
			   && smt.getValeur()                           == valeur)
			   return smt;

		return null;
	}

	public List<Sommet> getSommetsPrp()
	{
		List<Sommet> retour;
		Sommet       s;

		retour = new ArrayList<Sommet>();
		retour.add(this.getDepart());

		for(int i = 0; i < this.lstSommets.size(); i++)
		{
			s = this.lstSommets.get(i);
			if(s.aProprietaire())
				retour.add(s);
		}

		return retour;
	}

	// Autres Méthodes
	public void nouveauJeu()
	{
		this.numTour = 0;
		this.initTheme();
		this.initMap();

		System.out.println("Créations des listes : ");
		System.out.println(this.lstJoueurs);
		System.out.println(this.lstCouleurs);
		System.out.println(this.lstRessources);
		System.out.println(this.lstSommets);
		System.out.println(this.lstRoutes);

		/*
		 * Le sommet d'indice 0 est toujours considéré le départ.
		 * setDepart lui ajoute un joueur invisible comme proprietaire.
		 * Cela permet d'éviter des bug qui peuvent survenir par le fait 
		 * que le sommet de Départ ne peut pas être pris par un joueur.
		 */
		this.getDepart().setDepart();

		int indRes = 0;
		for(int i = 1; i < this.lstSommets.size(); i++)
		{
			indRes = (int)(Math.random() * this.lstRessources.size());
			this.lstSommets.get(i).setRessource(this.lstRessources.remove(indRes));
		}
	}

	public void incrementerNumTour () { this.numTour++; }

	public boolean estFinJeu()
	{
		for (int i = 1; i < this.lstSommets.size(); i++)
			if (!this.lstSommets.get(i).aProprietaire())
				return false;

		return true;
	}

	public boolean prendreSommet(Sommet smtDep, Sommet smtArr)
	{
		if(smtDep == null || smtArr == null) return false;

		Joueur joueurActif;
		Route  route;

		route       = smtDep.getRoute(smtArr);
		joueurActif = this.getJoueurActif();

		/*
		 * Pour prendre une mine, le joueur doit :
		 * - Partir soit d’une Mine qui a déjà été prise, soit de la nouvelle Rome.
		 * - Arriver sur une Mine qui n’a pas encore été prise, c’est-à-dire possédant toujours son jeton Mine.
		 * Il est tout à fait possible de passer par plusieurs Mines vides.
		 */
		if(!((smtDep == this.getDepart() || (smtDep != this.getDepart() && smtDep.aProprietaire()))
		   && !smtArr.aProprietaire()
		   && !route.aProprietaire())) return false;

		   System.out.println(smtArr);
		   System.out.println(smtArr.getRessource());
		// /!\ Sommet.setProprietaire(joueur) s'occupe de l'ajout du sommet et du ressource au joueur
 		if(smtArr.setProprietaire(joueurActif))
		{
			route.setProprietaire(joueurActif);
			return true;
		}
		return false;
	}

	// trouver le plus court trajet entre smt et Nouvelle Rome
	// parcourir le trajet en ajoutant les points
	// /!\ Mines d'or
	public int[] calculerScoresTrajet(List<Sommet> trajet)
	{
		int[]    scores;
		Route    r;
		
		scores = new int[this.lstJoueurs.size()];

		for(int i = 0; i < trajet.size()-1; i++)
		{
			r = trajet.get(i).getRoute(trajet.get(i+1));
			scores[r.getProprietaire().getNum() - 1] += r.getNbSection();
		}

		for(int i = 0; i < scores.length; i++)
		{
			this.lstJoueurs.get(i).varierScoreRoute(scores[i]);
		}

		return scores;
	}

	public List<List<Sommet>> plusCourtsChemins(Sommet smt)
	{
		Queue<Sommet>  file;
		Sommet         s;
		List<Sommet>   trajet, voisins, marque;
		List<List<Sommet>> retour;

		retour = new ArrayList<List<Sommet>>();
		
		marque = new ArrayList<Sommet>();
		trajet = new ArrayList<Sommet>();
		trajet.add(smt);

		// Cf cours de graph
		// Parcours en largeur
		file = new LinkedList<Sommet>();
		file.add(smt);
		marque.add(smt);
		while(!file.isEmpty())
		{
			s = file.remove();
			voisins = s.getVoisinsPrp();
			for(int i = 0; i < voisins.size(); i++)
			{
				s = voisins.get(i);

				if(s == this.getDepart())
				{
					trajet.add(s);

					if (retour.size() == 0 || trajet.size() == retour.get(0).size())
					{
						retour.add(new ArrayList<Sommet>(trajet));
					}
					else if (trajet.size() < retour.get(0).size())
					{
						retour.clear();
						retour.add(trajet);
					}

					trajet.removeLast();
				}
				else if(marque.indexOf(s) == -1) 
				{
					file.add(s);
					trajet.add(s);
					marque.add(s);
				}
			}
		}

		return retour;
	}

	private void initTheme ()
	{
		Scanner scFic;
		String  lig, tabLig[], nom;
		int     i, r, v, b, quantite;
		Couleur couleur;

		try
		{
			scFic = new Scanner(new FileInputStream ( "../theme/theme.txt" ));

			// Vocabulaire
			i = 0;
			lig = scFic.nextLine(); 
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
					this.vocab[i++] = this.getDonnee(lig);
				lig = scFic.nextLine();
			}

			// Joueur
			lig = scFic.nextLine();
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
					this.lstJoueurs.add(new Joueur(this.getDonnee(lig)));
				lig = scFic.nextLine();
			}

			// Couleurs
			lig = scFic.nextLine();
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
				{
					tabLig = lig.split("\t");
					
					nom = tabLig[1];
					r   = Integer.parseInt(tabLig[2]);
					v   = Integer.parseInt(tabLig[3]);
					b   = Integer.parseInt(tabLig[4]);
					this.lstCouleurs.add(new Couleur(nom, r, v, b));
				}
				lig = scFic.nextLine();
			}

			// Ressource
			lig = scFic.nextLine();
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
				{
					tabLig = lig.split("\t");

					nom      = tabLig[1];
					couleur  = this.lstCouleurs.get(Integer.parseInt(tabLig[2]));
					quantite = Integer.parseInt(tabLig[3]);
					
					if(tabLig[0].charAt(0) == 'R')
						for(i = 0; i < quantite; i++)
							this.lstRessources.add(new Ressource(nom, couleur));
					if(tabLig[0].charAt(0) == 'P')
						for(i = 0; i < quantite; i++)
							this.lstRessources.add(new Piece    (1, couleur));
				}
				lig = scFic.nextLine();
			}

			// Images
			i = 0;
			lig = scFic.nextLine(); 
			while(scFic.hasNextLine())
			{
				if( lig.charAt(0) != '#' )
					this.images[i++] = this.getDonnee(lig);
				lig = scFic.nextLine();
			}

			scFic.close();
		} catch (Exception e) {System.out.println(e);}
	}

	private String getDonnee(String lig)
	{	
		String retour;

		if ( !lig.contains("=") )
			retour = "";	
		else if ( lig.contains("\"") )
			retour = lig.substring(lig.indexOf("\"")+1, lig.lastIndexOf("\"")).trim();
		else 
			retour = lig.substring(lig.indexOf("=")+1).trim();

		return retour;
	}

	public void initMap()
	{
		Scanner scFic;
		String  lig, tabLig[];
		int     valeur, x, y, nbSection;
		Sommet  smtDep, smtArr;
		Couleur couleur;
		try
		{
			scFic = new Scanner(new FileInputStream ( "../theme/map.txt" ));
			
			// Sommets
			valeur = x = y = 0;
			lig = scFic.nextLine();
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
				{
					tabLig = lig.split("\t");

					valeur  = Integer.parseInt(tabLig[1]);
					couleur = this.lstCouleurs.get(Integer.parseInt(tabLig[2]));
					x       = Integer.parseInt(tabLig[3]);
					y       = Integer.parseInt(tabLig[4]);

					this.lstSommets.add(Sommet.nvSommet(valeur, couleur, x, y));
				}
				lig = scFic.nextLine();
			}

			// Routes
			lig = scFic.nextLine();
			while(scFic.hasNextLine() && !"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
				{
					tabLig = lig.split("\t");

					if(tabLig[0].length() == 1 && tabLig[1].length() == 1)
					{
						smtDep    = this.lstSommets.get(Integer.parseInt(tabLig[0]));
						smtArr    = this.lstSommets.get(Integer.parseInt(tabLig[1]));
					}
					else
					{
						smtDep    = this.getSommet(tabLig[0]);
						smtArr    = this.getSommet(tabLig[1]);
					}
					nbSection = Integer.parseInt(tabLig[2]);

					this.lstRoutes.add(Route.nvRoute(smtDep, smtArr, nbSection));
				}
				lig = scFic.nextLine();
			}

			scFic.close();
		}
		catch (Exception e){e.printStackTrace(System.out);}
	}
}