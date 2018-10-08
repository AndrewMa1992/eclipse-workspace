import java.net.InetAddress;
import java.net.UnknownHostException;

public class reverse {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("Usage: resolve <ip1> <ip2> ... <ipN>");
		}

		String address = new String();

		try {
			for (int i = 0; i < args.length; i++) {
				address = args[i];
				InetAddress ip = InetAddress.getByName(address);
				if (address.compareTo(ip.getHostName()) == 0) {
					System.out.println(address + ":" + address);
				} else {

					System.out.println(address + ":" + ip.getHostName());
				}
			}

		} catch (UnknownHostException e) {

			System.err.println(address + ": Unknown host");
		}
	}
}
