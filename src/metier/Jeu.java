package metier;

import java.io.*;
import java.util.*;

import ihm.IHM;

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

	private List<String>     lstEtapes;

	public Jeu()
	{
		this.vocab   = new String[]{"Sommet","Ressource","Piece","Route"};
		this.images  = new String[7];

		this.lstJoueurs    = new ArrayList<Joueur>();
		this.lstCouleurs   = new ArrayList<Couleur>();
		this.lstRessources = new ArrayList<IRessource>();
		this.lstSommets    = new ArrayList<Sommet>();
		this.lstRoutes     = new ArrayList<Route>();
		
		this.lstEtapes     = new ArrayList<String>();

		this.initTheme();
		this.initMap();

		int indRes = 0;
		for(int i = 1; i < this.lstSommets.size(); i++)
		{
			indRes = (int)(Math.random() * this.lstRessources.size());
			this.lstSommets.get(i).setRessource(this.lstRessources.remove(indRes));
		}

		this.nouveauIHM();
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
			if(x >= smt.getX() && y >= smt.getY() && x <= smt.getX()+IHM.LARGEUR_SOMMET && y <= smt.getY()+IHM.HAUTEUR_SOMMET)
				return smt;

		return null;
	}

	public String getImagePlateauVierge () { return this.images[0]; }
	public String getImageDepart        () { return this.images[5]; } 
	public String getImagePiece         () { return this.images[6]; } 
	public String getImagePlateauJoueur (int numJoueur) { return this.images[1+numJoueur]; } 
	public String getImagePionJoueur    (int numJoueur) { return this.images[2+numJoueur]; } 

	// Autres Méthodes
	public void nouveauIHM()
	{
		this.numTour = 1;

		for( Joueur j   : this.lstJoueurs ) j.initJoueur();
		for( Sommet smt : this.lstSommets ) smt.reinit();
		for( Route  r   : this.lstRoutes  ) r.reinit();
		/*
		 * Le sommet d'indice 0 est toujours considéré le départ.
		 * setDepart lui ajoute un joueur invisible comme proprietaire.
		 * Cela permet d'éviter des bug qui peuvent appaître par le fait 
		 * que le sommet de Départ ne peut pas être pris par un joueur.
		 */
		this.getDepart().setDepart();
	}

	public void incrementerNumTour () { this.numTour++; }

	public boolean estFinJeu()
	{
		for (int i = 1; i < this.lstSommets.size(); i++)
			if (!this.lstSommets.get(i).aProprietaire())
				return false;

		return true;
	}

	public List<List<Sommet>> prendreSommet(Sommet smtDep, Sommet smtArr)
	{
		if(smtDep == null || smtArr == null) return null;

		Joueur             joueurActif;
		List<List<Sommet>> trajets;

		trajets     = this.plusCourtsTrajets(smtDep, smtArr);
		joueurActif = this.getJoueurActif();
		/*
		 * Pour prendre une mine, le joueur doit :
		 * - Partir soit d’une Mine qui a déjà été prise, soit de la nouvelle Rome.
		 * - Arriver sur une Mine qui n’a pas encore été prise, c’est-à-dire possédant toujours son jeton Mine.
		 * Il est tout à fait possible de passer par plusieurs Mines vides.
		 */
		if(!((smtDep == this.getDepart() || (smtDep != this.getDepart() && smtDep.aProprietaire()))
		   && !smtArr.aProprietaire()
		   && trajets.size() != 0)) return null;

		// /!\ Sommet.setProprietaire(joueur) s'occupe de l'ajout du sommet et du ressource au joueur
 		if(smtArr.setProprietaire(joueurActif))
		{
			System.out.println(trajets);
			return trajets;
		}
		return null;
	}

	public void affecterPrpRoute(List<Sommet> trajet) 
	{
		Sommet smtDep, smtArr;
		Route r;
		
		for(int i = 0; i < trajet.size()-1; i++)
		{
			smtDep = trajet.get(i);
			smtArr = trajet.get(i+1);
			r = smtDep.getRoute(smtArr);
			if(!r.aProprietaire())
				r.setProprietaire(this.getJoueurActif());
		}
	}
	
	// parcours le trajet en calculant les scores
	public void ajouterScoresTrajet(List<Sommet> trajet)
	{
		int[]  scores;
		Route  r;
		Sommet smtFin;
		
		scores = new int[2];
		smtFin = trajet.get(trajet.size()-1);
		
		for(int i = 0; i < trajet.size()-1; i++)
		{
			r = trajet.get(i).getRoute(trajet.get(i+1));
			scores[r.getProprietaire().getNum() - 1] += r.getNbSection();
		}
		
		if(	  smtFin != this.getDepart()
		   && smtFin.getRessource().getType() == 'R' 
		   && ((Ressource)smtFin.getRessource()).getDoubler())
			for(int i = 0; i < scores.length; i++)
				scores[i] = scores[i] * 2;

		System.out.println(scores[0]);
		System.out.println(scores[1]);
		for(int i = 0; i < this.lstJoueurs.size(); i++)
			this.lstJoueurs.get(i).varierScoreRoute(scores[i]);
	}

	public List<List<Sommet>> plusCourtsTrajets(Sommet smtDep, Sommet smtArr)
	{
		List<List<Sommet>> retour;
		
		Queue<Sommet> file;
		List<Sommet>  trajet;
		List<Sommet>  smtVoisins;
		Sommet        smt, tmp;

		boolean[] marque;
		int[]     indiceSmtPrec;


		retour = new ArrayList<List<Sommet>>();
		trajet = new ArrayList<Sommet>();
		
		marque        = new boolean[this.lstSommets.size()];
		indiceSmtPrec = new int    [this.lstSommets.size()];


		// Cf cours de graph
		// Parcours en largeur
		file = new LinkedList<Sommet>();
		file.add(smtDep);
		marque[smtDep.getNum()] = true;
		indiceSmtPrec[smtDep.getNum()] = -1;

		while(!file.isEmpty())
		{
			smt = file.poll();

			if(smt == smtArr)
			{
				trajet.clear();

				tmp = smtArr;
				while (indiceSmtPrec[tmp.getNum()] != -1) 
				{
					trajet.add(tmp);
					tmp = this.getSommet(indiceSmtPrec[tmp.getNum()]);
				}
				trajet.add(smtDep);

				if (retour.size() == 0 || trajet.size() == retour.get(0).size())
				{
					retour.add(new ArrayList<Sommet>(trajet));
				}
				else if (trajet.size() < retour.get(0).size())
				{
					retour.clear();
					retour.add(trajet);
				}
			}

			smtVoisins = smt.getVoisins();
			smtVoisins.removeIf(smtVoisin -> !smtVoisin.aProprietaire() && smtVoisin != smtArr);

			for(Sommet smtVoisin : smtVoisins)
			{
				if(!marque[smtVoisin.getNum()]) 
				{
					file.offer(smtVoisin);
					indiceSmtPrec[smtVoisin.getNum()] = smt.getNum();
					marque[smtVoisin.getNum()] = true;
				}
			}
		}

		return retour;
	}

	public List<List<Sommet>> trajetsSommetDepart(Sommet smtArr)
	{
		List<List<Sommet>> retour;
		Queue<Sommet> file;
		List<Sommet>  trajet;
		List<Sommet>  smtVoisins, smtVoisinsPrp;
		Sommet        smt, tmp;
		boolean[] marque;
		int[]     indiceSmtPrec;

		retour = new ArrayList<List<Sommet>>();
		trajet = new ArrayList<Sommet>();
		marque        = new boolean[this.lstSommets.size()];
		indiceSmtPrec = new int    [this.lstSommets.size()];

		// Parcours en largeur
		file = new LinkedList<Sommet>();
		file.add(smtArr);
		marque[smtArr.getNum()] = true;
		indiceSmtPrec[smtArr.getNum()] = -1;

		while(!file.isEmpty())
		{
			smt = file.poll();
			System.out.println(smt);

			if(smt == this.getDepart())
			{
				trajet.clear();
				tmp = this.getDepart();
				while (indiceSmtPrec[tmp.getNum()] != -1) 
				{
					trajet.add(tmp);
					tmp = this.getSommet(indiceSmtPrec[tmp.getNum()]);
				}
				trajet.add(smtArr);

				if (retour.size() == 0 || trajet.size() == retour.get(0).size())
				{
					retour.add(new ArrayList<Sommet>(trajet));
				}
				else if (trajet.size() < retour.get(0).size())
				{
					retour.clear();
					retour.add(trajet);
				}
			}

			smtVoisins = smt.getVoisins();
			smtVoisinsPrp = new ArrayList<Sommet>();

			for(Sommet voisin : smtVoisins)
				if(voisin.getRoute(smt).aProprietaire())
					smtVoisinsPrp.add(voisin);
			
			smtVoisinsPrp.removeIf(smtVoisin -> !smtVoisin.aProprietaire() && smtVoisin != smtArr);

			for(Sommet smtVoisin : smtVoisinsPrp)
			{
				if(!marque[smtVoisin.getNum()]) 
				{
					file.offer(smtVoisin);
					indiceSmtPrec[smtVoisin.getNum()] = smt.getNum();
					marque[smtVoisin.getNum()] = true;
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

	public void parcourirEtape(int etape)
	{
		String[] tabLig;
		Sommet   smtDep, smtArr;
		
		List<List<Sommet>> lstTrajets;
		int                indiceTrajetChoisi;

		if(etape <= 0)                    etape = 1;
		if(etape > this.lstEtapes.size()) etape = this.lstEtapes.size();

		this.nouveauIHM();
		for(this.numTour = 1; this.numTour < etape; this.numTour++)
			{
				tabLig = this.lstEtapes.get(this.numTour-1).split("\t");

				smtDep = getSommet(Integer.parseInt(tabLig[1].substring(0, 2)));
				smtArr = getSommet(Integer.parseInt(tabLig[2].substring(0, 2)));
				this.prendreSommet(smtDep, smtArr);

				lstTrajets = this.plusCourtsTrajets(smtArr, this.getDepart());
				indiceTrajetChoisi = 0;
				if(tabLig.length == 4)
					indiceTrajetChoisi = Integer.parseInt(tabLig[3]);

				this.affecterPrpRoute(lstTrajets.get(indiceTrajetChoisi));
			}
	}

	public void ajouterEtape(Sommet smtDep, Sommet smtArr, Integer indiceTrajetChoisi)
	{
		String str = this.numTour + "\t" + smtDep.getNom() + "\t" + smtArr.getNom();
		if (indiceTrajetChoisi != null) 
			str += "\t" + indiceTrajetChoisi;

		this.lstEtapes.add(str);
		this.sauvegarder();
	}

	public void sauvegarder()
	{
		try
		{
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("../data/etapes.data"), "UTF8" ));

			for (String str : this.lstEtapes)
				pw.println(str);
			
			pw.close();
		}
		catch (Exception e){}
	}
}