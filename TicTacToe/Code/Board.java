import java.util.*;
public class Board {

    public enum BoardStatus
    {
        SUCCESS,
        EMPTY,
        EXCEED_BOUNDS,
        LOCATION_OCCUPIED
    }
    
    private Player [][] grid = null;
    private int boardSize=0;
    private Game activeGame;
  /***************************************************************************/
    public Board(int BoardSize, Game activeGame)
    {
        this.setBoardSize(BoardSize);
        this.activeGame=activeGame;
    }
    
    public Game getGame()
    {
    	return this.activeGame;
    }
    
    public int getBoardSize()
    {
        return this.boardSize;
    }
    public void setBoardSize(int boardSize)
    {
        this.grid=new Player[boardSize][boardSize];
        this.boardSize=boardSize;
    }
    public Board makeCopyOfBoard()
    {
    	Board newBoard =new Board(this.getBoardSize(),new Game(getGame().getPlayer1(),getGame().getPlayer2()));
    	
    	
    	for(int row=0;row<this.getBoardSize();row++)
        {
          /********************************************************************/
            for(int column=0;column<this.getBoardSize();column++)
            {
              /****************************************************************/
            	TicLocation locationInOld=TicLocation.create(row,column);

              /****************************************************************/            	
            	if(this.CheckLocation(locationInOld).equals(BoardStatus.EMPTY)){
            		continue;
            	}
            	newBoard.setPlayer(locationInOld,this.getPlayer(locationInOld));
            }
        }
        newBoard.getGame().setActivePlayer(getGame().getActivePlayer());
        return newBoard;
    }
    public Board getVirtualBoardWithNewMove(TicLocation location, Player player)
    {
    	Board newBoard = makeCopyOfBoard();
    	
        newBoard.setPlayer(location,player);
        return newBoard;
    }
    public ArrayList<TicLocation> GetEmptySpots()
    {
    	ArrayList<TicLocation> locations=new ArrayList<TicLocation>();
    	
      /************************************************************************/    	
    	for(int row=0;row<this.getBoardSize();row++)
        {
          /********************************************************************/
            for(int column=0;column<this.getBoardSize();column++)
            {
              /****************************************************************/
            	TicLocation location=TicLocation.create(row,column);

              /****************************************************************/            	
            	if(this.CheckLocation(location).equals(BoardStatus.EMPTY)){
            		locations.add(location);
            	}
            }
        }
        return locations;
    }
    public BoardStatus CheckLocation(TicLocation location)
    {   
      /************************************************************************/
      /* Check Boundaries                                                     */
      /************************************************************************/      
        if(location.getRow()<0 || location.getRow()>=this.getBoardSize() ||
           location.getColumn() < 0 || location.getColumn() >= this.getBoardSize())
        {
            return BoardStatus.EXCEED_BOUNDS;
        }

      /************************************************************************/            
        if(this.grid[location.getRow()][location.getColumn()] == null){
            return  BoardStatus.EMPTY;
        }       
        return  BoardStatus.LOCATION_OCCUPIED;        
    }
    
    public BoardStatus setPlayer(TicLocation location, Player player)
    {
      /************************************************************************/                
      /* Test isEmpty of Location                                             */
      /************************************************************************/      
        BoardStatus IsEmptyStatus=CheckLocation(location);
        if(!IsEmptyStatus.equals(BoardStatus.EMPTY)){
            return IsEmptyStatus;
        }
        
      /************************************************************************/                
        this.grid[location.getRow()][location.getColumn()]=player;
        this.getGame().switchTurns();
        return BoardStatus.SUCCESS;
    }
    public Player getPlayer(TicLocation location)
    {
    	if(location==null){
    		System.out.println("*ERROR*, Location is null");
    		System.exit(10);
    	}
    	
      /************************************************************************/                
      /* Test isEmpty of Location                                             */
      /************************************************************************/      
        BoardStatus IsEmptyStatus=CheckLocation(location);
        if(IsEmptyStatus.equals(BoardStatus.LOCATION_OCCUPIED)){
            return this.grid[location.getRow()][location.getColumn()];
        }

      /************************************************************************/            
        return null;
    }
    public boolean isDone()
    {
    	if(getWinner()!=null){
    		return true;
    	}
    	for(int row=0;row<this.getBoardSize();row++)
        { 
            for(int column=0;column<this.getBoardSize();column++)
            {
            	TicLocation location=TicLocation.create(row,column);
                if(getPlayer(location)==null){
                	return false;
                }
            }
        }
        return true;
    }
    
