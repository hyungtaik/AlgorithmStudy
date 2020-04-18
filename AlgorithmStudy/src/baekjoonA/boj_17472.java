package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_17472 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int[] parents;
	private static PriorityQueue<Bridge> pq;
	private static LinkedList<Node> q;
	private static int sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int color = 1;
		visited = new boolean[N][M];
		q = new LinkedList<Node>();
		pq = new PriorityQueue<Bridge>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					map[i][j] = color;
					visited[i][j] = true;
					dfs(i, j, color);
					color++;
				}
			}
		}

		color--;
		parents = new int[color + 1];
		Arrays.fill(parents, -1);
		sum = 0;
		makeBridge();
		while (!pq.isEmpty()) {
			Bridge temp = pq.poll();
			if (union(temp.startColor, temp.nowColor)) {
				sum += temp.len;
			}
		}
		if (unionCheck()) {
			System.out.println(sum);
		} else {
			System.out.println(-1);
		}
//		print();
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	static int findSet(int a) {
		if (parents[a] == -1)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	static boolean unionCheck() {
		int check = 0;
		for (int i = 1; i < parents.length; i++) {
			if (parents[i] < 0)
				check++;
		}
		if (check == 1)
			return true;
		else
			return false;
	}

	static void makeBridge() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				goCheck(temp.x, temp.y, d, temp.color);
			}
		}
	}

	static void goCheck(int x, int y, int dir, int color) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		int len = 0;
		while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != color) {
			if (map[nx][ny] != 0) {
				if (len > 1) {
					pq.add(new Bridge(color, map[nx][ny], len));
					return;
				} else {
					return;
				}
			}
			nx += dx[dir];
			ny += dy[dir];
			len++;
		}

	}

	static void dfs(int x, int y, int color) {
		q.add(new Node(x, y, color));
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1)
				continue;
			if (visited[nx][ny])
				continue;
			if (map[nx][ny] == 1) {
				visited[nx][ny] = true;
				map[nx][ny] = color;
				dfs(nx, ny, color);
			}
		}
	}

	static class Node {
		int x, y, color;

		public Node(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	static class Bridge implements Comparable<Bridge> {
		int startColor, nowColor, len;

		public Bridge(int startColor, int nowColor, int len) {
			super();
			this.startColor = startColor;
			this.nowColor = nowColor;
			this.len = len;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.len - o.len;
		}
	}
}
