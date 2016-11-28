
public class UserVsComputerGame extends Game{
	
	//+computerPlayer : Player
	private Computer computerPlayer = null;	
	
	//+UserVsComputerGame (UserName: String) : Game
	public UserVsComputerGame(String UserName)
	{
		super(new UserPlayer(UserName,'X'),new Computer("Computer",'O'));
		computerPlayer=(Computer)this.getPlayer2();		
	}
	
	//+setDifficulty (difficulty : int)
	public void setDifficulty(int difficulty){
		computerPlayer.setDifficulty(difficulty);	
	}
}