import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class XProcess {

	static AccountBST bst = new AccountBST(null, null, null);

	public static void main(String[] args) {
		File file = null;
		if (args.length != 1) {
			System.err.println("Arguments are wrong number!");
			return;
		} else {
			file = new File(args[0]);
		}
		if (!file.exists()) {
			System.err.println("File is not found!");
		}
		try {
			Scanner sc = new Scanner(file);
			String s;

			while (sc.hasNext()) {

				s = sc.nextLine();
				String str[] = s.split(" ");
				int key = Integer.parseInt(str[0]);
				char type = str[1].charAt(0);
				float amount = Float.parseFloat(str[2]);
				Account account = null;
				account = bst.find(key);
				if (account == null && (type == 'd' || type == 'w')) {
					account = new Account(key, 0);
				}

				if (type == 'd') {
					System.out.print("DEPOSIT");
					account.setBalance(account.getBalance() + amount);
					bst.insert(account.getKey(), account.getBalance());
				}

				if (type == 'w') {
					System.out.print("WITHDRAW");
					account.setBalance(account.getBalance() - amount);
					bst.insert(account.getKey(), account.getBalance());
				}

				if (type == 'c') {
					System.out.print("CLOSE");
					bst.remove(key);
				}
				System.out.println();
			}

			System.out.println("Result:");
			bst.traverse();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
