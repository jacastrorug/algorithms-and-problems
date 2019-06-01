import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class solved the 259 - Software Allocation in UVA
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=195
 */
public class Main259 {
	static int[][] g = new int[38][38];
	static int V = 38;
	static int flujo = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		String s = tec.readLine();
		boolean fin = false;
		while (s != null) {
			int tareas = 0;
			while (!s.equals("")) {
				int servicio = ' ';
				int ad = 0;
				for (int i = 27; i < 37; i++)
					g[i][37] = 1;
				for (int i = 0; i < s.length(); i++) {
					if (i == 0) {
						servicio = (int) (s.charAt(0) - 'A' + 1);
					} else if (i == 1) {
						ad = Integer.parseInt(s.charAt(1) + "");
						g[0][servicio] = ad;
						tareas += ad;
					} else if (i > 2 && s.charAt(i) != ';') {
						int nodo = Integer.parseInt(s.charAt(i) + "");
						g[servicio][nodo + 27] = 1;
					}
				}
				s = tec.readLine();
				if (s == null) {
					fin = true;
					break;
				}
			}
			int rGraph[][] = fordFulkerson(g, 0, 37);
			char[] result = new char[10];
			Arrays.fill(result, '_');
			for (int i = 27; i < 37; i++) {
				for (int j = 1; j < 27; j++) {
					if (rGraph[i][j] == 1) {
						result[i - 27] = (char) (j + 'A' - 1);
						break;
					}
				}
			}

			if (flujo == tareas) {
				System.out.println(new String(result));
			} else {
				System.out.println("!");
			}
			if (!fin)
				s = tec.readLine();
		}
	}

	static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; ++i)
			visited[i] = false;

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;

		while (queue.size() != 0) {
			int u = queue.poll();

			for (int v = 0; v < V; v++) {

				if (!visited[v] && rGraph[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		return visited[t];
	}

	public static int[][] fordFulkerson(int graph[][], int s, int t) {
		int u, v;

		int rGraph[][] = new int[V][V];
		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++) {
				rGraph[u][v] = graph[u][v];
			}

		int parent[] = new int[V];

		int max_flow = 0;

		while (bfs(rGraph, s, t, parent)) {
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow = Math.min(path_flow, rGraph[u][v]);
			}

			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}

			max_flow += path_flow;
		}
		flujo = max_flow;
		return rGraph;
	}
}
