import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestHash {

	public static void main(String[] args) {

		File file = null;
		if (args.length != 3) {
			System.err.println("Usage:  java TestHash <L or K> <capacity> <filename>");
			return;
		} else {
			file = new File(args[2]);
		}
		if (!file.exists()) {
			System.err.println("File is not found!");
		}
		try {
			String probing = args[0];
		    int capacity = Integer.parseInt(args[1]);
		    MyHashTable mt = new MyHashTable(capacity, probing);
			Scanner sc = new Scanner(file);
			String s;
			boolean flag = true;
			while (sc.hasNext()) {

				s = sc.nextLine();
				String str[] = s.split(" ");
				int key = Integer.parseInt(str[0]);
				char type = str[1].charAt(0);
				float amount = Float.parseFloat(str[2]);
				Account account = null;
				account = mt.get(key);
				if (account == null && (type == 'd' || type == 'w')) {
					account = new Account(key, 0);
				}

				if (type == 'd') {
					System.out.print("DEPOSIT");
					account.setBalance(account.getBalance() + amount);
					flag = mt.put(account.getKey(),account);
				}

				if (type == 'w') {
					System.out.print("WITHDRAW");
					account.setBalance(account.getBalance() - amount);
					flag = mt.put(account.getKey(),account);
				}

				if (type == 'c') {
					System.out.print("CLOSE");
					mt.remove(key);
				}
				
				if (!flag) {
			          break;
			        }
				System.out.println();
			}

			System.out.println("Result:");
			System.out.println("COLLISIONS:" + mt.getCollisions());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	

	}

}
