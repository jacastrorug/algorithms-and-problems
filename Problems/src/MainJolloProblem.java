import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class MainJolloProblem {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = in.readLine().trim();
		StringTokenizer tok = new StringTokenizer(line);

		int A = Integer.parseInt(tok.nextToken()), B = Integer.parseInt(tok.nextToken()),
				C = Integer.parseInt(tok.nextToken());

		int X = Integer.parseInt(tok.nextToken()), Y = Integer.parseInt(tok.nextToken());

		while (A != 0 && B != 0) {

			HashSet<Integer> used = new HashSet<>();
			used.addAll(Arrays.asList(A,B,C,X,Y));

			int[] princes = { A, B, C };
			int[] prince = { X, Y };

			Arrays.sort(princes);
			Arrays.sort(prince);

			int j = 1;
			boolean usado = false;
			int ganadas = 0;
			int max = -1;
			for (int i = 2; i > -1 && j > -1; i--) {
				if (princes[i] > prince[j]) {
					if (!usado) {
						for (int k = princes[i] + 1; k < 53; k++) {
							if (!used.contains(k)) {
								max = k;
								usado = true;
								ganadas++;
								used.add(k);
								break;
							}
						}

						if (max == -1)
							j--;

					} else {
						j--;
					}
				} else {
					j--;
					ganadas++;
				}
			}
			
			if(ganadas >= 2) {
				if(max == -1) {
					int p = princes[0];
					for (int i = p +1 ; i < 53; i++) {
						if(!used.contains(i)) {
							out.println(i);
							break;
						}
					}
				}else {
					out.println(max);
				}
			}else {
				out.println(-1);
			}
			
			line = in.readLine().trim();
			tok = new StringTokenizer(line);

			A = Integer.parseInt(tok.nextToken());
			B = Integer.parseInt(tok.nextToken());
			C = Integer.parseInt(tok.nextToken());

			X = Integer.parseInt(tok.nextToken());
			Y = Integer.parseInt(tok.nextToken());
		}

		in.close();
		out.close();

	}

}
