import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();

		while (in.hasNextLine()) {
			
			int nextStartPosition = 1;
			line = in.nextLine();

			while (nextStartPosition <= line.length()) {
				if(nextStartPosition == line.length()) {
					nextStartPosition++;
				}else {
				nextStartPosition = Check(line, nextStartPosition);
				}

			}
			System.out.println();
		}
		in.close();
	}

	private static int Check(String line, int nextStartPosition) {
		int count = 1;
		int start = 0;
		int last = 0;
		char firstChar = ' ';
		char lastChar = ' ';
		char middleChar = ' ';
		String finalString = "";
		for (int i = nextStartPosition; i < line.length(); i++) {
			char now = line.charAt(i);
			char pre = line.charAt(i - 1);
			if (now - pre == 1) {
				count++;
			} else {
				if (count >= 4) {
					start = i - count;
					nextStartPosition = start + count + 1;
					firstChar = line.charAt(start);
					last = start + count - 1;
					lastChar = line.charAt(last);
					finalString += firstChar + "-" + lastChar;
					break;

				} else {
					start = i - count;
					nextStartPosition = start + count + 1;
					last = start + count;
					for (int j = start; j < last; j++) {
						middleChar = line.charAt(j);
						finalString += middleChar;
					}
					break;
				}
			}
		}
		System.out.print(finalString);
		return nextStartPosition;
	}
}