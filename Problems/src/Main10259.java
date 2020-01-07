import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main10259 {

	public static int n;
	public static int k;
	public static int[][] movs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	public static TreeMap<String, ArrayList<String>> graph;
	public static HashMap<String, Integer> nodes;
	public static HashMap<String, int[]> mapper;
	public static Stack<String> stack;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		int casos = Integer.parseInt(in.readLine());
		StringTokenizer tok;
		while (casos-- > 0) {
			in.readLine();

			tok = new StringTokenizer(in.readLine());
			n = Integer.parseInt(tok.nextToken());
			k = Integer.parseInt(tok.nextToken());
			graph = new TreeMap<>();
			nodes = new HashMap<>();
			mapper = new HashMap<>();
			stack = new Stack<>();

			for (int i = 0; i < n; i++) {
				tok = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					String key = i + "$" + j;
					nodes.put(key, Integer.parseInt(tok.nextToken()));
					graph.put(key, new ArrayList<String>());
					mapper.put(key, new int[] { i, j });
				}
			}

			buildGraph();
			topologicalSort();

			System.out.println(graph);
			System.out.println(stack);

			out.println(nodes.get("0$0") + longestPath("0$0"));

			if (casos > 1)
				out.println();
		}
		out.close();
	}

	public static void buildGraph() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int actual = nodes.get(i + "$" + j);
				for (int[] mov : movs) {
					for (int len = 1; len <= k; len++) {
						int x = i + len * mov[0], y = j + len * mov[1];
						if (x >= 0 && x < n && y >= 0 && y < n) {
							if (nodes.get(x + "$" + y) > actual) {
								graph.get(i + "$" + j).add(x + "$" + y);
							}
						}
					}
				}
			}
		}
	}

	public static void topologicalSort() {
		boolean[][] visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					dfs(i, j, visited);
				}
			}
		}
	}

	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		String u = x + "$" + y;
		for (String v : graph.get(u)) {
			int[] pos = mapper.get(v);
			if (!visited[pos[0]][pos[1]]) {
				dfs(pos[0], pos[1], visited);
			}
		}
		stack.push(u);

	}

	public static int longestPath(String u) {
		int dist = 0;
		for (String v : graph.get(u)) {
			dist = Math.max(dist, longestPath(v) + nodes.get(v));
		}
		return dist;
	}

}
