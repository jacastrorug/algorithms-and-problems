package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

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
