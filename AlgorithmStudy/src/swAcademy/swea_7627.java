package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_7627 {

	private static int TC;
	private static int N;
	private static int[][] map;
	private static boolean[][] check;
	private static ArrayList<Point> list;

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int max;
	private static int lineMin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			check = new boolean[N][N];
			list = new ArrayList<Point>();

			max = Integer.MIN_VALUE;
			lineMin = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						check[i][j] = true;
						if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
							continue;
						}
						list.add(new Point(i, j));
					}
				}
			}
			dfs(0, 0, 0);
//			if(max ==0) lineMin = 0;
			System.out.println("#" + tc + " " + lineMin);
		}
	}

	static void dfs(int index, int count, int line) {
		if (index == list.size()) {
			if (max < count) {
				max = count;
				lineMin = line;
			}
			if (max == count) {
				if (lineMin > line) {
					lineMin = line;
				}
//				lineMin = Math.min(lineMin, line);
			}
			return;
		}
		Point temp = list.get(index);
		int x = temp.x;
		int y = temp.y;
//		System.out.println(x+"  "+y);
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			boolean chk = false;
			while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (check[nx][ny] == true) {
					chk = true;
					break;
				}
				nx += dx[i];
				ny += dy[i]; // 가던 방향으로 쭉 전진
			}
			if (chk == true)
				continue; // 갈 곳 없는 경우
			int sum = 0;
			if (i == 0) { // 상
				for (int j = x - 1; j >= 0; j--) {
					sum++;
					check[j][y] = true;
				}
			} else if (i == 1) { // 하
				for (int j = x + 1; j < N; j++) {
					sum++;
					check[j][y] = true;
				}
			} else if (i == 2) { // 좌
				for (int j = y - 1; j >= 0; j--) {
					sum++;
					check[x][j] = true;
				}
			} else { // 우
				for (int j = y + 1; j < N; j++) {
					sum++;
					check[x][j] = true;
				}
			}

			dfs(index + 1, count + 1, line + sum);

			if (i == 0) { // 상
				for (int j = x - 1; j >= 0; j--) {
					check[j][y] = false;
				}
			} else if (i == 1) { // 하
				for (int j = x + 1; j < N; j++) {
					check[j][y] = false;
				}
			} else if (i == 2) { // 좌
				for (int j = y - 1; j >= 0; j--) {
					check[x][j] = false;
				}
			} else { // 우
				for (int j = y + 1; j < N; j++) {
					check[x][j] = false;
				}
			}
		}
		dfs(index + 1, count, line);
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
