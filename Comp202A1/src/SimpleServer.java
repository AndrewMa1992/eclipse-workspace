import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(0);
		System.out.println(ss.getLocalPort());
		while (true) {
			Socket s = ss.accept();
			
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String address = br.readLine();
			System.out.println(address);
			
			OutputStream os = s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw, true);

			InetAddress ip = s.getInetAddress();
			pw.println("Hello, " + ip.getHostName());
			pw.println("Your IP address is " + ip.getHostAddress());
			s.close();
		}

	}

}
