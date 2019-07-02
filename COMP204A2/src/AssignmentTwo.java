

import java.util.LinkedList;
import java.util.Scanner;

import scrollback.Scrollback;

public class AssignmentTwo {

	private static Scanner scan = new Scanner(System.in);
	private static Scrollback sb = null;

	public static void main(String[] args) {
		System.out.println("Welcome to the AssignmentTwo interactive console!");
		System.out.println();
		System.out.println("Type items followed by return to add them to the scrollback.");
		System.out.println("Special commands:");
		System.out.println(
				"  .    - Retrieve and add the last command. Multiple periods will look back further in the scrollback");
		System.out.println("  show - Print details about the scrollback");
		System.out.println("  rst  - Reset the scrollback");
		System.out.println("  quit - Exit this console");
		System.out.println();
		System.out.println("Please enter a scrollback size (default = 10)");
		System.out.print("> ");
		String command = scan.nextLine();
		if (command.equals("")) {
			sb = new Scrollback();
			System.out.println("Created Scrollback of size 10");
		} else {
			sb = new Scrollback(Integer.parseInt(command));
			System.out.println("Created Scrollback of size " + command);
		}
		System.out.print("> ");
		while (true) {
			command = scan.nextLine();
			if (command.equals("show")) {
				show();
			} else if (command.equals("rst")) {
				rst();
			} else if (command.equals("quit")) {
				break;
			} else if (command.startsWith(".")) {
				retrieve(command);
			} else {
				sb.add(command);
				System.out.println("OK.");
				System.out.print("> ");
			}
		}
	}

	private static void retrieve(String command) {
		int size = command.length();
		String str = "";
		for (int i = 0; i < size; i++) {
			str = sb.getLast();
		}
		System.out.println(str);
		sb.add(str);
		System.out.print("> ");
	}

	private static void rst() {
		sb.clear();
		System.out.println("Scrollback reset");
		System.out.print("> ");
	}

	private static void show() {
		System.out.println("Scrollback capacity: " + sb.getCapacity());
		System.out.println("Current elements: " + sb.getCount());
		LinkedList<String> items = sb.items;
		for (int i = items.size() - 1; i >= 0; i--) {
			System.out.println("-> " + items.get(i));
		}
		System.out.print("> ");
	}
}
