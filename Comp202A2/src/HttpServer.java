import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServer {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8080);
		System.out.println("A server is started");
		while (true) {
			Socket client = ss.accept();
			System.out.println("The connection has been started, and IP: " + client.getInetAddress().getHostAddress());
			HttpServerSession thread = new HttpServerSession(client);
			thread.start();

		}

	}
}

class HttpServerSession extends Thread {

	private Socket client = null;

	public HttpServerSession(Socket a) {
		this.client = a;
	}

	public void run() {

		try {
			FileInputStream fis = null;
			BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
			InputStream is = client.getInputStream();
			InputStreamReader isw = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isw);
			String request = br.readLine();
			String[] parts = request.split(" ");
			String filename = null;
			if (parts.length == 3) {
				if (parts[0].compareTo("GET") == 0) {
					filename = parts[1].substring(1);
					System.out.println(filename);
				}
			}
			File file = new File(filename);
			if (!file.exists()) {
				println(out, "HTTP/1.1 404 Not Found");
			} else {
				fis = new FileInputStream(file);
				println(out, "HTTP/1.1 200 OK");
				println(out, "Content-Length: " + fis.available());
				println(out, "Last-Modified: " + new Date());
				println(out, "");
				byte[] bytes = new byte[1024];
				while (fis.read(bytes) != -1) {
					out.write(bytes);
					bytes = new byte[1024];
					out.flush();
					Thread.sleep(1000);
				}

				fis.close();
			}
			out.close();
			client.close();
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}

	}

	private void println(BufferedOutputStream bos, String s) throws IOException {
		String news = s + "\r\n";
		byte[] array = news.getBytes();
		for (int i = 0; i < array.length; i++)
			bos.write(array[i]);
		return;
	}

}