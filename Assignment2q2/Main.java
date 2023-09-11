import java.util.Scanner;

public class Main {
//Main method to use BigInt class
	public static void main(String[] args) {
		
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter two number according to the format");
			System.out.println("Example:+12345 or -12567890 Note:Zero is always positive");
			//Get two numbers from user
			BigInt num1 = new BigInt(scan.nextLine());
			BigInt num2 = new BigInt(scan.nextLine());
			//Try all the methods
			System.out.println("equal:"+num1.equal(num2));
			System.out.println("copare:"+num1.compareTo(num2));
			System.out.println("plus:"+num1.plus(num2));
			System.out.println("minus:"+num1.minus(num2));
			System.out.println("multiply:"+num1.multiply(num2));
			System.out.println("divide:"+num1.divide(num2));
		}
		//Find exceptions
		catch (IllegalArgumentException e){//Check invalid input of BigInt
			System.out.println("one of the number is invalid, please try again");
			main(null);
		}
		catch (ArithmeticException e){//Divide in 0
			System.out.println("Error, no divide in zero");
			main(null);
		}
		
	}
}
