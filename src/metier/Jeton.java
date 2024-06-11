package metier;

public class Jeton {
	private IRessource type;

	public Jeton (IRessource type)
	{
		this.type = type;
	}

	public IRessource getType()
	{
		return this.type;
	}

	public String toString()
	{
		return this.type.toString();
	}
}
