import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main11795 {

	public static long states[] = new long[1 << 16];
	public static int visited[] = new int[1 << 16];
	public static int bots[] = new int[16];
	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int cases = Integer.parseInt(in.readLine());
		for (int c = 1; c <= cases; c++) {
			N = Integer.parseInt(in.readLine());
			long inicio = 0;
			for (int i = 0; i <= N; i++) {
				String d = in.readLine();
				for (int j = 0; j < N; j++) {
					if (i == 0) {
						inicio |= d.charAt(j) == '1' ? (1 << j) : 0;
					} else {
						bots[i - 1] |= (d.charAt(j) == '1' ? 1 : 0) << j;
					}
				}
			}
			System.out.println(Arrays.toString(bots));
			out.printf("Case %d: %d%n", c, dfs(0, inicio));
		}

		out.close();
		in.close();
	}

	public static long dfs(int state, long path) {

		if (state == (1 << N) - 1) {
			return 1;
		}

		long ret = 0;
		for (int i = 0; i < N; i++) {
			if ((state >> i) == 0 && (path >> i) != 0) {
				ret += dfs(state | (1 << i), path | bots[i]);
			}
		}

		return ret;
	}

	/**
	 * 
	 * 
	 * 3 110 101 111 001
	 * 
	 * -> 4
	 */

}
