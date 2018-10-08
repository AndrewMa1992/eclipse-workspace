import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class CountString {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		LinkedList<Integer> nums = new LinkedList<Integer>();
		int index = 0;
		while (index < input.length()) {
			int length = 1;
			while (index < input.length() - 1 && isEquals(input.charAt(index), input.charAt(index + 1))) {
				length++;
				index++;
			}
			nums.add(length);
			index++;
		}

		double sum = 0;
		for (int num : nums) {
			sum += num;
		}

		DecimalFormat df = new DecimalFormat("0.00");
		String result = df.format(sum / nums.size());
		System.out.println(result);
		sc.close();
	}

	private static boolean isEquals(char str1, char str2) {
		if (String.valueOf(str1).equals(String.valueOf(str2)))
			return true;
		return false;
	}
}
