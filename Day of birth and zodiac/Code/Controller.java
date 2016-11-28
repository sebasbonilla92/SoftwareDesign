import java.util.regex.*;
import java.util.*;
public class Controller
{
  //-UsersInput: String
	private String UsersInput=null;

  //-DateOfBirth: UserBirthDate
	private UserBirthDate DateOfBirth=null;

  //+UCReadInUserBD(): void
	public void UCReadInUserBD()
	{
		int count=3; // For input invalid parameters count
		Scanner input=new Scanner(System.in); //Read in input from user

	  /************************************************************************/		
		do
		{
		  /********************************************************************/
			System.out.print("Enter the Date: ");
			String inputsTR=input.nextLine();

		  /********************************************************************/			
			UserBirthDate date=UCCheckFormat(inputsTR); // Will return null if incorrect format
			
			if(date==null) //If we have null, incorrect format
			{
				System.out.println("*ERROR*, Invalud Perameters");
				count--; // We will count down to exit later
				continue;	
			}
			
		  /********************************************************************/			
			DateOfBirth=date;
			return;
			
		}while(count>=0);

	  /************************************************************************/					
		UCExitProgram();

	}

  //+UCPrintResults(): void
  	public void UCPrintResults()
  	{
	  /************************************************************************/		
	  /* If DateOfBirth is null, then the use of the controller is incorrect  */
	  /************************************************************************/				
		if(DateOfBirth==null){
			System.out.println("Unkown Date");
			return;
		}

	  /************************************************************************/		
		System.out.println("You were born on "+DateOfBirth.getDayOfTheWeek());
		System.out.println("Your Zodiac Sign is "+DateOfBirth.getZodiacSign());
  	}
  	  
  //-UCExitProgram(): void
  	private void UCExitProgram()
  	{
  		System.exit(0);
  	}
  	  	  
  //-UCCheckFormat(): void
  	private UserBirthDate UCCheckFormat(String input)
  	{
  		return UserBirthDate.FromString(input);
  	}

}
