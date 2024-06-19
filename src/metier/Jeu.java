package metier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

import controleur.Controleur;

import ihm.IHM;
import java.io.InputStream;

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

	private Controleur ctrl;

	public Jeu(Controleur ctrl)
	{
		this.ctrl = ctrl;
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

	public Jeu(int numScenario)
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

		this.nouveauIHM(numScenario);
	}

	// Accesseurs
	public Sommet getDepart      () { return this.lstSommets.get(0); }
	public int    getNumTour     () { return this.numTour;                 }
	public int    getNbJoueur    () { return this.lstJoueurs.size();       }
	public Joueur getJoueurActif () { return this.lstJoueurs.get((this.numTour+1) % 2); }

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

	public void nouveauIHM(int numScenario)
	{
		Scanner scFic;
		String  lig, tabLig[], nom;
		Sommet  smtDep, smtArr;
		Couleur couleur;
		boolean doubler;
		Joueur joueur;
		int cptLig = 0;

		for( Sommet smt : this.lstSommets ) { smt.reinit(); }
		for( Route  r   : this.lstRoutes  )  r.reinit();
		for( Joueur j   : this.lstJoueurs ) { j.initJoueur(); }

		try
		{
			scFic = new Scanner(new FileInputStream ( "../src/scenario_" + numScenario + ".txt" ));
			
			lig = scFic.nextLine();
			while(!"".equals(lig))
			{
				if( lig.charAt(0) != '#' )
				{
					tabLig = lig.split("\t");

					nom      = tabLig[2];
					couleur  = this.lstCouleurs.get(Integer.parseInt(tabLig[3]));
					doubler  = false;
					if (tabLig.length == 5) doubler = true;
					
					if(tabLig[1].charAt(0) == 'R')
					{
						getSommet(Integer.parseInt(tabLig[0])).setRessource(new Ressource(nom, couleur, doubler));
					}
					if(tabLig[1].charAt(0) == 'P')
					{
						getSommet(Integer.parseInt(tabLig[0])).setRessource(new Piece (1, couleur));
					}

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

					smtDep = getSommet(Integer.parseInt(tabLig[1].substring(0, 2)));
					smtArr = this.lstSommets.get(Integer.parseInt(tabLig[2].substring(0, 2)));

					joueur = getJoueur(Integer.parseInt(tabLig[0]) % 2);

					smtArr.setProprietaire(joueur);
					smtDep.getRoute(smtArr).setProprietaire(joueur);

					cptLig++;
				}
				lig = scFic.nextLine();
			}

			scFic.close();
		}
		catch (Exception e){e.printStackTrace(System.out);}

		this.numTour = cptLig;
		this.getDepart().setDepart();
	}


	public void incrementerNumTour () 
	{ 
		this.numTour++; 
		if(this.getJoueurActif().getNbPions() <= 0) this.numTour++;
	}

	public boolean estFinJeu()
	{
		if(   this.lstJoueurs.get(0).getNbPions() <= 0
		   && this.lstJoueurs.get(0).getNbPions() <= 0) 
		   return false;
		
		for (int i = 1; i < this.lstSommets.size(); i++)
			if (!this.lstSommets.get(i).aProprietaire())
				return false;

		return true;
	}

	public List<Sommet> prendreSommet(Sommet smtDep, Sommet smtArr)
	{
		if(smtDep == null || smtArr == null) return null;

		Joueur             joueurActif;
		List<List<Sommet>> trajets;
		List<Sommet>       retour;

		trajets     = this.getTrajets(smtDep, smtArr, false);
		joueurActif = this.getJoueurActif();

		/*
		 * Pour prendre une mine, le joueur doit :
		 * - Partir soit d’une Mine qui a déjà été prise, soit de la nouvelle Rome.
		 * - Arriver sur une Mine qui n’a pas encore été prise, c’est-à-dire possédant toujours son jeton Mine.
		 * Il est tout à fait possible de passer par plusieurs Mines vides.
		 */
		if(!((smtDep == this.getDepart() || (smtDep != this.getDepart() && smtDep.aProprietaire()))
		   && !smtArr.aProprietaire()		  
		   && trajets != null   
		   && trajets.size() != 0)) 
		return null;

		// On récupère le trajet
		retour = trajets.get(0);
		if(trajets.size() > 1)
		{
			for(List<Sommet> trajet : trajets)
				if(trajet.size() < retour.size()) retour = trajet;
		}
		
		// Verifier que le joueur possède suffisament de pions
		if(Jeu.calculerCoutTrajet(retour) == -1 && Jeu.calculerCoutTrajet(retour) > this.getJoueurActif().getNbPions())
			return null;

		// /!\ Sommet.setProprietaire(joueur) s'occupe de l'ajout du sommet et du ressource au joueur
 		if(!smtArr.setProprietaire(joueurActif))
			return null;

		return retour;
		
	}

	private static int calculerCoutTrajet(List<Sommet> trajet)
	{
		if(Jeu.trajetRouteExiste(trajet)) return -1;

		Route r;
		int   retour;
		
		retour = 0;
		
		for(int i = 0; i < trajet.size() - 1; i++)
		{
			r = trajet.get(i).getRoute(trajet.get(i+1));
			if(!r.aProprietaire())
				retour += r.getNbSection();
		}

		return retour;
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
			{
				this.getJoueurActif().varierNbPions(-r.getNbSection());
				r.setProprietaire(this.getJoueurActif());
			}
		}
	}
	
	// parcours le trajet en calculant les scores
	public void ajouterScoresTrajet(List<Sommet> trajet, Sommet smtFin)
	{
		int[]  scores;
		Route  r;
		
		scores = new int[2];
		
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

		for(int i = 0; i < this.lstJoueurs.size(); i++)
			this.lstJoueurs.get(i).varierScoreRoute(scores[i]);
	}

	public List<List<Sommet>> getTrajets(Sommet smtDep, Sommet smtArr, boolean routePrp)
	{
		List<List<Sommet>> retour;
		
		Queue<Sommet> file;
		List<Sommet>  parcours, trajet, smtVoisins;
		Sommet        smt, tmp;

		boolean[] marque;
		int[]     indiceSmtPrec;

		retour        = new ArrayList<List<Sommet>>();
		trajet        = new ArrayList<Sommet>();
		parcours      = new ArrayList<Sommet>();
		marque        = new boolean[this.lstSommets.size()];
		indiceSmtPrec = new int    [this.lstSommets.size()];

		// Parcours en largeur
		file = new LinkedList<Sommet>();
		file.add(smtDep);
		marque[smtDep.getNum()]  = true;
		indiceSmtPrec[smtArr.getNum()] = -1;
		for(int i = 0; i < indiceSmtPrec.length; i++)
			indiceSmtPrec[i] = -1;
		

		while(!file.isEmpty())
		{
			smt = file.poll();
			parcours.add(smt);

			if(smt == smtArr)
			{
				smtVoisins = smtArr.getVoisins();

				for(Sommet smtVoisin : smtVoisins)
				{
					trajet.clear();
					trajet.add(smtArr);
					tmp = smtVoisin;
					trajet.add(tmp);
					while (indiceSmtPrec[tmp.getNum()] != -1) 
					{
						tmp = this.getSommet(indiceSmtPrec[tmp.getNum()]);
						trajet.add(tmp);
					}
					Collections.reverse(trajet);
 
					if(routePrp && trajet.get(0) == smtDep && Jeu.trajetRouteExiste(trajet))
					{
						if(retour.size() != 0 && retour.get(0).size() <= trajet.size())
							retour.clear();	
						retour.add(new ArrayList<Sommet>(trajet));
					}
					else if(!routePrp && trajet.get(0) == smtDep)
					{
						if(retour.size() != 0 && retour.get(0).size() <= trajet.size())
							retour.clear();	
						retour.add(new ArrayList<Sommet>(trajet));
					}
				}
				return retour;
			}

			smtVoisins = smt.getVoisins();

			if(routePrp)
			{
				List<Sommet> lstTmp = new ArrayList<Sommet>();
				
				for(Sommet smtVoisin : smtVoisins)
					if(smtVoisin.getRoute(smt).aProprietaire())
						lstTmp.add(smtVoisin);

				smtVoisins = lstTmp;
			}

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

		return null;
	}

	private static boolean trajetRouteExiste(List<Sommet> trajet)
	{		
		for(int i = 0; i < trajet.size() - 1; i++)
			if(!trajet.get(i).getRoute(trajet.get(i+1)).aProprietaire())
				return false;

		return true;
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
				if( lig.charAt(0) != '#')
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

				lstTrajets = this.getTrajets(smtArr, this.getDepart(), false);
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

	public void ajouterVille(List<Sommet> lstS)
	{
		for (int i = 0; i < lstS.size() - 1; i++) {
			for (int j = 0; j < lstS.size() - i - 1; j++) {
				if (lstS.get(j).getNum() < lstS.get(j + 1).getNum()) {
					Sommet temp = lstS.get(j);
					lstS.set(j, lstS.get(j + 1));
					lstS.set(j + 1, temp);
				}
			}
		}
		String sRet = "";
		sRet = 		"# SOMMETS" + "\n" +
					"# indice \\t valeur \\t indice couleur \\t x \\t y" + "\n" +
					"# Sommet de départ" + "\n" +
					"# region jaune" + "\n" +
					"# region bleue"+ "\n" +
					"# region grise"+ "\n" +
					"# region verte"+ "\n" +
					"# region rouge"+ "\n" +
					"# region orange"+ "\n" +
					"\n" +
					"# ROUTES"+ "\n" +
					"# '/!\' On utilise les noms des sommets pour aider avec la lisibilité"+ "\n" +
					"#     (Un nom est plus parlant qu'une indice)"+ "\n" +
					"# sommet_depart (indice+couleur+valeur) \\t sommet_arrivee (couleur+valeur) \\t nombre_sections";


		for (Route r: this.lstRoutes)
		{
			sRet += "\n" + r.getSmtDep().getNom() + "\t" + r.getSmtArr().getNom() + "\t" + r.getNbSection();
		}

		this.ecrire(sRet);
		sRet = "# SOMMETS";
		for(Sommet s: lstS)
		{
			try
			{
				Scanner sc = new Scanner ( new FileInputStream ( "../theme/map.txt" ) );
				String ligne = sc.nextLine();
	
				while ( sc.hasNextLine() )
				{
					ligne = sc.nextLine();
					sRet += "\n" + ligne;
					if (s.getNum() == 0 && ligne.equals("# Sommet de départ"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t" + 0 +"\t" + s.getX() + "\t" + s.getY();
					}

					int cpt = 0;
					int couleur = 0;
					for (Couleur c: this.lstCouleurs)
					{
						if (c.getNom().equals(s.getCouleur().getNom()))
						{
							couleur = cpt;
						}
						cpt++;
					}

					if (couleur == 1 && ligne.equals("# region jaune") && s.getNum() != 0)
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
					if (couleur == 2 && ligne.equals("# region bleue"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
					if (couleur == 3 && ligne.equals("# region grise"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
					if (couleur == 4 && ligne.equals("# region verte"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
					if (couleur == 5 && ligne.equals("# region rouge"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
					if (couleur == 6 && ligne.equals("# region orange"))
					{
						sRet += "\n" + s.getNum() + "\t" + s.getValeur() + "\t"+ couleur + "\t" + s.getX() + "\t" + s.getY();
					}
				}
				this.ecrire(sRet);
	
				sc.close();
				sRet = "# SOMMETS";
			}
			catch (Exception e){ e.printStackTrace(); }
		}
		
	}

	private void ecrire(String s)
	{
		try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("../theme/map.txt") );

			pw.println ( s );


			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public boolean sommetExiste(int x, int y)
	{

		for(Sommet s: this.lstSommets)
		{
			if (x == s.getX() && y==s.getY())
			{
				System.out.println("sommet deja existant");
				return true;
			}
		}
		return false;
	}

	public boolean routeExiste(String sommetD, String sommetA)
	{
		for (Route r: this.lstRoutes)
		{
			if (sommetD.equals(r.getSmtDep().getNom()) && sommetA.equals(r.getSmtArr().getNom()))
				return true;
		}
		return false;
	}

	public void ajouterRoute(String sommetD, String sommetA, String tronc)
	{
		String sRet = "";
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "../theme/map.txt" ) );

			while ( sc.hasNextLine() )
			{
				String ligne = sc.nextLine();
				sRet += ligne + "\n";
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }

		if (!routeExiste(sommetD, sommetA))
		{
			sRet += sommetD + "\t" + sommetA + "\t" + tronc;
			//System.out.println("sommet existe pas");
			//System.out.println(sRet);
			this.ecrire(sRet);
			Sommet sommetTmpD, sommetTmpA;
			sommetTmpD = sommetTmpA = null;
			for (Sommet s: this.lstSommets)
			{
				if(sommetA.equals(s.getNom()))
				{
					sommetTmpA = s;
					System.out.println("alright2");
				}

				if(sommetD.equals(s.getNom()))
				{
					sommetTmpD = s;
					System.out.println("alrigth1");
				}
			}

			this.lstRoutes.add(Route.nvRoute(sommetTmpD, sommetTmpA, Integer.parseInt(tronc)));
		}

	}

	public void resetNumSommet()
	{
		Sommet.resetNum();
	}

	public Couleur getCouleur(int i)
	{
		return this.lstCouleurs.get(i);
	}
}