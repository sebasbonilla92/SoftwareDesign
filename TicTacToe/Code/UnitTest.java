import java.util.*;

public class UnitTest {

	public void runAllTest()
	{
		System.out.println(""+
				"              Unit Test\n"+
				"----------------------------------------");		
		runHardDifficultyTest();
		runMoveOutOfBoundsTest();		
		runRowWinnerTest();
		runColumnWinnerTest();
		runCrossWinnerTest();
		System.out.println("----------------------------------------");
	}
	
	public void runHardDifficultyTest(){
		
		boolean passed = true;		
		final Computer p1 = new Computer("Computer1", '1');
		p1.setDifficulty(1);
		final Computer p2 = new Computer("Computer2", '2');
		p2.setDifficulty(3);		
		Game G1 = new Game(p1, p2);
		Player winningPlayer=G1.playTestWinner();
		if(winningPlayer!=null){
			if(winningPlayer.equals(p1)){
				passed=false;
			}
		}
		
		
		System.out.println("Perfect Player test ..............: "+(passed?"Passed":"Failed"));
	}	
	
	public void runMoveOutOfBoundsTest(){  //still working on this one.
		
		boolean passed = true;
		UserPlayer p1 = new UserPlayer("p1", 'X');
		UserVsComputerGame G1 = new UserVsComputerGame(p1.getName());
		G1.setActivePlayer(G1.getPlayer1());
		Board board = new Board(3,G1);		
		G1.setDifficulty(1);		
		TicLocation t = new TicLocation(0, 3);
		TicLocation.create(0, 3);		
		if (!board.CheckLocation(t).equals(Board.BoardStatus.EXCEED_BOUNDS)){   
			passed = false;
		}
		System.out.println("Run Move Out Of Bounds Test.......: "+(passed?"Passed":"Failed"));
	}
	
	public void runRowWinnerTest()
	{
		boolean passed = true;
		
		UserPlayer p1 = new UserPlayer("winner", 'X');
		UserPlayer p2 = new UserPlayer("loser", 'O');
		
		Game G1 = new Game(p1, p2);		
		Board board = new Board(3, G1);
		G1.setActivePlayer(p1);
		for(int row= 0;row < board.getBoardSize(); row++)
		{
			TicLocation location=TicLocation.create(0,row);			
			board.setPlayer(location, p1);
			board.getPlayer(location);					
			
		}
		
		for(int row= 0;row < board.getBoardSize(); row++)
		{
			TicLocation location=TicLocation.create(1,row);
			if (row == 2) continue;
			board.setPlayer(location, p2);
			
		}
		
		for(int row= 0;row < board.getBoardSize(); row++)
		{
			TicLocation location=TicLocation.create(2,row);
			if (row == 1) continue;
			board.setPlayer(location, p2);
			
		}
		
		TicLocation location=TicLocation.create(1,2); //occupy 1 2  
		board.setPlayer(location, p1);
		location=TicLocation.create(2,1);  //occupy 2 1
		board.setPlayer(location, p1);	
		
		/*    0   1   2 
  		  +-----------+
		0 | X | X | X |
		1 | O | O | X |
		2 | O | X | O |
  		  +-----------+  */
		Player winningPlayer=board.getColumnWinner();
		if(winningPlayer==null){
			passed=false;
		}
		else if(!winningPlayer.equals(p1))
		{
			passed=false;
		}		
		System.out.println("Get Row Winner Test...............: "+(passed?"Passed":"Failed"));		
	}
	
	public void runColumnWinnerTest()
	{
		boolean passed = true;
		
		UserPlayer p1 = new UserPlayer("winner", 'X');
		UserPlayer p2 = new UserPlayer("loser", 'O');
		
		Game G1 = new Game(p1, p2);		
		Board board = new Board(3, G1);
		G1.setActivePlayer(p1);
		for(int column= 0;column < board.getBoardSize(); column++)
		{
			TicLocation location=TicLocation.create(column, 0);			
			board.setPlayer(location, p1);
			board.getPlayer(location);					
			
		}
		
		for(int column = 0 ;column < board.getBoardSize(); column++)
		{
			TicLocation location=TicLocation.create(column,1);
			if (column == 2) continue;
			board.setPlayer(location, p2);			
		}
		
		for(int column= 0;column < board.getBoardSize(); column++)
		{
			TicLocation location=TicLocation.create(column, 2);
			if (column == 1) continue;
			board.setPlayer(location, p2);			
		}
		
		TicLocation location=TicLocation.create(2,1); //occupy 1 2  
		board.setPlayer(location, p1);
		location=TicLocation.create(1,2);  //occupy 2 1
		board.setPlayer(location, p1);	
		
		/*    0   1   2 
  		  +-----------+
		0 | X | O | O |
		1 | X | O | X |
		2 | X | X | O |
  		  +-----------+  */
		
		Player winningPlayer=board.getRowWinner();		
		if(winningPlayer==null){
			passed=false;
		}
		else if(!winningPlayer.equals(p1))
		{
			passed=false;
		}
		
		System.out.println("Get Column Winner Test............: "+(passed?"Passed":"Failed"));
		
	}
	
	public void runCrossWinnerTest()
	{
		boolean passed = true;
		
		UserPlayer p1 = new UserPlayer("winner", 'X');
		UserPlayer p2 = new UserPlayer("loser", 'O');
		
		Game G1 = new Game(p1, p2);		
		Board board = new Board(3, G1);
		G1.setActivePlayer(p1);
		for(int tic= 0;tic < board.getBoardSize(); tic++)
		{
			TicLocation location=TicLocation.create(tic, tic);			
			board.setPlayer(location, p1);
			board.getPlayer(location);					
			
		}
		
	
		TicLocation location=TicLocation.create(0,1); //occupy 1 2  		
		board.setPlayer(location, p2);
		location=TicLocation.create(0,2);  //occupy 0 2
		board.setPlayer(location, p2);	
		location=TicLocation.create(2,0);  //occupy 2 0
		board.setPlayer(location, p2);	
		location=TicLocation.create(2,1);  //occupy 2 1
		board.setPlayer(location, p2);
		location=TicLocation.create(1,0);  //occupy 1 0
		board.setPlayer(location, p1);	
		location=TicLocation.create(1,2);  //occupy 1 0
		board.setPlayer(location, p1);		
		
		/*    0   1   2 
  		  +-----------+
		0 | X | O | O |
		1 | X | X | X |
		2 | 0 | 0 | X |
  		  +-----------+  */		
		
		Player winningPlayer=board.getCrossWinner();		
		if(winningPlayer==null){
			passed=false;
		}
		else if(!winningPlayer.equals(p1))
		{
			passed=false;
		}
		
		System.out.println("Get Cross Winner Test.............: "+(passed?"Passed":"Failed"));
		
	}
	
}
