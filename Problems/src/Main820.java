import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * This class solved the 820 - Internet Bandwidth in UVA
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=761
 */
public class Main820 {
	public static void main(String[] args) throws Exception {
		BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int nodos = Integer.parseInt(tec.readLine());
		int caso = 1;
		while (nodos != 0) {
			StringTokenizer tok = new StringTokenizer(tec.readLine());
			int s = Integer.parseInt(tok.nextToken());
			int t = Integer.parseInt(tok.nextToken());
			int ady[][] = new int[nodos][nodos];
			for (int i = 0; i < ady.length; i++) {
				Arrays.fill(ady[i], -1);
			}
			int C[][] = new int[nodos][nodos];
			int conexiones = Integer.parseInt(tok.nextToken());
			for (int i = 0; i < conexiones; i++) {
				tok = new StringTokenizer(tec.readLine());
				int a = Integer.parseInt(tok.nextToken());
				int b = Integer.parseInt(tok.nextToken());
				int c = Integer.parseInt(tok.nextToken());
				ady[a - 1][b - 1] = b - 1;
				ady[b - 1][a - 1] = a - 1;
				C[b - 1][a - 1] = C[a - 1][b - 1] += c;
			}
			out.println("Network " + caso);
			out.println("The bandwidth is " + edmondsKarp(ady, C, s - 1, t - 1) + ".");
			out.println();
			nodos = Integer.parseInt(tec.readLine());
			caso++;
		}
		out.close();
		tec.close();
	}

	public static int edmondsKarp(int[][] E, int[][] C, int s, int t) {
		int n = C.length;
		int[][] F = new int[n][n];
		while (true) {
			int[] P = new int[n];
			Arrays.fill(P, -1);
			P[s] = s;
			int[] M = new int[n];
			M[s] = Integer.MAX_VALUE;
			Queue<Integer> Q = new LinkedList<Integer>();
			Q.offer(s);
			LOOP: while (!Q.isEmpty()) {
				int u = Q.poll();
				for (int v : E[u]) {
					if (v != -1) {
						if (C[u][v] - F[u][v] > 0 && P[v] == -1) {
							P[v] = u;
							M[v] = Math.min(M[u], C[u][v] - F[u][v]);
							if (v != t)
								Q.offer(v);
							else {
								while (P[v] != v) {
									u = P[v];
									F[u][v] += M[t];
									F[v][u] -= M[t];
									v = u;
								}
								break LOOP;
							}
						}
					}
				}
			}
			if (P[t] == -1) {
				int sum = 0;
				for (int x : F[s])
					sum += x;
				return sum;
			}
		}
	}
}