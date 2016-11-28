import java.util.*;
import java.util.regex.*;
public class UserPlayer extends Player{

	public UserPlayer(String Name, char Token) {
		super(Name, Token);
		// TODO Auto-generated constructor stub
	}
	
	public TicLocation move(Board board){
		return moveToAskPosition(board);
	}
	
	public TicLocation moveToAskPosition(Board board){
		Scanner scan = new Scanner(System.in); //read input
		while(true)
		{
			System.out.print("Please enter your move(Ex 1 2): ");
			String input=scan.nextLine();
			
		  /***********************************************************************/
		  /* Check format of input                                               */			
		  /***********************************************************************/		  	
			Pattern p=Pattern.compile("^(\\d+) (\\d+)$");
			Matcher m=p.matcher(input);
			if(m.find()==false)
			{
				System.out.println("Invalid Input. Please follow the format (row column)");
				continue;
			}
			
		  /***********************************************************************/			
			int row=Integer.parseInt(m.group(1));
			int column=Integer.parseInt(m.group(2));
			TicLocation location=TicLocation.create(row,column);
				
				
		  /***********************************************************************/				
			Board.BoardStatus locationStatus=board.CheckLocation(location);
				
		  /***********************************************************************/
			if(locationStatus.equals(Board.BoardStatus.EMPTY))
			{
				return location;
			}
			
		  /***********************************************************************/			
			if(locationStatus.equals(Board.BoardStatus.EXCEED_BOUNDS)){
				System.out.println("Out of bounds of Board. Please enter values from 0-"+(board.getBoardSize()-1));
				continue;
			}
			
		  /***********************************************************************/			
			if(locationStatus.equals(Board.BoardStatus.LOCATION_OCCUPIED)){
				System.out.println("That Location is occupied. Please try another location");
				continue;
			}
			
			System.out.println("Unkown Board Status "+locationStatus);
			System.exit(10);
		}
	}
	
	
}