    public Player getWinner()
    {
      /************************************************************************/       	
        Player winner=getColumnWinner();
        if(winner!=null){
        	return winner;
        }    
        	
      /************************************************************************/           	
        winner=getRowWinner();
        if(winner!=null){
        	return winner;
        }
            
      /************************************************************************/               
        winner=getCrossWinner();
        if(winner!=null){
        	return winner;
        }   
        	 
      /************************************************************************/           	 
        return null;
        
    }
    public Player getColumnWinner()
    {
        Player currentPossibleWinner=null;
      /************************************************************************/        
        for(int row=0;row<this.getBoardSize();row++)
        {
          /********************************************************************/
          /* If first row of the column is empty, then we have no possible win*/
          /********************************************************************/          
            TicLocation location=TicLocation.create(row,0);
            if(getPlayer(location)==null)
            {
                continue;
            }
            
          /********************************************************************/ 
            currentPossibleWinner=getPlayer(location); //Who ever is on the first row must be on all columns    
            for(int column=0;column<this.getBoardSize();column++)
            {
                location=TicLocation.create(row,column);
                Player testPlayer=getPlayer(location);
                
              /****************************************************************/ 
                if(testPlayer==null)
                {
                    currentPossibleWinner=null;
                    break;
                }
                
              /****************************************************************/                 
                if(!testPlayer.equals(currentPossibleWinner))
                {
                	currentPossibleWinner=null;
                    break;
                }
                
            }
            if(currentPossibleWinner!=null){
            	return currentPossibleWinner;
            }
        }
        return null;        
    }
    public Player getRowWinner()
    {
        Player currentPossibleWinner=null;
      /************************************************************************/        
        for(int column=0;column<this.getBoardSize();column++)
        {
          /********************************************************************/
          /* If first column of the row is empty, then we have no possible win*/
          /********************************************************************/          
            TicLocation location=TicLocation.create(0,column);
            if(getPlayer(location)==null)
            {
                continue;
            }
            
          /********************************************************************/ 
            currentPossibleWinner=getPlayer(location); //Who ever is on the first column must be on all rows
                
            for(int row=0;row<this.getBoardSize();row++)
            {
            	
                location=TicLocation.create(row,column);
                Player testPlayer=getPlayer(location);
                
              /****************************************************************/ 
                if(testPlayer==null)
                {
                    currentPossibleWinner=null;
                    break;
                }
                
              /****************************************************************/                 
                if(!testPlayer.equals(currentPossibleWinner))
                {
                	currentPossibleWinner=null;
                    break;
                }
            }
            
          /********************************************************************/
            if(currentPossibleWinner!=null){
            	return currentPossibleWinner;
            }
        }
        return null;        
    }
    public Player getCrossWinner()
    {
      /************************************************************************/
      /* Test Cross from top left to bottom right                             */
      /************************************************************************/    	
        Player currentPossibleWinner=getPlayer(TicLocation.create(0,0));
        if(currentPossibleWinner!=null)
        {
        	for(int i=1;i<getBoardSize();i++)
        	{
        		TicLocation location=TicLocation.create(i,i);  //locations 1-1, 2-2, 3-3, etc.
                Player testPlayer=getPlayer(location);	
                
              /****************************************************************/ 
                if(testPlayer==null)  //nobody at location = no possible winner
                {
                    currentPossibleWinner=null;
                    break;
                }
                
              /****************************************************************/                 
                if(!testPlayer.equals(currentPossibleWinner)) //if its not the same player = no winner
                {
                	currentPossibleWinner=null;
                    break;
                }
        	}
        	
          /********************************************************************/        	
        	if(currentPossibleWinner!=null){ 
            	return currentPossibleWinner;
            }
        }
        
      /************************************************************************/
      /* Test Cross from top right to bottom left                             */
      /************************************************************************/      
        currentPossibleWinner=getPlayer(TicLocation.create(0,getBoardSize()-1));
        if(currentPossibleWinner!=null)
        {
        	for(int i=1;i<getBoardSize();i++)
        	{
        		TicLocation location=TicLocation.create(i,(getBoardSize()-1)-i);
                Player testPlayer=getPlayer(location);
                
              /****************************************************************/ 
                if(testPlayer==null)
                {
                    currentPossibleWinner=null;
                    break;
                }
                
              /****************************************************************/                 
                if(!testPlayer.equals(currentPossibleWinner))
                {
                	currentPossibleWinner=null;
                    break;
                }
        	}
        	if(currentPossibleWinner!=null){
            	return currentPossibleWinner;
            }
        }
      
        return null;        
    }
    
    public String toString()
    {
        String gridString="  ";
        for(int row=0;row<getBoardSize();row++)
        {
        	gridString+="  "+row+" ";
        }
        gridString+="\n  +";
        for(int row=0;row<getBoardSize();row++)
        {
        	if(row==getBoardSize()-1)
        	{
        		gridString+="---+";
        	}
        	else
        	{
        		gridString+="----";
        	}
        	
        }
        gridString+="\n";
        
        for(int row=0;row<getBoardSize();row++)
        {
        	gridString+=row+" |";
        	for(int column=0;column<getBoardSize();column++)
        	{
        		TicLocation location=TicLocation.create(row,column);
        		Player player=this.getPlayer(location);
        		if(player==null){
        			gridString+="   |";
        		}
        		else{
        			gridString+=" "+player.getToken()+" |";
        		}
        	}
        	gridString+="\n";
        	
        }
        gridString+="  +";
        for(int row=0;row<getBoardSize();row++)
        {
        	if(row==getBoardSize()-1)
        	{
        		gridString+="---+";
        	}
        	else
        	{
        		gridString+="----";
        	}
        	
        }
        gridString+="\n";
        return gridString;
        
    }
    
    
}