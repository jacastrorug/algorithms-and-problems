import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main10305 {

	public static boolean[] visited;
	public static ArrayList<Integer> list;
	public static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		StringTokenizer tok = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(tok.nextToken());
		int M = Integer.parseInt(tok.nextToken());

		while (N != 0) {

			visited = new boolean[N + 1];
			list = new ArrayList<>();

			adjList = new ArrayList[N + 1];

			for (int i = 1; i < adjList.length; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				tok = new StringTokenizer(in.readLine());
				int u = Integer.parseInt(tok.nextToken());
				int v = Integer.parseInt(tok.nextToken());
				adjList[u].add(v);
			}

			for (int u = 1; u <= N; u++) {
				if (!visited[u]) {
					topologicalSort(u);
				}

			}

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i) + " ");
			}

			out.println(sb.toString().trim());

			tok = new StringTokenizer(in.readLine());
			N = Integer.parseInt(tok.nextToken());
			M = Integer.parseInt(tok.nextToken());
		}

		in.close();
		out.close();

	}

	public static void topologicalSort(int u) {

		visited[u] = true;

		for (int v : adjList[u]) {
			if (!visited[v])
				topologicalSort(v);
		}

		list.add(0, u);
	}

}
