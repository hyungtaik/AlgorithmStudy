package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1941 {

	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[] person;
	private static boolean[][] visited;
	private static int result;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[5][5]; // 1: S 0:Y
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) {
				char temp = input.charAt(j);
				if (temp == 'S') {
					map[i][j] = 1;
				}
			}
		}
		result = 0;

		for (int i = 0; i < 25; i++) {
			person = new boolean[25];
			visited = new boolean[5][5];
			person[i] = true;
			visited[i / 5][i % 5] = true;
			if (map[i/5][i%5] == 0) {
				combi(i, 1, 0);
			} else {
				combi(i, 1, 1);
			}
		}
		System.out.println(result);
	}

	private static void combi(int index, int count, int s) {
		if (count == 7) {
			if (s >= 4) {
				for (int i = 0; i < 25; i++) {
					if (person[i]) {
						boolean[][] check = new boolean[5][5];
						check[i / 5][i % 5] = true;
						cnt = 1;
						dfs(i / 5, i % 5, check);
						break;
					}
				}
			}
			return;
		} else {
			for (int i = index+1; i < 25; i++) {
				if(!person[i]) {
					person[i] = true;
					visited[i / 5][i % 5] = true;
					if (map[i / 5][i % 5] == 1) {
						combi(i, count + 1, s + 1);
					} else {
						combi(i, count + 1, s);
					}
					person[i] = false;
					visited[i / 5][i % 5] = false;
				}
			}

		}
	}

	private static void dfs(int x, int y, boolean[][] check) {
		if (cnt == 7) {
			result++;
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > 4 || ny < 0 || ny > 4)
				continue;
			if (visited[nx][ny] && !check[nx][ny]) {
				check[nx][ny] = true;
				cnt++;
				dfs(nx, ny, check);
			}
		}
	}
}
