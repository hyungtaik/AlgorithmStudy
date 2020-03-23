package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17779 {

	private static int N;
	private static int[][] map;
	private static int[] dx = { 1, 1, -1, -1 };
	private static int[] dy = { -1, 1, 1, -1 };
	private static Pair[] arr;
	private static int min;
	private static int[][] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		arr = new Pair[4];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N - 2; i++) {
			for (int j = 1; j < N - 1; j++) {
				arr[0] = new Pair(i, j);
				dfs(i, j, 0, 0, 0);
			}
		}

		System.out.println(min);
	}

	static void dfs(int x, int y, int left, int right, int dir) {
		if (dir == 2) {
			int tx = x + dx[2] * left;
			int ty = y + dy[2] * left;

			if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
				return;

			arr[3] = new Pair(tx, ty);
			count(arr[0].x, arr[0].y, left, right);
			return;
		}

		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx >= 0 && nx <= N - 1 && ny >= 0 && ny <= N - 1) {
			if (dir == 0) {
				dfs(nx, ny, left + 1, right, dir);
			} else if (dir == 1) {
				dfs(nx, ny, left, right + 1, dir);
			}
		}

		if (left > 0) {
			if (dir == 0) {
				arr[dir + 1] = new Pair(x, y);
				dfs(x, y, left, right, dir + 1);
			} else if (dir == 1 && right > 0) {
				arr[dir + 1] = new Pair(x, y);
				dfs(x, y, left, right, dir + 1);
			}
		}
	}

	public static void count(int x, int y, int d1, int d2) {
		num = new int[N][N];
		int[] people = new int[5]; // 인구수 배열

		// 5 구역
		int tx = x, ty = y;
		int td1 = d1, td2 = d2; // 왼쪽 길이, 오른쪽 길이
		
		// 테두리부터 안쪽으로 5를 채워 넣는다.
		while (td1 >= 0 && td2 >= 0) {
			for (int i = 0; i <= td1; i++) { // 왼쪽 아래 
				num[tx + i][ty - i] = 5;
				num[tx + td2 + i][ty + td2 - i] = 5;
			}
			for (int i = 0; i <= td2; i++) { // 오른쪽 아래
				num[tx + i][ty + i] = 5;
				num[tx + td1 + i][ty - td1 + i] = 5;
			}
			tx++;  // 안쪽으로 들어가기 위함
			td1--; // 들어가면서 길이를 1씩 줄인다.
			td2--;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (num[i][j] == 5)
					people[4] += map[i][j];
			}
		}

		// 1 구역
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (num[i][j] != 5)
					people[0] += map[i][j];
			}
		}

		// 2 구역
		for (int i = 0; i <= x + d2; i++) {
			for (int j = y + 1; j < N; j++) {
				if (num[i][j] != 5)
					people[1] += map[i][j];
			}
		}

		// 3 구역
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (num[i][j] != 5)
					people[2] += map[i][j];
			}
		}

		// 4 구역
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = y - d1 + d2 ; j < N; j++) {
				if (num[i][j] != 5)
					people[3] += map[i][j];
			}
		}

		Arrays.sort(people);
		int result = people[4] - people[0];
		if (min > result)
			min = result;
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
