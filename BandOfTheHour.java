import java.util.Scanner;
import java.util.Arrays;
//=============================================================================
public class BandOfTheHour{
//-----------------------------------------------------------------------------
	public static final Scanner keyboard = new Scanner(System.in);
	public static int MAX_ROWS = 10;
	public static int MAX_POSITIONS = 8;
	public static double MIN_WEIGHT = 45.0;
	public static double MAX_WEIGHT = 200.0;
  public static double MAX_AVERAGE_WEIGHT = 100.0;
//-----------------------------------------------------------------------------
	public static void main (String[] args){

	double [][] bandstand;
	int rows;
	int position;
	char[] rowNumber;
	int index;
	char choice;



//----Greeting Message

	System.out.println("Welcome to the Band of the Hour");
	System.out.println("-------------------------------");


//----Rows and Positions

	System.out.print("Please enter number of rows: ");
		do {
			rows = keyboard.nextInt();
			if (rows < 0 || rows >= MAX_ROWS) {
				System.out.print("ERROR: Out of range, try again: ");
			}
		} while (rows < 0 || rows >= MAX_ROWS);

	rowNumber = new char[rows];

	bandstand = new double [rows][];

	for (index = 0; index < rowNumber.length; index++) {
		rowNumber[index] = (char)(index + (int)'A');
		System.out.print("Please enter number of positions in row " +
		rowNumber[index] + ": ");
		position = keyboard.nextInt();
		while (position <= 0 || position >= MAX_POSITIONS)
		{System.out.print("ERROR: Out of range, try again: ");
		position = keyboard.nextInt();
		}
		bandstand [index] = new double[position];
	}

	System.out.println();


//----Menu

//----Checks if valid command; programs closes if 'x'
	do {
		System.out.println();
		System.out.print("(A)dd, (R)emove, (P)rint,          e(X)it : ");
		choice = keyboard.next().charAt(0);
		choice = Character.toUpperCase(choice);
		while (choice != 'A' && choice != 'R' && choice != 'P' && choice != 'X')
		{
			System.out.print("ERROR: Invalid option, try again          : ");
			choice = keyboard.next().charAt(0);
		}
		System.out.println();
		System.out.println();

		switch(choice){

		  case 'A':
			addMusician(bandstand, rowNumber);
			break;

		  case 'R':
			removeMusician(bandstand, rowNumber);
			break;

		  case 'P':
			printMusician(bandstand);
			break;

		  case 'X':
			break;

		  default:
			if (choice != 'X') {

			break;

			}
		}
	} while (choice != 'X');
}
//-----------------------------------------------------------------------------
//----Add Musician
	private static void addMusician(double[][] bandstand, char[] rowNumber){

   	    int row;
	    char rowLetter;
	    int position;
	    double weight = 0;
	    int index2;
	    double weightTotal = 0;
	    double weightAverage = 0;

//----Check if existing row
	    System.out.print("Please enter row letter                   : ");
	    rowLetter = keyboard.next().toUpperCase().charAt(0);
	    row = (int)(rowLetter - 'A');
	    while (row >= rowNumber.length) {
	   	    System.out.print("ERROR: Out of range, try again            : ");
		    rowLetter = keyboard.next().toUpperCase().charAt(0);
		    row = (int)(rowLetter - 'A');
	    }

//----Check if position exists and is vacant
	    System.out.print("Please enter position number (1 to " +
bandstand[row].length + ")     : ");
	    position = keyboard.nextInt();
	    while (position < 1 || position > bandstand[row].length) {
	     	System.out.print("ERROR: Out of range, try again            : ");
		    position = keyboard.nextInt();
	    }
	    if (bandstand[row][position-1] > 0) {
		    System.out.println("ERROR: There is already a musician there.");
		    System.out.println();
		    return;
	    }

//----Enter weight and see if over limit
	    System.out.print("Please enter weight (45.0 to 200.0)       : ");
	    weight = keyboard.nextInt();
	    while (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
		    System.out.print("ERROR: Out of range, try again            : ");
		    weight = keyboard.nextDouble();
	    }
	    bandstand[row][position-1] = weight;
	    for (index2 = 0; index2 < bandstand[row].length; index2++) {
		    weightTotal += bandstand[row][index2];
	    }
	    weightAverage = (weightTotal / bandstand[row].length);

	    if (weightAverage > MAX_AVERAGE_WEIGHT) {
		    System.out.println("ERROR: That would exceed the average weight limit.");
		    bandstand[row][position-1] = 0;
		    System.out.println();
		    return;
	    }

//---If info checks, musician is added and array is returned
	    System.out.println("****** Musician added.");
	    System.out.println();

	   return;
    }

//-----------------------------------------------------------------------------
//----Remove Musician
	private static void removeMusician(double[][] bandstand, char[] rowNumber){

		int row;
		char rowletter;
		int position;
		char rowLetter;

//----Checks if existing row
		System.out.print("Please enter row letter                   : ");
		rowLetter = keyboard.next().toUpperCase().charAt(0);
		row = (int)(rowLetter - 'A');
		while (row >= rowNumber.length) {
			System.out.print("ERROR: Out of range, try again            : ");
		 	rowLetter = keyboard.next().toUpperCase().charAt(0);
		 	row = (int)(rowLetter - 'A');
		}

//----Checks if position exists and vacant
		System.out.print("Please enter position number (1 to " +
bandstand[row].length + ")     : ");
		position = keyboard.nextInt();
		while (position < 1 || position > bandstand[row].length) {
		 	System.out.print("ERROR: Out of range, try again            : ");
		 	position = keyboard.nextInt();
		}
		if (bandstand[row][position-1] == 0) {
			System.out.println("ERROR: That position is vacant.");
			System.out.println();
			return;
		}

		bandstand[row][position-1] = 0;

//----Once info is accurate, musician is removed and array is returned
		System.out.print("****** Musician removed.");
		System.out.println();
	    return;
    }
//----Print Musician
	private static void printMusician(double[][] bandstand) {

        int index;
        int index2;
        double totals = 0;

//----Prints array and row total weight and average
        for (index = 0; index < bandstand.length; index++){
			System.out.print((char)(65 + index) + ": " );
			for (index2 = 0; index2 <= MAX_POSITIONS; index2++){
				if (bandstand[index].length > index2){
				   	System.out.printf("%6.1f ", bandstand[index][index2]);
			        totals += bandstand[index][index2];
		    	} else {
		      	System.out.print("       ");
			    }
			}
			System.out.printf("[%3.1f, "
			+ "%3.1f]%n", totals, ((totals/(bandstand[index].length))));
			totals = 0;
		}
		System.out.println();
	}
//-----------------------------------------------------------------------------
}
//=============================================================================
