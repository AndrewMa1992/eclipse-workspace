
import java.util.Scanner;

public class Singer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int[] array = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				array[i] = sc.nextInt();
			}
			int K = sc.nextInt();
			int d = sc.nextInt();
			long[][] fmax = new long[K + 1][n + 1];
			long[][] fmin = new long[K + 1][n + 1];
			long res = Integer.MIN_VALUE;
			for (int i = 1; i <= n; i++) {
				fmax[1][i] = array[i];
				fmin[1][i] = array[i];
				for (int k = 2; k <= K; k++) {
					for (int j = i - 1; j > 0 && i - j <= d; j--) {
						fmax[k][i] = Math.max(fmax[k][i],
								Math.max(fmax[k - 1][j] * array[i], fmin[k - 1][j] * array[i]));
						fmin[k][i] = Math.min(fmin[k][i],
								Math.min(fmax[k - 1][j] * array[i], fmin[k - 1][j] * array[i]));
					}
				}
				res = Math.max(res, fmax[K][i]);
			}
			System.out.println(res);
		}
		sc.close();
	}
}