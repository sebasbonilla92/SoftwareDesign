import java.util.*;
public class Controller {

	
	private UserVsComputerGame game = null;
	
	private void UCSetDifficulty(){
		
		//3 levels of difficulty: Easy, Medium, Hard
		int difficulty = 0;
		Scanner input = new Scanner(System.in); //read input		
		
		/************************************************************************/
		while(difficulty < 1 || difficulty > 3){ //loop until correct input is given
			System.out.println("Select Difficulty level: \n1 = Easy\n2 = Medium\n3 = hard");
			difficulty = input.nextInt();
			if (difficulty < 1 || difficulty > 3) System.out.println("Invalid Selection\n");
		}
		
		String difficultyStr = null;
		if (difficulty == 1) difficultyStr = "Easy";
		else if (difficulty == 2) difficultyStr = "Medium";
		else if (difficulty == 3) difficultyStr = "Hard";
		System.out.println("you selected: " + difficultyStr + "\n");
		
		game.setDifficulty(difficulty);
	}
	
	public void UCStartGame(){
		//TODO		
		System.out.println("------    START    ------\n");		
		game.play();
	}
	
	public void UCSetupGame(){
		//TODO
		String username = "";
		Scanner input = new Scanner(System.in); //get name of UserPlayer
		System.out.print("Please enter your name: ");
		username = input.next();		
		
		UserPlayer player1 = new UserPlayer(username, 'X'); //create UserPlayer		
		game = new UserVsComputerGame(player1.getName());  
		UCSetDifficulty();		 							//set Difficulty
	}
}
