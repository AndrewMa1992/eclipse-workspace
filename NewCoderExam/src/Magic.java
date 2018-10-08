import java.util.Scanner;

public class Magic {

	public static void main(String[] args) {
		int n = 0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		String result = "";
		result = magic(n, result);
		char[] results = new char[result.length()];
		results = result.toCharArray();
		for (int i = results.length - 1; i >= 0; i--) {
			System.out.print(results[i]);
		}
		sc.close();
	}

	private static String magic(int n, String result) {
		while (n > 0) {
			if (n % 2 == 0) {
				result += '2';
				n = (n - 2) / 2;
			} else {
				result += '1';
				n = (n - 1) / 2;
			}
		}
		return result;
	}
}
