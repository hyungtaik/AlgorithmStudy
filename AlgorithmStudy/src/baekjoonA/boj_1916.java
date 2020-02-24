package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1916 {

	private static int[][] map;
	private static int M;
	private static int N;
	private static int endP;
	private static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			if (map[start][end] > pay) {
				map[start][end] = pay;
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int startP = Integer.parseInt(st.nextToken());
		endP = Integer.parseInt(st.nextToken());
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startP] = 0;

		dijkstra(startP);
		System.out.println(dist[endP]);
	}

	static void dijkstra(int start) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(start);
		while (!q.isEmpty()) {
			int index = q.poll();
			for (int i = 1; i <= N; i++) {
				if (map[index][i] !=Integer.MAX_VALUE ) {
					if (dist[i] > dist[index] + map[index][i]) {
						dist[i] = dist[index] + map[index][i];
						q.add(i);
					}
				}
			}
		}
	}

}