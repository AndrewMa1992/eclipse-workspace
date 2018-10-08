import java.util.Scanner;

public class Main1 {
final static int COUNT = 3;
public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int number = Integer.parseInt(line);
		int sum = 0;
	    for (int i = 2; i <= number; i++) 
	    	sum = (sum+COUNT)%i; 
	    System.out.println(sum+1);
	}
}
