import java.util.*;
public class UnitTest {
	public void runAllTest()
	{
		System.out.println(""+
"              Unit Test\n"+
"----------------------------------------");
		runUserBirthDateFormatTest();
		runDayOfTheWeekTest();
		runZodiacSignTest();
		System.out.println();
	}
	public void runUserBirthDateFormatTest()
	{
	  /************************************************************************/				
		boolean Passed=true;
		
	  /************************************************************************/				
		Map<String,UserBirthDate> TestCases = new HashMap<String, UserBirthDate>(){{
		    put("01/01/2016",new UserBirthDate(1,1,2016));
		    put("02/01/2016",new UserBirthDate(2,1,2016));
		    put("01/02/2016",new UserBirthDate(1,2,2016));
		    put("01/01/2000",new UserBirthDate(1,1,2000));
		    
		    put("1/01/2016",new UserBirthDate(1,1,2016));
		    put("2/01/2016",new UserBirthDate(2,1,2016));
		    put("1/02/2016",new UserBirthDate(1,2,2016));
		    put("1/01/2000",new UserBirthDate(1,1,2000));
		    
		    put("01/1/2016",new UserBirthDate(1,1,2016));
		    put("02/1/2016",new UserBirthDate(2,1,2016));
		    put("01/2/2016",new UserBirthDate(1,2,2016));
		    put("01/1/2000",new UserBirthDate(1,1,2000));
		    
		    put("1/1/2016",new UserBirthDate(1,1,2016));
		    put("2/1/2016",new UserBirthDate(2,1,2016));
		    put("1/2/2016",new UserBirthDate(1,2,2016));
		    put("1/1/2000",new UserBirthDate(1,1,2000));
		    
		    put("0/1/2000",null);
		    put("0/0/2000",null);
		    
		    put("01/32/2000",null);
		    put("01/01/2000h",null);
		}};
		
	  /************************************************************************/				
		for(String stringFormat:TestCases.keySet())
		{
		  /***********************************************************************/
			UserBirthDate expected=TestCases.get(stringFormat);
			UserBirthDate resulted=UserBirthDate.FromString(stringFormat);
			
		  /***********************************************************************/
		  /* If either one is null, then we want to test seperatly to avoid      */
		  /* exepctions when using equals bellow                                 */
		  /***********************************************************************/		   			
			if(expected==null || resulted==null)
			{
			  /*******************************************************************/
			  /* If both null, then we expect the format to be incorrect which is*/
			  /* is the case                                                     */
			  /*******************************************************************/			   
				if(expected==null && resulted==null)
				{
		     	  //Passed
					continue;
				}
				
			  /*******************************************************************/
			  /* If we get here then someone dis-agrees with that fact that is is*/
			  /* a correct format or not                                         */				
			  /*******************************************************************/			  	
				System.out.println("*ERROR*, runUserBirthDateFormatTest() Date Format('"+stringFormat+"')\n"+
					               "                                      Expected: "+expected+"\n"+
					               "                                      Resulted: "+resulted);
				Passed=false;
				continue;
			}
			
		  /***********************************************************************/
		  /* If both are not null, then do a equal compare, if we are equal then */
		  /* Passed                                                              */
		  /***********************************************************************/		   
			if(expected.equals(resulted)){
				continue;
			}
			
		  /***********************************************************************/
		  /* Not Equal. Not Passed                                               */
		  /***********************************************************************/		   
			System.out.println("*ERROR*, runUserBirthDateFormatTest() Date Format('"+stringFormat+"') \n"+
					               "                                  Expected: "+expected+"\n"+
					               "                                  Resulted: "+resulted);
			Passed=false;
		}
		
	  /************************************************************************/						
		System.out.println("User Birth Date Format Unit Test: "+(Passed?"Passed":"Failed"));
		
	}
	public void runDayOfTheWeekTest()
	{
	  /************************************************************************/						
		boolean Passed=true;
		
	  /************************************************************************/						
		Map<String,String> TestCases = new HashMap<String, String>(){{
		    put("01/01/2016","Friday");
		    put("07/27/1993","Tuesday");
		    put("07/27/1994","Wednesday");
		    put("8/17/1985","Saterday");
		}};
		
	  /************************************************************************/						
		for(String stringFormat:TestCases.keySet())
		{
		  /***********************************************************************/			
			UserBirthDate fromString=UserBirthDate.FromString(stringFormat);
			
		  /***********************************************************************/
		  /* Handle Possible misses by last test on FromString routine           */
		  /***********************************************************************/		   
			if(fromString==null){
				System.out.println("*ERROR*, runDayOfTheWeekTest() Date Format('"+stringFormat+"') Test Error\n"+
					               "                               Expected Format to Work. Please Refer to runUserBirthDateFormatTest()");
				Passed=false;
				continue;
			}
			
		  /***********************************************************************/			
			String expected=TestCases.get(stringFormat);
			String resulted=fromString.getDayOfTheWeek();
			
		  /***********************************************************************/			
			if(expected.equals(resulted)){
				continue;
			}
			
		  /***********************************************************************/			
			System.out.println("*ERROR*, runDayOfTheWeekTest() Date ('"+fromString+"') Test Error\n"+
					               "                           Expected: "+expected+"\n"+
					               "                           Resulted: "+resulted);
			Passed=false;
		}
		
		
		System.out.println("Day Of the Week Unit Test.......: "+(Passed?"Passed":"Failed"));
	}
	
	public void runZodiacSignTest()
	{
	  /************************************************************************/				
		boolean Passed=true;
		
	  /************************************************************************/				
		Map<String,String> TestCases = new HashMap<String, String>(){{
		    put("01/01/2016","Capricorn");
		    put("07/27/1993","Leo");
		    put("07/27/1994","Leo");
		    put("8/17/1985","Leo");
		    put("3/21/1993","Aries");
		    put("3/20/1993","Pisces");
		    put("4/19/1993","Aries");
		    put("4/20/1993","Taurus");
		}};
		
	  /************************************************************************/				
		for(String stringFormat:TestCases.keySet())
		{
		  /***********************************************************************/						
			UserBirthDate fromString=UserBirthDate.FromString(stringFormat);
			
		  /***********************************************************************/
		  /* Handle Possible misses by last test on FromString routine           */
		  /***********************************************************************/		   			
			if(fromString==null){
				System.out.println("*ERROR*, runZodiacSignTest() Date Format('"+stringFormat+"') Test Error\n"+
					               "                             Expected Format to Work. Please Refer to runUserBirthDateFormatTest()");
				Passed=false;
				continue;
			}
			
		  /***********************************************************************/				
			String expected=TestCases.get(stringFormat);
			String resulted=fromString.getZodiacSign();
			
		  /***********************************************************************/				
			if(expected.equals(resulted)){
				continue;
			}
			
		  /***********************************************************************/				
			System.out.println("*ERROR*, runZodiacSignTest() Date ('"+fromString+"') Test Error\n"+
					               "                         Expected: "+expected+"\n"+
					               "                         Resulted: "+resulted);
			Passed=false;
		}
		
		System.out.println("Zodiac Sign Unit Test...........: "+(Passed?"Passed":"Failed"));
	}
}
