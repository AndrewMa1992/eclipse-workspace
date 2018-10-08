import java.util.Scanner;

public class OppsiteNumber {

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int oppsiteNumber = 0;
		int originnalNumber = n;
		while (!(originnalNumber < 1)) {
			oppsiteNumber = oppsiteNumber * 10 + originnalNumber % 10;
			originnalNumber /= 10;
		}
		System.out.println(n + oppsiteNumber);
		sc.close();
	}
}
