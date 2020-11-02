package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 다익스트라
 * 
 * @see 백준 1719번: 택배 <br>
 *      메모리: 28396 KB <br>
 *      시간: 852 ms
 * @since 2020-11-02
 * 
 */

public class boj_1719 {

	private static int n;
	private static int m;
	private static int[][] map;
	private static PriorityQueue<Integer> pq;
	private static int[] dp;
	private static int[] path;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			map[a][b] = cost;
			map[b][a] = cost;
		}
		sb = new StringBuilder();
		dijkstra();
		System.out.println(sb.toString());
	}

	static void dijkstra() {
		for (int i = 1; i <= n; i++) {
			pq = new PriorityQueue<Integer>();
			pq.offer(i);
			dp = new int[n + 1];
			path = new int[n + 1];

			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[i] = 0;
			path[i] = i;
			
			while(!pq.isEmpty()) {
				int cur = pq.poll();
				for(int j=1;j<=n;j++) {
					if(map[cur][j] == 0) continue;
					if(dp[j] > map[cur][j] + dp[cur]) {
						dp[j] = map[cur][j] + dp[cur];
						path[j] = cur;
						pq.offer(j);
					}
				}
			}
			
			for(int j=1;j<=n;j++) {
				if(i == j) {
					sb.append("- ");
					continue;
				}
				int node = j;
				int index = path[j];
				while(index != i) {
					node = index;
					index = path[index];
				}
				sb.append(node+" ");
			}
			sb.append("\n");
		}

	}

	static class Pair {
		int to;
		int cost;

		public Pair(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

	}

}
