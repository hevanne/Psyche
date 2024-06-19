import controleur.Controleur;

public class Psyche
{
	public static void main(String[] args)
	{
		if (args.length > 0)
		{
			switch (args[0])
			{
				case "scenario_1.run":
					System.out.println("scenario 1");
					new Controleur(1);
					break;

				case "scenario_2.run":
					System.out.println("scenario 2");
					new Controleur(2);
					break;

				case "scenario_3.run":
					System.out.println("scenario 3");
					new Controleur(3);
					break;
			}
		}
		else { new Controleur(); }
	}
}