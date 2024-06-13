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
	public int    getNumTour     () { return this.numTour; }
	public Joueur getJoueurActif () { return this.lstJoueurs.get((this.numTour+1) % (this.lstJoueurs.size())); }
	public int    getNbJoueur    () { return this.lstJoueurs.size(); }

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
			if (this.lstSommets.get(i).getProprietaire() == null)
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
		 *  Pour prendre une mine, le joueur doit :
		 * - Partir soit d’une Mine qui a déjà été prise, soit de la nouvelle Rome.
		 * - Arriver sur une Mine qui n’a pas encore été prise, c’est-à-dire possédant toujours son jeton Mine.
		 * Il est tout à fait possible de passer par plusieurs Mines vides.
		 */
		if(!((smtDep == this.lstSommets.get(0) || (smtDep != this.lstSommets.get(0) && smtDep.getProprietaire() != null))
		   && smtArr.getProprietaire() == null
		   && route.getProprietaire()  == null)) return false;

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
	private void calculerScoreTrajet(Sommet[] trajet)
	{
		int[]    scoresRoute;
		Route    r;
		
		scoresRoute = new int[this.lstJoueurs.size()];

		for(int i = 0; i < trajet.length-1; i++)
		{
			r = trajet[i].getRoute(trajet[i+1]);
			scoresRoute[r.getProprietaire().getNum() - 1] += r.getNbSection();
		}

		for(int i = 0; i < scoresRoute.length; i++)
		{
			this.lstJoueurs.get(i).varierScoreRoute(scoresRoute[i]);
		}
	}

	public List<Sommet[]> plusCourtChemin(Sommet smt)
	{
		Sommet []      voisins, tabTrajet;
		boolean[]      marque;
		Queue<Sommet>  file;
		Sommet         s;
		List<Sommet>   trajet;
		List<Sommet[]> retour;

		retour = new ArrayList<Sommet[]>();
		trajet = new ArrayList<Sommet>();
		marque = new boolean[this.lstSommets.size()];
		tabTrajet = null;

		// Cf cours de graph
		// Parcours en largeur
		file = new LinkedList<Sommet>();
		file.offer(smt);
		marque[smt.getNum()] = true;
		while(!file.isEmpty())
		{
			s = file.poll();
			voisins = s.getVoisins();
			for(int i = 0; i < voisins.length; i++)
			{
				if(voisins[i] == this.lstSommets.get(0))
				{
					trajet.add(voisins[i]);

					tabTrajet = ((Sommet[])trajet.toArray());
					if (retour.size() == 0 || tabTrajet.length == retour.get(0).length)
					{
						retour.add(tabTrajet);
					}
					else if (tabTrajet.length < retour.get(0).length)
					{
						retour.clear();
						retour.add(tabTrajet);
					}

					trajet.removeLast();
				}
				else if(!marque[i]) 
				{
					file.offer(voisins[i]);
					marque[i] = true;
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