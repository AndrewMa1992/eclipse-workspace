import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MyTLSFileClient {

	public static void main(String[] args)
			throws NumberFormatException, UnknownHostException, IOException, InvalidNameException {
		if (args.length != 3) {
			System.out.println("java MyTLSFileClient [host] [port] [filename]");
		}
		String host = args[0];
		String port = args[1];
		String filename = args[2];
		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) factory.createSocket(host, Integer.parseInt(port));
		String EnabledProtocols[] = { "TLSv1.2", "TLSv1.1" };
		socket.setEnabledProtocols(EnabledProtocols);
		SSLParameters params = new SSLParameters();
		params.setEndpointIdentificationAlgorithm("HTTPS");
		socket.setSSLParameters(params);
		socket.startHandshake();
		SSLSession sesh = socket.getSession();
		X509Certificate cert = (X509Certificate) sesh.getPeerCertificates()[0];
		String commonName = getCommonName(cert);
		System.out.println(commonName);

		if (host.equals(commonName)) {
			System.out.println(true);
		}
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		out.println(filename);
		out.flush();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		String name = "_" + dis.readUTF();
		String cwd = System.getProperty("user.dir");
		String filePath = cwd+ "/file/" + name;
		System.out.println(filePath);
		byte[] bytes = new byte[1024];
		int length = 0;
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));
		while ((length = dis.read(bytes, 0, bytes.length)) > 0) {
			dos.write(bytes, 0, length);
			dos.flush();
		}
		dos.close();
		dis.close();
		socket.close();
	}

	static String getCommonName(X509Certificate cert) throws InvalidNameException {
		String name = cert.getSubjectX500Principal().getName();
		LdapName ln = new LdapName(name);
		String cn = null;
		for (Rdn rdn : ln.getRdns())
			if ("CN".equalsIgnoreCase(rdn.getType()))
				cn = rdn.getValue().toString();
		return cn;
	}
}
