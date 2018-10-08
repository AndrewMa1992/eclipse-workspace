import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

	public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {

		if (args.length != 2) {
			System.out.println("Usage: SimpleClient <address> <port>");
			return;
		}

		Socket s = new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]));

		OutputStream os = s.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw, true);
		pw.println("Hello?");

		InputStream is = s.getInputStream();
		InputStreamReader isw = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isw);

		String response;
		while ((response = br.readLine()) != null) {
			System.out.println(response);
		}
		s.close();

	}

}
