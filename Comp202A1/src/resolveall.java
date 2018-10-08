import java.net.InetAddress;
import java.net.UnknownHostException;

public class resolveall {

	public static void main(String[] args) {


		if (args.length < 1) {
			System.out.println("Usage: resolve <name1> <name2> ... <nameN>");
		}

		String address = new String();

		try {
			for (int i = 0; i < args.length; i++) {
				address = args[i];
				InetAddress[] ips = InetAddress.getAllByName(address);
				for(int j =0; j < ips.length; j++) {
				System.out.println(address + ":" + ips[j].getHostAddress());
				}
			}

		} catch (UnknownHostException e) {

			System.err.println(address + ": Unknown host");

		}

	

	}

}
