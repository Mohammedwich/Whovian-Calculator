//	Mohammed Ahmed 		msa190000

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class Main 
{
	public static void main(String args[]) throws IOException 
	{
		String [] invalidLetters = {"a","b","c","d","e","f","g","h","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
				,"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String fileName;
		Scanner inputScanner = new Scanner(System.in);
		
		System.out.println("Enter a file name to process: ");
		fileName = inputScanner.nextLine();		
	
		File inputfile = new File(fileName);
		File outputFile = new File("results.txt");
		
		//Verify input file exists before using it. If not, exit.
		if(!inputfile.exists())
		{
			System.out.println("Input file does not exist");
			System.exit(-1);
		}
		
		//Try to create output file. Print message and exit program if it fails if it fails.
		try
		{
			outputFile.createNewFile();
		} catch (IOException e)
		{
			System.out.println("Error creating output file");
			e.printStackTrace();
			System.exit(-1);
		}
		
		//Binding Scanner and FileWriter
		Scanner fileReader = new Scanner(inputfile);
		FileWriter writer = new FileWriter(outputFile);
		
		
		//This loop will process each line, create Number objects with the data, perform operation on them, then write the
		// original line followed by \t and the operation result into the output file.
		while(fileReader.hasNextLine())
		{
			String currentLine = fileReader.nextLine();
			Scanner lineReader = new Scanner(currentLine);
			
			//first and second word will hold the values as strings
			String firstWord = lineReader.next();
			String operation = lineReader.next();
			String secondWord = lineReader.next();
			String tempWord = ""; //Will be used to hold minus sign or a full number when setting the imaginary part
			
			//skip line if invalid operator
			
			if(!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/") && 
					!operation.equals("<") && !operation.equals(">") && !operation.equals("=") )
			{
				continue;
			}
			
			
			//skip line if any non-i characters are in the line
			for(String currentChar : invalidLetters)
			{
				if(currentLine.contains(currentChar))
				{
					continue;
				}
			}
			
			// These will hold the parsed numbers
			double firstReal = 0;
			double firstImaginary = 1;	// Set to 1 so we can pass a 1 if just i
			double secondReal = 0;
			double secondImaginary = 1;
			
			// These will hold the created object that will be constructed using first/secondValue
			Number firstNumber;
			Number secondNumber;	
			Number resultNumber;
			Boolean resultBool;
			
			// Parse the first word and create a number object out of the data
			if(!(firstWord.contains("i")) )
			{
				firstReal = Double.parseDouble(firstWord);
				firstNumber = new Number(firstReal);
			}
			else
			{
				firstWord = firstWord.replace("+", " ");
				firstWord = firstWord.replace("-", " -");
				firstWord = firstWord.replaceFirst("i", " ");
				//if we have more than 1 i character, skip line
				if(firstWord.contains("i"))
				{
					continue;
				}
				
				Scanner wordReader = new Scanner(firstWord);
				
				firstReal = Double.parseDouble(wordReader.next() );
				
				//Test if there was a number or only an i, if only i set imaginary to 1
				try
				{
					tempWord = wordReader.next(); // In case we have a minus sign by itself
					firstImaginary = Double.parseDouble(tempWord);
				}
				catch(Exception e)
				{
					if(tempWord.equals("-"))
					{
						firstImaginary = -1.0;
					}
					else
					{
						firstImaginary = 1.0;
					}
				}
				
				firstNumber = new Complex(firstReal, firstImaginary);
				wordReader.close();
				
			} // end of parsing first word and creating first number
			
			
			// Parse the second word and create a number object out of the data
			if(!(secondWord.contains("i")) )
			{
				secondReal = Double.parseDouble(secondWord);
				secondNumber = new Number(secondReal);
			}
			else
			{
				secondWord = secondWord.replace("+", " ");
				secondWord = secondWord.replace("-", " -");
				secondWord = secondWord.replaceFirst("i", " ");
				//if we have more than 1 i character, skip line
				if(secondWord.contains("i"))
				{
					continue;
				}
				
				Scanner wordReader = new Scanner(secondWord);
				
				secondReal = Double.parseDouble(wordReader.next() );
				
				//Test if there was a number or only an i, if only i set imaginary to 1
				try
				{
					tempWord = wordReader.next(); // In case we have a minus sign by itself
					secondImaginary = Double.parseDouble(tempWord);
				}
				catch(Exception e)
				{
					if(tempWord.equals("-"))
					{
						secondImaginary = -1.0;
					}
					else
					{
						secondImaginary = 1.0;
					}
				}
				
				secondNumber = new Complex(secondReal, secondImaginary);
				wordReader.close();
				
			} // end of parsing second word and creating second number
			
			
			//Perform the appropriate operation on both numbers
			switch(operation)
			{
			case "+":
				resultNumber = add(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultNumber.toString() + "\n" );
				break;
				
			case "-":
				resultNumber = subtract(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultNumber.toString() + "\n" );
				break;
				
			case "*":
				resultNumber = multiply(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultNumber.toString() + "\n" );
				break;
				
			case "/":
				try //In case a zero division happens
				{
				resultNumber = divide(firstNumber, secondNumber);
				}
				catch (ArithmeticException e)
				{
					continue;
				}
				writer.write(currentLine + "\t" + resultNumber.toString() + "\n" );
				break;
				
			case "<":
				resultBool = lessThan(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultBool.toString() + "\n" );
				break;
				
			case ">":
				resultBool = greaterThan(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultBool.toString() + "\n" );
				break;
				
			case "=":
				resultBool = equals(firstNumber, secondNumber);
				writer.write(currentLine + "\t" + resultBool.toString() + "\n" );
				break;
			}
			
			
			lineReader.close();
		} // end of while loop, current line processing
		
		
	
		//TODO: close scanners
		inputScanner.close();
		fileReader.close();
		writer.close();
	}	// main end
	
	
	
	// Functions
	// Note: In the case that these functions are fed Objects that are not Number or Complex type, they will return a default Number object
	//************************************************************************************************
	
	
	// Checks parameter types and adds their members accordingly then returns a new object with the new values
	static Number add(Object first, Object second)
	{
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			double real = ((Complex)first).getRealNumber() + ((Complex)second).getRealNumber();
			double imaginary = ((Complex)first).getImaginaryNumber() + ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			double real = ((Number)first).getRealNumber() + ((Complex)second).getRealNumber();
			double imaginary = 0 + ((Complex)second).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			double real = ((Complex)first).getRealNumber() + ((Number)second).getRealNumber();
			double imaginary = ((Complex)first).getImaginaryNumber();
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			double real = ((Number)first).getRealNumber() + ((Number)second).getRealNumber();
			
			Number result = new Number(real);
			return result;
		}
		
		//This part is here to shut the compiler up about the function having no return value(in case none of the ifs work)
		else
		{
			System.out.println("Error in add function. The two parameters do not meet any of the case types.");
			System.out.println("A Number object with value zero has been returned");
			System.out.flush();
			return new Number();
		}
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

	// Checks parameter types and divides the numbers and returns a new Number object with the result
	//The switch in main handles any strange division by zero occurrence. The way this function is set up, it can't divide by zero
	static Number divide(Object first, Object second)
	{
		if((first instanceof Complex) && (second instanceof Complex) )
		{
			Complex result = (Complex)multiply(first, conjugate(second));
			double divisor = Math.pow(((Complex)second).getRealNumber(), 2) + 1;
			
			result.setRealNumber(result.getRealNumber() / divisor);
			result.setImaginaryNumber(result.getImaginaryNumber() / divisor);
			
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Complex) )
		{
			Complex result = (Complex)multiply(first, conjugate(second));
			double divisor = Math.pow(((Complex)second).getRealNumber(), 2) + 1;
			
			result.setRealNumber(result.getRealNumber() / divisor);
			result.setImaginaryNumber(result.getImaginaryNumber() / divisor);
			
			return result;
		}
		
		if((first instanceof Complex) && (second instanceof Number) )
		{
			double divisor = ((Number)second).getRealNumber();
			
			double real = ((Complex)first).getRealNumber() / divisor;
			double imaginary = ((Complex)first).getImaginaryNumber() / divisor;
			
			Number result = new Complex(real, imaginary);
			return result;
		}
		
		if((first instanceof Number) && (second instanceof Number) )
		{
			double real = ((Number)first).getRealNumber() / ((Number)second).getRealNumber();
			
			Number result = new Number(real);
			return result;
		}
		
		//This part is here to shut the compiler up about the function having no return value(in case none of the ifs work)
		else
		{
			System.out.println("Error in divide function. The two parameters do not meet any of the case types.");
			System.out.println("A Number object with value zero has been returned");
			System.out.flush();
			return new Number();
		}
	} // divide() end
	

	static Complex conjugate(Object theNum)
	{
		return new Complex(((Complex)theNum).getRealNumber(), (-1*((Complex)theNum).getImaginaryNumber()) );
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
