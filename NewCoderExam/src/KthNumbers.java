
public class KthNumbers {
	public static int[] findKthNumbers(int[] A, int n, int k) {
		int count = n - k;
		int length = A.length;
		while (count > 0) {
			int maxNumberIndex = 0;
			int i;
			for (i = 1; i < length; i++) {
				if (A[maxNumberIndex] < A[i]) {
					maxNumberIndex = i;
				}
			}
			for (int j = maxNumberIndex; j < length - 1; j++) {
				A[j] = A[j + 1];
			}
			length--;
			count--;
		}
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = A[i];
		}
		return result;
	}
}