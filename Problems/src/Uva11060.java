import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2001
 * 
 * @author julian
 *
 */
public class Uva11060 {

    static int N;
    static int E;
    static String[] nodes;
    static HashMap<String, Integer> nodesMap;
    static boolean[][] graph;
    static int[] parents;
    static LinkedList<Integer> sort;

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int caseNumber = 0;
        StringTokenizer tok;
        StringBuilder sb;
        String s = in.readLine();

        while (s != null && !s.trim().equals("")) {

            caseNumber++;

            N = Integer.parseInt(s);

            nodes = new String[N];
            nodesMap = new HashMap<>(N);
            graph = new boolean[N][N];
            parents = new int[N];
            sort = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                nodes[i] = in.readLine();
                nodesMap.put(nodes[i], i);
            }

            E = Integer.parseInt(in.readLine());
            for (int i = 0; i < E; i++) {
                tok = new StringTokenizer(in.readLine());
                int u = nodesMap.get(tok.nextToken());
                int v = nodesMap.get(tok.nextToken());

                if (u == v || graph[u][v])
                    continue;

                graph[u][v] = true;
                parents[v]++;
            }

            topologicalSort();

            sb = new StringBuilder(String.format("Case #%d: Dilbert should drink beverages in this order: ", caseNumber));
            for (int node: sort) {
                sb.append(nodes[node]).append(" ");
            }

            out.println(sb.toString().trim() + ".");
            out.println();

            in.readLine();
            s = in.readLine();
        }

        in.close();
        out.close();

    }


    public static void topologicalSort() {

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int u = 0; u < N; u++) {
            if (parents[u] == 0)
                q.add(u);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            sort.add(u);
            for (int v = 0; v < N; v++) {
                if (graph[u][v]) {
                    graph[u][v] = false;
                    parents[v]--;
                    if (parents[v] == 0)
                        q.add(v);
                }
            }

        }

    }

}