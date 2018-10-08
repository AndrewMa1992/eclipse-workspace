import java.util.*;
import java.io.*;

/*
 * prefixComparator
 *
 * this class is used when sorting an array in Java.
 */
class prefixComparator implements Comparator<prefix> {
	@Override
	public int compare(prefix a, prefix b) {
		/*
		 * XXX: return a value such that the array is ordered from most specific to
		 * least specific. take a look at the documentation at
		 * http://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html on how the
		 * return value from this method will impact sort order. make sure the longest
		 * prefixes are sorted so that they come first!
		 */
		for (int i = 0; i < 4; i++) {
			int cmp = b.net[i] - a.net[i];
			if (cmp != 0) {
				return cmp;
			}
		}
		return b.len - a.len;
	}
};

/*
 * prefix
 *
 * this class holds details of a prefix: the network address, the prefix length,
 * and details of the autonomous systems that announce it.
 *
 */
class prefix {
	public int[] net = { 0, 0, 0, 0 };
	public int len;
	public String asn;

	public prefix(String net, int len, String asn) {
		/*
		 * XXX: initialise the object given the inputs. break the network ID into four
		 * integers.
		 */

		String[] tokens = net.split("\\.");
		if (tokens.length == 4) {
			for (int i = 0; i < tokens.length; i++) {
				this.net[i] = Integer.parseInt(tokens[i]);
			}
		}
		this.len = len;
		this.asn = asn;
	}

	public String toString() {
		/* pretty print out of the prefix! my lecturer is kind! */
		return net[0] + "." + net[1] + "." + net[2] + "." + net[3] + "/" + len;
	}

	/*
	 * match
	 *
	 * given an address, determine if it is found in this prefix structure or not.
	 */
	public boolean match(String addr) {
		int[] mask = { 0x80, 0xC0, 0xE0, 0xF0, 0xF8, 0xFC, 0xFE, 0xFF };
		int i;

		/*
		 * XXX: break up the address passed in as a string
		 */
		String[] tokens = addr.split("\\.");
		for (i = 0; i < 4 && i * 8 < this.len; i++) {
			/*
			 * XXX: compare up to four different values in the dotted quad, (i.e. enough to
			 * cover this.len) to determine if this address is a match or not
			 */

			int net = Integer.parseInt(tokens[i]);
			int maskPosition;
			if (i * 8 < this.len) {
				maskPosition = 7;
			} else {
				maskPosition = this.len % 8 - 1;
			}

			if ((this.net[i] & mask[maskPosition]) != (net & mask[maskPosition])) {
				return false;
			}

		}
		return true;

	}
}

class ip2as {
	public static void main(String args[]) {
		if (args.length < 3) {
			/* always check the input to the program! */
			System.err.println("usage: ip2as <prefixes> <asnames> [ip0 ip1 .. ipN]");
			return;
		}

		/* read the prefix list into a list */
		ArrayList<prefix> list = new ArrayList<prefix>();
		try {
			BufferedReader file = new BufferedReader(new FileReader(args[0]));
			String line;

			while ((line = file.readLine()) != null) {
				/* XXX: add code to parse the ip2as line */
				String net, ases;
				int len;
				String[] tokens = line.split("/| ");
				if (tokens.length == 3) {
					net = tokens[0];
					len = Integer.parseInt(tokens[1]);
					ases = tokens[2];
					if (len >= 8 && len <= 24) {
						/* create a new prefix object and stuff it in the list */
						prefix pf = new prefix(net, len, ases);
						list.add(pf);
					}
				}

			}
			file.close();
		} catch (FileNotFoundException e) {
			System.err.println("could not open prefix file " + args[0]);
			return;
		} catch (IOException e) {
			System.err.println("error reading prefix file " + args[0] + ": " + e);
		}

		/*
		 * take the list of prefixes and transform it into a sorted array i'd like to
		 * thank my lecturer for giving me this code.
		 */
		prefix[] x = new prefix[list.size()];
		list.toArray(x);
		Arrays.sort(x, new prefixComparator());

		/*
		 * read in the asnames file so that we can report the network's name with its
		 * ASN
		 */
		HashMap<String, String> asnames = new HashMap<String, String>();
		try {
			BufferedReader file = new BufferedReader(new FileReader(args[1]));
			String line;
			while ((line = file.readLine()) != null) {
				String ases, name;
				String[] tokens = line.split(" ", 2);
				if (tokens.length == 2) {
					ases = tokens[0];
					name = tokens[1];
					asnames.put(ases, name);
				}
			}
			file.close();
		} catch (FileNotFoundException e) {
			System.err.println("could not open asnames file " + args[1]);
			return;
		} catch (IOException e) {
			System.err.println("error reading asnames file " + args[1] + ": " + e);
		}

		/*
		 * for all IP addresses supplied on the command line, print out the
		 * corresponding ASes that announce a corresponding prefix, as well as their
		 * names. if there is no corresponding prefix, print the IP address and then say
		 * no corresponding prefix.
		 */
		for (int i = 2; i < args.length; i++) {
			int matched = -1;
			String ip = args[i];
			/*
			 * x contains the sorted array of prefixes, organised longest to shortest prefix
			 * match
			 */
			for (int j = 0; j < x.length; j++) {
				prefix p = x[j];

				/*
				 * XXX: check if this prefix matches the IP address passed in
				 */
				if (p.match(ip)) {
					matched = j;
					break;
				}
			}
			/*
			 * XXX: print something out if it was not matched
			 */
			if (matched == -1) {
				System.out.println(ip + " : no prefix");
			} else {
				prefix p = x[matched];
				String[] tokens = p.asn.split("_");
				for (int j = 0; j < tokens.length; j++) {
					String asn = tokens[j];
					String asname;
					if (asnames.containsKey(asn)) {
						asname = asnames.get(asn);
					} else {
						asname = "";
					}
					System.out.println(ip + " " + p + " " + asn + " " + asname);
				}
			}
		}
		return;
	}
};
