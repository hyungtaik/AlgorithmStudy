package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17825 {
	static horse[] horses = new horse[4];
	static int[][] map = { { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40 },
			{ 10, 13, 16, 19 }, { 30, 28, 27, 26 }, { 20, 22, 24, 25, 30, 35, 40 } }; // 25부터 겹칠수 있으므로 한곳에만 보관
	static int[] command = new int[10];

	static int max;
	static int[] moves = new int[10];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < 10; i++) command[i] = Integer.parseInt(st.nextToken());

		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < 4; i++) {
			horses[i] = new horse(i);
		}

		dfs(0);
		
		System.out.println(max);
	}

	// 순열 만들어서 체크
	static void dfs(int index) {
		if (index == 10) {
			for (int i = 0; i < 4; i++) {
				horses[i].x = 0;
				horses[i].y = 0;
			}
			int score = 0;
			int tempX = 0;
			int tempY = 0;
			for (int i = 0; i < 10; i++) {
				boolean check = solve(i, command[i]);
				if (check) {
					tempX = horses[moves[i]].x;
					tempY = horses[moves[i]].y;
					if (tempX == 3 && tempY == 7)
						continue;
					else
						score += map[tempX][tempY];
				} else
					return;
			}
			if (max < score) {
				max = score;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			moves[index] = i;
			dfs(index + 1);
		}
	}

	static boolean solve(int idx, int m) {
		int x = horses[moves[idx]].x;
		int y = horses[moves[idx]].y;
		int num = horses[moves[idx]].num;
		int ny = y + m; // 열
		int nx = x; // 행
		if (x == 0) {
			if (ny == 5) {
				nx = 1;
				ny = 0;
			} else if (ny == 10) {
				nx = 3;
				ny = 0;
			} else if (ny == 15) {
				nx = 2;
				ny = 0;
			} else if (ny == 20) {
				nx = 3;
				ny = 6;
			} else if (ny > 20) {
				nx = 3;
				ny = 7; // 맨 끝 (3,7)
			}
		} else if (x == 1 || x == 2) {
			if (ny > 3) { // 마지막 행으로 이동
				nx = 3;
				ny--;
				if (ny > 6)	ny = 7;
			}
		} else if (x == 3) {
			if (ny > 6) ny = 7;
		}

		if (nx == 3 && ny == 7) {
			horses[moves[idx]].x = nx;
			horses[moves[idx]].y = ny;
			return true;
		}

		for (int i = 0; i < 4; i++) {
			if (num == i)
				continue;
			if (horses[i].x == 0 && horses[i].y == 0)
				continue;
			if (horses[i].x == 3 && horses[i].y == 7)
				continue;

			if (horses[i].x == nx && horses[i].y == ny) {
				return false;
			}
		}
		horses[moves[idx]].x = nx;
		horses[moves[idx]].y = ny;
		return true;
	}

	static class horse {
		int x;
		int y;
		int num;

		public horse(int num) {
			this.num = num;
			this.x = 0;
			this.y = 0;
		}

	}
}