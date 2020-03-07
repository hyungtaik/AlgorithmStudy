package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기_김형택{

	private static int TC;
	private static int N;
	private static int W;
	private static int H;
	private static int min;
	private static LinkedList<Pair> q;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int[][] map;
	private static int[] combi;
	private static int[][] temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			temp = new int[H][W];
			combi = new int[N];
			min = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			makeSet(0);
			System.out.println("#" + tc + " " + min);
		}
	}

	static void copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	private static void makeSet(int count) {
		if(min == 0) return;
		if (count == N) {
			copy();
			int brick = 0;

			for (int i = 0; i < N; ++i) {
				breakBrick(combi[i]);
			}

			brick = calc();
			min = Math.min(min, brick);

			return;
		}

		for (int i = 0; i < W; i++) {
			combi[count] = i;
			makeSet(count + 1);
		}
	}

	static int calc() {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (temp[i][j] > 0) {
					count++;
				}
			}
		}
		return count;
	}

	static void breakBrick(int index) {
		q = new LinkedList<Pair>();
		visited = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			if (temp[i][index] > 0) {
				q.add(new Pair(i, index, temp[i][index]));
				visited[i][index] = true;
				break;
			}
		}

		while (!q.isEmpty()) {
			Pair p = q.poll();
			temp[p.x][p.y] = 0;
			int count = p.count;
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < count; j++) {
					int nx = p.x + dx[i] * j;
					int ny = p.y + dy[i] * j;
					if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1)
						continue;
					if (visited[nx][ny])
						continue;
					visited[nx][ny] = true;
					q.add(new Pair(nx, ny, temp[nx][ny]));
				}

			}
		}
		q.clear();
		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i >= 0; i--) {
				if (temp[i][j] > 0) {
					q.add(new Pair(i, j, temp[i][j]));
				}
			}
			for (int i = H - 1; i >= 0; i--) {
				if (!q.isEmpty()) {
					temp[i][j] = q.poll().count;
				} else {
					temp[i][j] = 0;
				}
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
