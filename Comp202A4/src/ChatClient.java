import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ChatClient extends Thread {

	private MulticastSocket socket = null;
	private static final String MULTICAST_IP = "239.0.202.1";
	private static final int BROADCAST_PORT = 40202;
	private static final int DATA_LEN = 4096;
	byte[] data = new byte[DATA_LEN];
	private InetAddress multicastAddress = null;
	private DatagramPacket inputPacket = new DatagramPacket(data, data.length);
	private DatagramPacket outputPacket = null;

	public static void main(String[] args) throws IOException {
		ChatClient chat = new ChatClient();
		chat.start();
		chat.initalize();
	}

	public ChatClient() throws IOException {
		socket = new MulticastSocket(BROADCAST_PORT);
		multicastAddress = InetAddress.getByName(MULTICAST_IP);
		socket.joinGroup(multicastAddress);
		socket.setLoopbackMode(false);
		outputPacket = new DatagramPacket(new byte[0], 0, multicastAddress, BROADCAST_PORT);
	}

	public void initalize() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String text = null;
		while ((text = br.readLine()) != null) {
			byte[] data = text.getBytes();
			outputPacket.setData(data);
			socket.send(outputPacket);
		}

	}

	public void run() {
		while (true) {
			try {
				socket.receive(inputPacket);
				System.out.println(
						inputPacket.getSocketAddress() + ":" + new String(this.data, 0, inputPacket.getLength()));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
