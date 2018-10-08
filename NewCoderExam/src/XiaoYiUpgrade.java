import java.util.Scanner;

public class XiaoYiUpgrade {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int a = sc.nextInt();
			int[] b = new int[n];
			for (int i = 0; i < n; i++) {
				b[i] = sc.nextInt();
				if (b[i] <= a) {
					a += b[i];
				} else {
					a += gcd(a, b[i]);
				}
			}
		}
		sc.close();
	}

	private static int gcd(int a, int b) {
		if (b % a == 0) {
			return a;
		} else {
			return gcd(b % a, a);
		}
	}
}
