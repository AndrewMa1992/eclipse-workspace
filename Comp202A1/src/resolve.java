import java.net.InetAddress;
import java.net.UnknownHostException;

public class resolve {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Usage: resolve <name1> <name2> ... <nameN>");
		}

		String address = new String();

		try {
			for (int i = 0; i < args.length; i++) {
				address = args[i];
				InetAddress ip = InetAddress.getByName(address);
				System.out.println(address + ":" + ip.getHostAddress());
			}

		} catch (UnknownHostException e) {

			System.err.println(address + ": Unknown host");

		}

	}

}
