package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1986 {

	private static int n;
	private static int m;
	private static int[][] map;
	private static int Q, K, P;
	private static LinkedList<Pair> q1, q2;
	private static boolean[][] visited;
	private static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static int[] dx2 = { -2, -2, -1, 1, 2, 2, -1, 1 };
	private static int[] dy2 = { -1, 1, -2, -2, -1, 1, 2, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n + 1][m + 1];
		visited = new boolean[n + 1][m + 1];

		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		q1 = new LinkedList<Pair>();
		for (int i = 0; i < Q; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			visited[x][y] = true;
			q1.add(new Pair(x, y));
		}
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		q2 = new LinkedList<Pair>();
		for (int i = 0; i < K; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			visited[x][y] = true;
			q2.add(new Pair(x, y));
		}
		st = new StringTokenizer(br.readLine());
		P = Integer.parseInt(st.nextToken());
		for (int i = 0; i < P; i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			visited[x][y] = true;
		}
		
		while (!q1.isEmpty()) {
			Pair temp = q1.poll();
			for (int dir = 0; dir < 8; dir++) {
				int nx = temp.x;
				int ny = temp.y;
				while (true) {
					nx += dx[dir];
					ny += dy[dir];
					if (nx < 1 || nx > n || ny < 1 || ny > m)
						break;
					if (visited[nx][ny]) {
						break;
					}
					map[nx][ny] = 1;
				}
			}
		}
		while (!q2.isEmpty()) {
			Pair temp = q2.poll();
			for (int dir = 0; dir < 8; dir++) {
				int nx = temp.x+dx2[dir];
				int ny = temp.y+dy2[dir];
				if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
				if (visited[nx][ny]) continue;
				map[nx][ny] = 1;
			}
		}
		
		
		int count = 0;
		for(int i=1;i<=n;i++) {	
			for(int j=1;j<=m;j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
