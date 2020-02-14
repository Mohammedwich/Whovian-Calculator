//	Mohammed Ahmed 		msa190000

//TODO: remove unused import if not needed
//TODO: round up to 2 decimals
import java.math.BigDecimal;	//Used to format the double in the toString() function to have 2 decimal places
import java.math.RoundingMode;

public class Complex extends Number
{
	public Complex()
	{
		setRealNumber(0);
		imaginaryNumber = 0;
	}
	
	public Complex(double theReal, double theImaginary)
	{
		this();
		setRealNumber(theReal);
		setImaginaryNumber(theImaginary); 
	}
	
	public double getImaginaryNumber()
	{
		return imaginaryNumber;
	}
	
	public void setImaginaryNumber(double x)
	{
		imaginaryNumber = x;
	}
	
	//TODO: remove the zero and 1 coefficient from i
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		
		if(realNumber != 0)
		{
			BigDecimal theNum = new BigDecimal(getRealNumber()).setScale(2, RoundingMode.HALF_UP);
			string.append(theNum);	//append real part to the string, can be zero
		}
		
		// append + or - followed by imaginary part
		if(imaginaryNumber < 0)
		{
			string.append("-");
			
			if(imaginaryNumber != -1)	// Don't append coefficient when it is -1
			{
				BigDecimal theNum = new BigDecimal(Math.abs(imaginaryNumber)).setScale(2, RoundingMode.HALF_UP);
				string.append(theNum);
			}
			
			string.append("i");
		}
		else if(imaginaryNumber >= 0)
		{
			if(realNumber != 0)
			{
				string.append("+");
			}
			
			if(imaginaryNumber != 1)	// Don't append coefficient when it is 1
			{
				BigDecimal theNum = new BigDecimal(Math.abs(imaginaryNumber)).setScale(2, RoundingMode.HALF_UP);
				string.append(theNum);
			}
			
			string.append("i");
		}
		
		String result = string.toString();
		
		return result;
	}
	
	
	public boolean equals(Object someObject)
	{
		if(!(someObject instanceof Complex))
		{
			return false;
		}
		
		if(someObject instanceof Complex)
		{
			if(realNumber == ((Complex)someObject).getRealNumber() && imaginaryNumber ==  ((Complex)someObject).getImaginaryNumber())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;	//return false if no cases met
	}
	
	
	protected double imaginaryNumber;
	
}
