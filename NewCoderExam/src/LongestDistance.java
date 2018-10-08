
public class LongestDistance {
	public static void main(String[] args) {

	}

	public int getDis(int[] A, int n) {
		int dis = 0;
		if (n > 1) {
			int min = A[0];
			for (int i = 1; i < n; i++) {
				if (A[i] - min > dis) {
					dis = A[i] - min;
				}
				if (min > A[i]) {
					min = A[i];
				}
			}
		}
		return dis;
	}
}
