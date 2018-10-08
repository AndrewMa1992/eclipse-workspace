import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIPAddrews {

	public static void main(String[] args) {
		
		String nameAddress = "sorcerer.cms.waikato.ac.nz";
		String ipAddress = "130.217.250.39";
		try {
			InetAddress ip1 = InetAddress.getByName(nameAddress);
			System.out.println(nameAddress + ":" +ip1.getHostAddress());
			InetAddress ip2 = InetAddress.getByName(ipAddress);
			System.out.println(ipAddress + ":" +ip2.getHostName());
		}catch(UnknownHostException e){
			
			System.err.println(nameAddress + ": Unknown host");
			System.err.println(ipAddress + ": Unknown host");
			
		}

	}

}
