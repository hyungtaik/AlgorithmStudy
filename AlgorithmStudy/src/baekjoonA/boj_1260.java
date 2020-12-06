package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DFS, BFS
 * 
 * @see 백준 1260번: DFS와 BFS <br>
 *      메모리: 18860 KB <br>
 *      시간: 300 ms
 * @since 2020-12-06
 * 
 */

public class boj_1260 {

	private static int N;
	private static int M;
	private static int V;
	private static boolean[][] map;
	private static boolean[] visited;
	private static LinkedList<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		map = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());

			map[n1][n2] = true;
			map[n2][n1] = true;
		}
		dfs(V);
		System.out.println();
		bfs();
	}

	static void dfs(int index) {
		visited[index] = true;
		System.out.print(index + " ");

		for (int i = 1; i <= N; i++) {
			if (visited[i] || !map[index][i])
				continue;
			dfs(i);
		}
	}

	static void bfs() {
		visited = new boolean[N + 1];
		q = new LinkedList<Integer>();
		q.add(V);
		visited[V] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			for (int i = 1; i <= N; i++) {
				if (visited[i] || !map[temp][i])
					continue;
				visited[i] = true;
				q.add(i);
			}
		}
	}

}
