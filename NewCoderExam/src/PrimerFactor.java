import java.util.*;

public class PrimerFactor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long number = 0;
		while (sc.hasNextLong()) {
			number = sc.nextLong();
			isPrimerFactor(number);
		}
		sc.close();
	}

	private static void isPrimerFactor(long num) {
		long number = num;
		while (number != 1) {
			for (int i = 2; i <= number; i++) {
				if (number % i == 0) {
					number /= i;
					System.out.print(i + " ");
					break;
				}
			}
		}
	}
}