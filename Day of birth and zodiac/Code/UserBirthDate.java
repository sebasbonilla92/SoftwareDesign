import java.util.regex.*;
import java.util.*;
public class UserBirthDate
{
  //-Month: int
	private int Month;

  //-Day: int
  	private int Day;

  //-Year: int
  	private int Year;

  //+UserBirthDate(Month:int,Day:int,Year:int): UserBirthDate
  	public UserBirthDate(int Month, int Day, int Year)
  	{
  		this.Month=Month;
  		this.Day=Day;
  		this.Year=Year;
  	}
	
	public static UserBirthDate FromString(String strFormated)
	{
	  //Used Later to test the values for correctness
		int[] MaxDaysInMonth={31,29,31,30,31,30,31,31,30,31,30,31};
		
	  /************************************************************************/
	  /* Used Regex to test if the input is in the correct format             */
	  /* Correct Format: mm/dd/yyyy                                           */
	  /************************************************************************/	   	
		Pattern patern=Pattern.compile("^(0??[1-9]|1[0-2])[\\/](0??[1-9]|[1-3][0-9])[\\/](19[0-9][0-9]|20[0-1][0-9])$");
  		Matcher match=patern.matcher(strFormated);
  		if(!match.find()){
  			return null;
  		}
  		
	  /************************************************************************/	
  		int Month=Integer.parseInt(match.group(1));
  		int Day=Integer.parseInt(match.group(2));
  		int Year=Integer.parseInt(match.group(3));
  		
	  /************************************************************************/  		
  		if(Month<1){
  			return null;
  		}
  		
	  /************************************************************************/  		
		if(Month>12){
			return null;
		}
		
	  /************************************************************************/		
		if(Day>MaxDaysInMonth[Month-1]){
			return null;
		}
		
	  /************************************************************************/		
		if(Day<1){
			return null;
		}
		
	  /************************************************************************/		
  		return new UserBirthDate(Month,Day,Year);
	}
	
  //+getDayOfTheWeek(): String
  	public String getDayOfTheWeek()
  	{
  	  /**********************************************************************/  		
  		String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saterday","Sunday"};
  		  		
  	  /**********************************************************************/
  	  /* Use Calendar Class already built in Java to calculate day of the   */
  	  /* week.                                                              */
  	  /**********************************************************************/  	   
  		Calendar date=Calendar.getInstance();
  		date.set(this.Year,this.Month-1,this.Day-1,0,0,0);

  	  /**********************************************************************/
  		return days[date.get(Calendar.DAY_OF_WEEK)-1];
  	}
  	
  //+getZodiacSign(): String
  	public String getZodiacSign()
  	{
  	  /**********************************************************************/
  	  /* Dates for Zodiac Found on website                                  */
  	  /* http://www.psychicguild.com/horoscopes_explained.php		        */
  	  /**********************************************************************/  	   
  		Object[][] ZodiacSignRanges=
  		{
  			{1,20,2,18,"Aquarius"},
  			{2,19,3,20,"Pisces"},
  			{3,21,4,19,"Aries"},
  			{4,20,5,20,"Taurus"},
  			{5,21,6,20,"Gemini"},
  			{6,21,7,22,"Cancer"},
  			{7,23,8,22,"Leo"},
  			{8,23,9,22,"Virgo"},
  			{9,23,10,22,"Libra"},
  			{10,23,11,21,"Scorpio"},
  			{11,22,12,21,"Saqittarius"},
  			{12,22,1,19,"Capricorn"}
  		};
  		
  	  /**********************************************************************/
  	  /* Go through all possible ranges to find the range that this date    */
  	  /* falls in. And that will be our zodiac sign                         */
  	  /**********************************************************************/  	  
  		for(int i=0;i<ZodiacSignRanges.length;i++)
  		{
  	  	  /******************************************************************/  			
  			int ZodiacStartDate_Month = (int)   ZodiacSignRanges[i][0];
  			int ZodiacStartDate_Day   = (int)   ZodiacSignRanges[i][1];
  			int ZodiacEndDate_Month   = (int)   ZodiacSignRanges[i][2];
  			int ZodiacEndDate_Day     = (int)   ZodiacSignRanges[i][3];
  			String ZodiacSign         = (String)ZodiacSignRanges[i][4];

  	  	  /******************************************************************/  			  			
  			if(this.Month==ZodiacStartDate_Month && this.Day>=ZodiacStartDate_Day)
  				return ZodiacSign;
  				
  	  	  /******************************************************************/  			  				
  			if(this.Month==ZodiacEndDate_Month && this.Day<=ZodiacEndDate_Day)
  				return ZodiacSign;
  			
  		}
  		
  	  /**********************************************************************/  			  		
  		return "*ERROR*, Unkown";
  	}
	public boolean equals(Object obj)
	{
      /*********************************************************************/				
		if(obj instanceof UserBirthDate)
		{
			UserBirthDate that=(UserBirthDate)obj;
			return this.Month==that.Month && this.Day==that.Day && this.Year==that.Year;
		}
		
      /*********************************************************************/		
		return false;
	}
	public String toString()
	{
		return this.Month+"/"+this.Day+"/"+this.Year;
	}
  
  	
  	
}
