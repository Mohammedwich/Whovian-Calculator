//	Mohammed Ahmed 		msa190000

//TODO: remove unused import if not needed
//TODO: round up to 2 decimals
import java.math.BigDecimal;	//Used to format the double in the toString() function to have 2 decimal places
import java.math.RoundingMode;

public class Number 
{
	public Number()
	{
		realNumber = 0;
	}
	
	public Number(double x)
	{
		this();
		realNumber = x;
	}
	
	public double getRealNumber()
	{
		return realNumber;
	}
	
	public void setRealNumber(double x)
	{
		realNumber = x;
	}
	
	
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		BigDecimal theNum = new BigDecimal(realNumber).setScale(2);
		string.append(theNum);
		
		String result = string.toString();
		return result;
	}
	
	
	public boolean equals(Object someObject)
	{
		if(someObject instanceof Complex)
		{
			return false;
		}
		
		if(!(someObject instanceof Number))
		{
			return false;
		}
		
		if(someObject instanceof Number)
		{
			if(realNumber == ((Number)someObject).getRealNumber() )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false; //Incase none of the cases match for some reason
		
	}
	
	public boolean equals(String someNum)
	{
		double parsedNumber = Double.parseDouble(someNum);
		
		if(realNumber == parsedNumber)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	protected double realNumber;
}
