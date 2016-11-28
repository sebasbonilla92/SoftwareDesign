import java.util.*;
import java.lang.*;
public class Computer extends Player{

	//difficulty : int
	private int difficulty = 0;

	public Computer(String Name, char Token){
		super(Name, Token);		
		Name = "Computer";
	}
	public TicLocation move(Board board)
	{
		switch(this.getDifficulty())
		{
			case 1:
				return moveToRandomSpot(board);
			case 2:
				return moveToWinningSpot(board);		
			case 3:
				return moveToOptimalSpot(board,0).getKey();
			default:
				System.out.println("*ERROR*, Unknown Difficulty "+this.getDifficulty());
				System.exit(10);
		}
		return null;
		
	}
	public TicLocation moveToWinningSpot(Board board)
	{
		ArrayList<TicLocation> emptyLocations=board.GetEmptySpots();
		
	  /************************************************************************/
	  /* Go through all available locations, and check if any of them cause   */
	  /* a win                                                                */
	  /************************************************************************/
		for(TicLocation location:emptyLocations)
		{
		  //Make a virtual board with possible move to check for a win
			Board possibleGame=board.getVirtualBoardWithNewMove(location,board.getGame().getActivePlayer());
			
		  //Check if no win
			Player player=possibleGame.getWinner();
			if(player==null){
				continue;
			}
			
		  //Check if who won was me
			if(player.equals(this)){
				return location;
			}
		}
		
	  //If we did not find a winning move, then do a random move
		return moveToRandomSpot(board);
	}
	public int Score(Board board , int depth)
	{
	  /*************************************************************************/
	  /* This routine is used for the AI part of the Computer. Will return the */
	  /* Score of the game depending on who won... This is used to make predict*/
	  /* and see what move will give the best outcome                          */
	  /*************************************************************************/	  
		Player player=board.getWinner();
		
	  /************************************************************************/		
		if(player==null){
			return 0;
		}
		
	  /************************************************************************/		
		if(player.equals(this)){
			return 10 - depth;
		}
		
	  /************************************************************************/		
		return depth - 10;
	}

	public Map.Entry<TicLocation,Integer> moveToOptimalSpot(Board board, int depth)
	{
	  /************************************************************************/
	  /* If the board has a win or done then return the score of the board    */
	  /* the reason we want the depth so that the score is more significant   */
	  /* if the win happened earlier in the game or later                     */
	  /************************************************************************/	  
		if(board.isDone())
		{
			int score=Score(board,depth);
			return new AbstractMap.SimpleEntry(null,score);
		}
		
	  /************************************************************************/		
		depth++;
		if(board.getBoardSize()>=5)
		{
			if(depth>=5)
			{
				return new AbstractMap.SimpleEntry(moveToRandomSpot(board),0);
			}
		}
		
	  /************************************************************************/		
		ArrayList<TicLocation> emptyLocations=board.GetEmptySpots();
		ArrayList<Integer> Scores=new ArrayList<Integer>();
		ArrayList<TicLocation> Moves=new ArrayList<TicLocation>();
		
	  /************************************************************************/
	  /* Go through all possible locations and make a hypothetical board with */
	  /* a Move to that these locations and sent that board back into the     */
	  /* function giving us a end tree of optimal points for each move        */	
	  /************************************************************************/	  	
		for(TicLocation location : emptyLocations)
		{
			Board possibleGame=board.getVirtualBoardWithNewMove(location,board.getGame().getActivePlayer());
			
		  /********************************************************************/
			int socre=moveToOptimalSpot(possibleGame,depth).getValue();
			Scores.add(socre);
			Moves.add(location);			
		}
		
	  /************************************************************************/
	  /* If the current player is the one we want to win then we must find the*/
	  /* move that gave us the greatest points                                 */		
	  /************************************************************************/	  	
		if(board.getGame().getActivePlayer().equals(this))
		{
			int maxIndex=Integer.MIN_VALUE;
			int maxValue=Integer.MIN_VALUE;
			
		  /********************************************************************/
			for(int i=0;i<Scores.size();i++)
			{
				if(Scores.get(i)>maxValue)
				{
					maxValue=Scores.get(i);
					maxIndex=i;
				}
			}
			
		  /********************************************************************/			
			TicLocation choice=Moves.get(maxIndex);
			int Score=Scores.get(maxIndex);
			return new AbstractMap.SimpleEntry(choice,Score);
		}
		
	  /************************************************************************/
	  /* If the current player is the one we want to loose then we must find  */
	  /* the move that will give us the least points since we need the worse  */
	  /* Scenario meaning the opponent is a perfect player                      */		
	  /************************************************************************/		
		else
		{
			int minIndex=Integer.MAX_VALUE;
			int minValue=Integer.MAX_VALUE;
			
		  /********************************************************************/
			for(int i=0;i<Scores.size();i++)
			{
				if(Scores.get(i)<minValue){
					minValue=Scores.get(i);
					minIndex=i;
				}
			}
			
		  /********************************************************************/			
			TicLocation choice=Moves.get(minIndex);
			int Score=Scores.get(minIndex);
			return new AbstractMap.SimpleEntry(choice,Score);
		}
	}
	public TicLocation moveToRandomSpot(Board board)
	{
		ArrayList<TicLocation> emptyLocations=board.GetEmptySpots();
		
		TicLocation location=emptyLocations.get((int)(Math.random()*emptyLocations.size()));
		
		return location;
	}
	
	public void setDifficulty(int difficulty){		
		this.difficulty=difficulty;
	}
	private int getDifficulty(){		
		return difficulty;
	}
	
}