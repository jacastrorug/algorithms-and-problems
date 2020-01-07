package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class TopologicalSort {

	public static boolean[] visited;
	public static ArrayList<Integer>[] adjList; // graph
	public static LinkedList<Integer> list; // list of tasks in order to be executed

	public static void topologicalSort(int u) {

		visited[u] = true;

		for (int v : adjList[u]) {
			if (!visited[v])
				topologicalSort(v);
		}

		list.addFirst(u);
	}

}
