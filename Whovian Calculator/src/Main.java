//	Mohammed Ahmed 		msa190000

public class Main 
{
	public static void main(String args[])
	{
	
		Number one = new Number(1);
		Number two = new Number(3);
		Complex three = new Complex();
		Complex four = new Complex(2,1);
		Complex five = new Complex(5,6);
		
		Number test1 = multiply(four, five);
		String test1String = test1.toString();
		
		System.out.println("Test1 is: " + test1String);
		
		//TODO: Fix the can't cast returned object from subtract issue
		
	
	}	// main end
	
	//TODO: Implement this
	static Number add(Object first, Object second)
	{
		return new Number();
	}	// add() end
	
	
	// Checks parameter types and subtracts their members accordingly then returns a new object with the new values
	static Number subtract(Object first, Object second)
	{		
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			double real = ((Complex)first).getRealNumber() - ((Complex)second).getRealNumber();
			double imaginary = ((Complex)first).getImaginaryNumber() - ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			double real = ((Number)first).getRealNumber() - ((Complex)second).getRealNumber();
			double imaginary = 0 - ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			double real = ((Complex)first).getRealNumber() - ((Number)second).getRealNumber();
			double imaginary = ((Complex)first).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			double real = ((Number)first).getRealNumber() - ((Number)second).getRealNumber();
			
			Number result = new Number(real);
			return result;
		}
		
		//This part is here to shut the compiler up about the function having no return value(in case none of the ifs work)
		else
		{
			System.out.println("Error in subtract function. The two parameters do not meet any of the case types.");
			System.out.println("A Number object with value zero has been returned");
			System.out.flush();
			return new Number();
		}
	} // subtract() end
	
	
	// Checks parameter types and multiplies the numbers and returns a new Number object with the result
	static Number multiply(Object first, Object second)
	{
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			double real = ((Complex)first).getRealNumber() * ((Complex)second).getRealNumber()
					- ((Complex)first).getImaginaryNumber() * ((Complex)second).getImaginaryNumber();
			
			double imaginary = ((Complex)first).getRealNumber() * ((Complex)second).getImaginaryNumber()
					+ ((Complex)first).getImaginaryNumber() * ((Complex)second).getRealNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			double real = ((Number)first).getRealNumber() * ((Complex)second).getRealNumber();
			double imaginary = ((Number)first).getRealNumber() * ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			double real = ((Number)first).getRealNumber() * ((Complex)second).getRealNumber();
			double imaginary = ((Number)first).getRealNumber() * ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			double real = ((Number)first).getRealNumber() * ((Number)second).getRealNumber();
			
			Number result = new Number(real);
			return result;
		}
		
		
		//This part is here to shut the compiler up about the function having no return value(in case none of the ifs work)
		else
		{
			System.out.println("Error in multiply function. The two parameters do not meet any of the case types.");
			System.out.println("A Number object with value zero has been returned");
			System.out.flush();
			return new Number();
		}
	} // multiply() end


	//TODO: Implement this
	static Number divide(Object first, Object second)
	{
		return new Number();
	} // divide() end
	
	
	//TODO: Implement this
	static Complex conjugate(Complex theNum)
	{
		return new Complex();
	} // conjugate() end

	
	// Checks parameter types then compares their magnitudes to see if the first parameter is less than the second
	// powering and square-rooting a single value to avoid faulty comparisons due to Double data type calculation
	static Boolean lessThan(Object first, Object second)
	{
		
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < (sqrt(2.real^2 + 2.imaginary^2)) ) 
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					< (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			// (1.real < (sqrt(2.real^2 + 2.imaginary^2)) )
			Boolean result = (
					(Math.sqrt(Math.pow( ((Number)first).getRealNumber(), 2)))
					< (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < 2.real)
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					< (Math.sqrt(Math.pow( ((Number)second).getRealNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			Boolean result = ( ((Number)first).getRealNumber() < ((Number)second).getRealNumber() )? true: false;
			
			return result;
		}
		
		// default return false with message print if something is wrong
		else
		{
			System.out.println("Error in lessThan function. The two parameters do not meet any of the case types.");
			System.out.println("Return value: false was returned");
			System.out.flush();
			return false;
		}
	} // lessThan() end
	
	
	// Checks parameter types then compares their magnitudes to see if the first parameter is greater than the second
	// powering and square-rooting a single value to avoid faulty comparisons due to Double data type calculation
	static Boolean greaterThan(Object first, Object second)
	{
		
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < (sqrt(2.real^2 + 2.imaginary^2)) ) 
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					> (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			// (1.real < (sqrt(2.real^2 + 2.imaginary^2)) )
			Boolean result = (
					(Math.sqrt(Math.pow( ((Number)first).getRealNumber(), 2)))
					> (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < 2.real)
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					> (Math.sqrt(Math.pow( ((Number)second).getRealNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			Boolean result = ( ((Number)first).getRealNumber() > ((Number)second).getRealNumber() )? true: false;
			
			return result;
		}
		
		// default return false with message print if something is wrong
		else
		{
			System.out.println("Error in greaterThan function. The two parameters do not meet any of the case types.");
			System.out.println("Return value: false was returned");
			System.out.flush();
			return false;
		}
	} // greaterThan() end

	
	// Checks parameter types then compares their magnitudes to see if the first parameter is equal to the second
	// powering and square-rooting a single value to avoid faulty comparisons due to Double data type calculation
	static Boolean equals(Object first, Object second)
	{
		
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < (sqrt(2.real^2 + 2.imaginary^2)) ) 
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					== (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			// (1.real < (sqrt(2.real^2 + 2.imaginary^2)) )
			Boolean result = (
					(Math.sqrt(Math.pow( ((Number)first).getRealNumber(), 2)))
					== (Math.sqrt(Math.pow( ((Complex)second).getRealNumber(), 2) + Math.pow( ((Complex)second).getImaginaryNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			// ( (sqrt(1.real^2 + 1.imaginary^2)) < 2.real)
			Boolean result = (
					(Math.sqrt(Math.pow( ((Complex)first).getRealNumber(), 2) + Math.pow( ((Complex)first).getImaginaryNumber(), 2)))
					== (Math.sqrt(Math.pow( ((Number)second).getRealNumber(), 2)))
					)
					? true: false;
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			Boolean result = ( ((Number)first).getRealNumber() == ((Number)second).getRealNumber() )? true: false;
			
			return result;
		}
		
		// default return false with message print if something is wrong
		else
		{
			System.out.println("Error in equals function. The two parameters do not meet any of the case types.");
			System.out.println("Return value: false was returned");
			System.out.flush();
			return false;
		}
	} // equals() end	
	

} // Main class end
