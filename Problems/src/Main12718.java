import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main12718 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int cases = Integer.parseInt(in.readLine());

		for (int c = 1; c <= cases; c++) {
			String s = in.readLine();
			int response = 0;
			for (int i = 0; i < s.length(); i++) {
				int[] count = new int[26];
				int odd = 0;
				for (int j = i, m = 1; j < s.length(); j++, m++) {
					count[s.charAt(j) - 'a']++;
					odd += count[s.charAt(j) - 'a'] % 2 == 0 ? -1 : 1;
					if (m % 2 == 0 && odd == 0) {
						response++;
					} else if (m % 2 != 0 && odd == 1) {
						response++;
					}
				}
			}

			out.printf("Case %d: %d%n", c, response);
		}
		out.close();

	}
}
