package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class TopologicalSort {

	public static boolean[] visited;
	public static ArrayList<Integer>[] adjList; // graph
	public static LinkedList<Integer> list; // list of tasks in order to be executed

	public static int N;
	public static int[] parents;
	public static boolean[][] graph;
	public static LinkedList<Integer> sort;

	public static void topologicalSort(int u) {

		visited[u] = true;

		for (int v : adjList[u]) {
			if (!visited[v])
				topologicalSort(v);
		}

		list.addFirst(u);
	}

	public static void topologicalSortMultipleDependence() {

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
