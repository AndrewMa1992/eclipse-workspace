import java.util.Scanner;


public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if(sc.hasNext()) {
			String[] words = sc.nextLine().split(" ");
			System.out.println(words[words.length-1].length());
		}
		sc.close();
	}
}