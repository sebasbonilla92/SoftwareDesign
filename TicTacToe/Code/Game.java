
public class Game {	
	
	//players:
	private Player Player1 = null;
	private Player Player2 = null;
	private Player ActivePlayer=null;
	//Board:
	private Board Board = null;
	public final int BoardSizeConstant=3;
	/************************************************************************/
	
	public Game(Player player1, Player player2)
	{		
		this.Player1 = player1;
		this.Player2 = player2;
		this.Board=new Board(BoardSizeConstant,this);
		this.ActivePlayer=player1;
	}	
	
	public Player getPlayer1()
	{
		return this.Player1;
	}
	public Player getPlayer2()
	{
		return this.Player2;
	}
	public Player getActivePlayer()
	{
		return this.ActivePlayer;
	}
	public void setActivePlayer(Player player)
	{
		this.ActivePlayer=player;
	}
	public void switchTurns()
	{
		if(this.getActivePlayer().equals(this.getPlayer1())){
			this.ActivePlayer=this.getPlayer2();
			return;
		}
		this.ActivePlayer=this.getPlayer1();
		
	}
	public boolean play()
	{
		while(!Board.isDone())
		{
			System.out.println(Board);
			
			TicLocation location=getActivePlayer().move(Board);
			Board.setPlayer(location,getActivePlayer());
		}
		System.out.println(Board);
		if(Board.getWinner()==null)
		{
			System.out.println("Draw");	
		}
		else
		{
			System.out.println(Board.getWinner().getName()+" Won");
		}		
		
		return false;		
	}
	
	public Player playTestWinner()
	{
		while(!Board.isDone())
		{					
			TicLocation location=getActivePlayer().move(Board);
			Board.setPlayer(location,getActivePlayer());
		}		
		return Board.getWinner();		
	}
}