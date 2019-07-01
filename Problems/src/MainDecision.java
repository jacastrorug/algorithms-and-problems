import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainDecision {

	public static HashMap<Character, int[][]> movs = new HashMap<>();
	public static HashMap<Character, char[][]> posibles = new HashMap<>();
	public static char[][] adj;
	public static boolean[][] vist;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		movs.put('A', new int[][] {});
		posibles.put('A', new char[][] {});

		movs.put('B', new int[][] { { 0, -1 }, { +1, 0 } });
		posibles.put('B', new char[][] { { 'D', 'E', 'F' }, { 'C', 'D', 'F' } });

		movs.put('C', new int[][] { { 0, -1 }, { -1, 0 } });
		posibles.put('C', new char[][] { { 'D', 'E', 'F' }, { 'B', 'E', 'F' } });

		movs.put('D', new int[][] { { 0, +1 }, { -1, 0 } });
		posibles.put('D', new char[][] { { 'B', 'C', 'F' }, { 'B', 'E', 'F' } });

		movs.put('E', new int[][] { { 0, +1 }, { +1, 0 } });
		posibles.put('E', new char[][] { { 'B', 'C', 'F' }, { 'C', 'D', 'F' } });

		movs.put('F', new int[][] { { +1, 0 }, { 0, +1 }, { 0, -1 }, { -1, 0 } });

		int casos = Integer.parseInt(in.readLine());
		StringTokenizer tok;
		while (casos-- > 0) {
			tok = new StringTokenizer(in.readLine().trim());

			int columns = Integer.parseInt(tok.nextToken());
			int rows = Integer.parseInt(tok.nextToken());
			adj = new char[columns][rows];
			for (int i = 0; i < columns; i++) {
				String r = in.readLine().trim();
				adj[i] = r.toCharArray();
			}

			for (int i = 0; i < adj.length; i++) {
				System.out.println(Arrays.toString(adj[i]));
			}
			System.out.println();
			
			out.println(components());
		}

		out.close();

	}

	public static int components() {
		int count = 0;
		vist = new boolean[adj.length][adj[0].length];

		for (int i = 0; i < vist.length; i++) {
			for (int j = 0; j < vist[0].length; j++) {
				if (!vist[i][j] && adj[i][j] != 'A') {
					count++;
					dfs(i, j);
				}
			}
		}

		return count;
	}

	public static void dfs(int x, int y) {
		char piece = adj[x][y];
		int[][] movimientos = movs.get(piece);
		vist[x][y] = true;
		for (int i = 0; i < movimientos.length; i++) {
			int[] mov = movimientos[i];
			int movX = x + mov[0];
			int movY = y + mov[1];

			if (movX < adj.length && movX > -1 && movY < adj[0].length && movY > -1 && !vist[movX][movY]) {
				char otherPiece = adj[movX][movY];
				if (piece != 'F') {
					char[] posib = posibles.get(piece)[i];
					for (int k = 0; k < posib.length; k++) {
						if (posib[k] == otherPiece) {
							dfs(movX, movY);
						}
					}
				} else {
					dfs(movX, movY);
				}

			}
		}

	}

}
