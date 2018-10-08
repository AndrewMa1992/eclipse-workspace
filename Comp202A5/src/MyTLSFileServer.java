import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class MyTLSFileServer {

	public static void main(String[] args) throws UnrecoverableKeyException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException {
		ServerSocketFactory ssf = getSSF();
		SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(40202);
		String EnabledProtocols[] = { "TLSv1.2", "TLSv1.1" };
		ss.setEnabledProtocols(EnabledProtocols);
		while (true) {
			SSLSocket s = (SSLSocket) ss.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String filename = br.readLine();
			System.out.println(filename);
			File file = new File(filename);
			if (!file.exists()) {
				s.close();
			} else {
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
				byte[] bytes = new byte[1024];
				int length = 0;
				dos.writeUTF(file.getName());
				dos.flush();
				while ((length = dis.read(bytes, 0, bytes.length)) > 0) {
					dos.write(bytes, 0, length);
					dos.flush();
				}
				dos.close();
				dis.close();
				s.close();
			}
		}
	}

	private static ServerSocketFactory getSSF()
			throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException, KeyManagementException {
		SSLContext ctx = SSLContext.getInstance("TLS");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		KeyStore ks = KeyStore.getInstance("JKS");
		char[] passphrase = "88406886".toCharArray();
		String cwd = System.getProperty("user.dir");
		ks.load(new FileInputStream(cwd+ "/server.jks"), passphrase);
		kmf.init(ks, passphrase);
		ctx.init(kmf.getKeyManagers(), null, null);
		SSLServerSocketFactory ssf = ctx.getServerSocketFactory();
		return ssf;
	}

}
