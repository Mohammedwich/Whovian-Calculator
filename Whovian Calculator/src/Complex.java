//	Mohammed Ahmed 		msa190000


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
			BigDecimal theNum = new BigDecimal(getRealNumber()).setScale(2);
			string.append(theNum);	//append real part to the string, can be zero
		}
		
		// append + or - followed by imaginary part
		if(imaginaryNumber < 0)
		{
			string.append("-");
			BigDecimal theNum = new BigDecimal(Math.abs(imaginaryNumber)).setScale(2);
			string.append(theNum); //TODO: don't put in 1 or 0 coefficients
			string.append("i");
		}
		else if(imaginaryNumber >= 0)
		{
			if(realNumber != 0)
			{
				string.append("+");
			}
			BigDecimal theNum = new BigDecimal(Math.abs(imaginaryNumber)).setScale(2);
			string.append(theNum);
			string.append("i");
		}
		
		String result = string.toString();
		
		return result;
	}
	
	
	public boolean equals(String someComplex)
	{
		String thisObject = this.toString();
		int checkResult = thisObject.compareTo(someComplex);
		
		if(checkResult == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	protected double imaginaryNumber;
	
}
