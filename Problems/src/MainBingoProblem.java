import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MainBingoProblem {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		String line = in.readLine().trim();
		StringTokenizer tok = new StringTokenizer(line);
		int N = Integer.parseInt(tok.nextToken());
		int B = Integer.parseInt(tok.nextToken());

		while (B != 0 && N != 0) {
			line = in.readLine().trim();
			tok = new StringTokenizer(line);
			int[] balls = new int[B];
			boolean[] fill = new boolean[N + 1];
			int count = 0;
			for (int i = 0; i < B; i++) {
				balls[i] = Integer.parseInt(tok.nextToken());
				if (balls[i] < fill.length && !fill[balls[i]]) {
					fill[balls[i]] = true;
					count++;
				}
				for (int j = 0; j < i; j++) {
					int diffLeft = Math.abs(balls[i] - balls[j]);
					if (diffLeft < fill.length && !fill[diffLeft]) {
						fill[diffLeft] = true;
						count++;
					}

					int diffRight = Math.abs(balls[j] - balls[i]);
					if (diffRight < fill.length && !fill[diffRight]) {
						fill[diffRight] = true;
						count++;
					}

				}
			}
			out.println((count - 1) == N ? "Y" : "N");

			line = in.readLine().trim();
			tok = new StringTokenizer(line);
			N = Integer.parseInt(tok.nextToken());
			B = Integer.parseInt(tok.nextToken());
		}

		in.close();
		out.close();

	}

}