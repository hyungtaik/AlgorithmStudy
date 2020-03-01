package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2146 {
	private static int N;
	private static LinkedList<Pair> q;
	private static int[][] map;
	private static boolean[][] check;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] tMap;
	private static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		q = new LinkedList<Pair>();
		map = new int[N][N];
		tMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int index = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
//					check = new boolean[N][N];
					Pair p = new Pair(i, j, 0);
					map[i][j] = index;
					bfs(p, index++);
				}
			}
		}
		min = Integer.MAX_VALUE;
		q = new LinkedList<Pair>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 1) {
					check = new boolean[N][N];
					check[i][j] = true;
					bfs2(new Pair(i, j, 0), map[i][j]);
				}
			}
		}
		System.out.println(min);
	}

	static void bfs(Pair p, int index) {
		q.add(p);
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
					continue;
				if (map[nx][ny]==index)
					continue;
				if (map[nx][ny] == 0)
					continue;
				map[nx][ny] = index;
				q.add(new Pair(nx, ny, 0));
			}
		}
	}

	static void bfs2(Pair p, int index) {
		q.add(p);
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int count = temp.count;
			if (count > min)
				continue;
			if (map[x][y] != index && map[x][y] > 1) {
				min = Math.min(min, count-1);
				q.clear();
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
					continue;
				if (check[nx][ny])
					continue;
				if (map[nx][ny] == index)
					continue;
				check[nx][ny] = true;
				q.add(new Pair(nx, ny, count + 1));

			}
		}
	}

	static class Pair {
		int x, y, count;

		public Pair(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

	}

}