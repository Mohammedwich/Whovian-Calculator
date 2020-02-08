//	Mohammed Ahmed 		msa190000



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
	
	
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		
		if(realNumber != 0)
		{
			string.append(getRealNumber());	//append real part to the string, can be zero
		}
		
		// append + or - followed by imaginary part
		if(imaginaryNumber < 0)
		{
			string.append("-");
			string.append(Math.abs(imaginaryNumber));
			string.append("i");
		}
		else if(imaginaryNumber >= 0)
		{
			if(realNumber != 0)
			{
				string.append("+");
			}
			string.append(Math.abs(imaginaryNumber));
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
