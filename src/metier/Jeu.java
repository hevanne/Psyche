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

	public Joueur getJoueur (int i) { return this.lstJoueurs.get(i); }
	public String getVocab  (int i) { return this.vocab[i];          }
	public Sommet getSommet (int i) { return this.lstSommets.get(i); }

	public List<Sommet> getLstSommets() {return new ArrayList<Sommet> (this.lstSommets); }
	public List<Route>  getLstRoutes () {return new ArrayList<Route>  (this.lstRoutes); }

	public Sommet getSommet(int x, int y)
	{
		for(Sommet smt : this.lstSommets)
			//if(smt.getX() == x && smt.getY() == y)
			if(Jeu.distance(x, y, smt.getX(), smt.getY()) <= Sommet.RAYON_SOMMET)
				return smt;

		return null;
	}

	private static double distance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
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
		this.setCheminsImage();
		this.initMap();

		/*
		 * Le sommet d'indice 0 est toujours considéré le départ.
		 * setDepart lui ajoute un joueur invisible comme proprietaire.
		 * Cela permet d'éviter des bug qui peuvent appaître par le fait 
		 * que le sommet de Départ ne peut pas être pris par un joueur.
		 */
		this.getDepart().setDepart();

		int indRes = 0;
		for(int i = 1; i < this.lstSommets.size(); i++)
		{
			indRes = (int)(Math.random() * this.lstRessources.size());
			this.lstSommets.get(i).setRessource(this.lstRessources.remove(indRes));
		}

		System.out.println("Créations des listes : ");
		System.out.println(this.lstJoueurs);
		System.out.println(this.lstCouleurs);
		System.out.println(this.lstRessources);
		System.out.println(this.lstSommets);
		System.out.println(this.lstRoutes);
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
		if(smtDep == null || smtArr == null || smtDep.getRoute(smtArr) == null) return false;

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

		if(   trajet.get(0).getRessource().getType() == 'R' 
		   && ((Ressource)trajet.get(0).getRessource()).getDoubler())
			for(int i = 0; i < scores.length; i++)
				scores[i] = scores[i] * 2;

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

					if (!trajet.isEmpty()) {
						trajet.remove(trajet.size() - 1);
					}
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
		boolean doubler;


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
					doubler  = false;
					if (tabLig.length == 5) doubler = true;
					
					if(tabLig[0].charAt(0) == 'R')
						for(i = 0; i < quantite; i++)
							this.lstRessources.add(new Ressource(nom, couleur, doubler));
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

	private void setCheminsImage()
	{
		this.lstJoueurs.get(0).setCheminImage(this.images[1]);
		this.lstJoueurs.get(1).setCheminImage(this.images[2]);
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

					smtDep    = this.lstSommets.get(Integer.parseInt(tabLig[0].substring(0, 2)));
					smtArr    = this.lstSommets.get(Integer.parseInt(tabLig[1].substring(0, 2)));

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