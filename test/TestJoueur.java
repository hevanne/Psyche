import metier.*;

public class TestJoueur
{
	public static void main(String[] args)
	{
		Joueur j1 = new Joueur("Joueur");
		Couleur marron = new Couleur("MARRON", 12, 157, 56);
		Couleur jaune = new Couleur("JAUNE", 210, 138, 96);
		Couleur gris = new Couleur("GRIS", 100, 45, 233);
		Couleur vert = new Couleur("VERT", 213, 44, 129);
		Couleur bleu = new Couleur("BLEU", 20, 144, 203);
		Couleur beige = new Couleur("BEIGE", 214, 10, 110);

		j1.ajouterRessource(new Piece(1, jaune));
		j1.ajouterRessource(new Piece(1, jaune));

		j1.ajouterRessource(new Ressource("Cobalt", marron, false));
		j1.ajouterRessource(new Ressource("Cobalt", marron, false));

		j1.ajouterRessource(new Ressource("Or", jaune, true));
		j1.ajouterRessource(new Ressource("Or", jaune, true));
		j1.ajouterRessource(new Ressource("Or", jaune, true));

		j1.ajouterRessource(new Ressource("Argent", gris, false));
		j1.ajouterRessource(new Ressource("Argent", gris, false));
		j1.ajouterRessource(new Ressource("Argent", gris, false));

		j1.ajouterRessource(new Ressource("Titane", vert, false));
		j1.ajouterRessource(new Ressource("Titane", vert, false));

		j1.ajouterRessource(new Ressource("Nickel", bleu, false));
		j1.ajouterRessource(new Ressource("Nickel", bleu, false));

		j1.ajouterRessource(new Ressource("Aluminium", beige, false));

		j1.calculerScore();

		System.out.println("Score du joueur : " + j1.getScore());

		System.out.println(j1);
		j1.triTabRessource();
		System.out.println(j1);
	}
}