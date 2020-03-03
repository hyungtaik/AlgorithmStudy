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
	private static int[] princess;
	private static int result;
	private static boolean[][] check;
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
//		person = new boolean[25];
//		visited = new boolean[5][5];

		for (int i = 0; i < 25; i++) {
			person = new boolean[25];
			visited = new boolean[5][5];
			combi(i, 1, 0);
		}

//		if(map[0][0] == 0) {
//			combi(0,0,0);
//		}else {
//			combi(0,0,1);
//		}
		System.out.println(result);
	}

	private static void combi(int index, int count, int s) {
		if (map[index / 5][index % 5] == 1) {
			s++;
		}
		
		person[index] = true;
		visited[index / 5][index % 5] = true;

		if (count == 7) {
			if (s >= 4) {
				for (int i = 0; i < 25; i++) {
					if (person[i]) {
						boolean[][]	chk = new boolean[5][5];
						chk[i / 5][i % 5] = true;
						cnt=1;
						dfs(i/5,i%5,chk);
						break;
					}
				}
			}
		}else {
			for (int i = index + 1; i < 25; i++) {
				if (!person[i]) {
					combi(i, count + 1, s);
				}
			}
		}
		person[index] = false;
		visited[index / 5][index % 5] = false;
	}

	private static void dfs(int x, int y,boolean[][] chk) {
		if (cnt == 7) {
//			System.out.println(Arrays.toString(princess));
			result++;
		}else {
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > 4 || ny < 0 || ny > 4)
					continue;
				if(visited[nx][ny] && !chk[nx][ny]) {
					chk[nx][ny] = true;
					cnt++;
					dfs(nx,ny,chk);
				}
			}
		}
	}
}
