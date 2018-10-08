import java.util.*;

public class MaxTree {
	public int[] buildMaxTree(int[] A, int n) {
		// write code here
		if (n <= 0) {
			return null;
		}
		int[] leftPosition = new int[n];
		int[] rightPosition = new int[n];
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < n; i++) {
			leftPosition[i] = -1;
			rightPosition[i] = -1;
			result[i] = -1;
		}
		for (int i = 0; i < n; i++) {
			while (!stack.empty() && A[i] > A[stack.peek()]) {
				stack.pop();
			}
			if (!stack.empty()) {
				leftPosition[i] = stack.peek();
			}
			stack.push(i);
		}
		while (!stack.empty()) {
			stack.pop();
		}

		for (int i = n - 1; i >= 0; i--) {
			while (!stack.empty() && A[i] > A[stack.peek()]) {
				stack.pop();
			}
			if (!stack.empty()) {
				rightPosition[i] = stack.peek();
			}
			stack.push(i);
		}

		for (int i = 0; i < n; i++) {
			if (leftPosition[i] >= 0 && rightPosition[i] >= 0) {
				result[i] = A[leftPosition[i]] >= A[rightPosition[i]] ? rightPosition[i] : leftPosition[i];
			}
			if (leftPosition[i] >= 0 && rightPosition[i] == -1)
				result[i] = leftPosition[i];
			if (rightPosition[i] >= 0 && leftPosition[i] == -1)
				result[i] = rightPosition[i];
		}

		return result;
	}
}