import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main1636 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = in.readLine();
		while (line != null && !line.isEmpty()) {
			int len = line.length();
			double zeroCount = 0, oneCount = 0, zeroToZero = 0, zeroToOne = 0;
			for (int i = 0; i < len; i++) {
				if (line.charAt(i) == '1') {
					oneCount++;
					if (line.charAt((i + 1) % len) == '0')
						zeroToOne++;
				} else {
					zeroCount++;
					if (line.charAt((i + 1) % len) == '0')
						zeroToZero++;
				}
			}

			double shoot = zeroToZero / (zeroToZero + zeroToOne);
			double rotate = zeroCount / (zeroCount + oneCount);

			if (Math.abs(shoot - rotate) == 0) {
				out.println("EQUAL");
			} else if (shoot > rotate) {
				out.println("SHOOT");
			} else {
				out.println("ROTATE");
			}

			line = in.readLine();
		}
		out.close();

	}

}
