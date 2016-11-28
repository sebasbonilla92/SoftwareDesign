
public abstract class Player {
	
	private String Name = null;
	private char Token = 0;	
	
	/************************************************************************/
	
	public Player (String Name, char Token){
		this.Name = Name;
		this.Token = Token;
	}
	
	public abstract TicLocation move(Board board);
	
	public String toString()
	{
		return this.Name;
	}
	public String getName()
	{
		return Name;
	}
	public char getToken()
	{
		return Token;		
	}
}