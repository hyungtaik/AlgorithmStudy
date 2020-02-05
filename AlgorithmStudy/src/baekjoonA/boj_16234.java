package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16234 {
	static int N, L, R; // 맵크기, 최소, 최대 범위
	static int[][] map; // 맵
	static int[][] copy; // copy 맵
	static boolean[][] checked;
	static boolean[][] newChecked;
	static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };
	static int count; // 이동한 횟수
	static int sum;
	static int result;
	static int change;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		checked = new boolean[N][N];
		newChecked = new boolean[N][N];
		count = 0;
		sum = 0;
		result = 0;
		change = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			int temp = change;
			checked = new boolean[N][N];
			newChecked = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (newChecked[i][j] == true) {
						continue;
					}
					sum = 0;
					count = 0;
					solve(i, j);
					if (count <= 1) { // 하나만 방문했을때 맵리셋 필요 x
						checked[i][j] = newChecked[i][j];
					} else {
						resetMap();
					}
				}
			}
			
			result++;
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j]+" ");
//				}System.out.println();
//			}
			if(temp == change) break;
		}

		System.out.println(result - 1);
	}

	// dfs해서 true로 만듬 - true로 바뀌는 경우가 없을때 끝!
	static void solve(int x, int y) {
		newChecked[x][y] = true;
		sum += map[x][y];
		count++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
				continue;
			if (newChecked[nx][ny] == true)
				continue;
			int temp = Math.abs(map[x][y] - map[nx][ny]);
			if (temp >= L && temp <= R) {
				solve(nx, ny);
			}

		}

	}

	// true인걸 골라서 sum 구하고 다시 넣어주기
	static void resetMap() {
		int avg = sum / count;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (checked[i][j] != newChecked[i][j]) {
					map[i][j] = avg;
					change++;
				}
				checked[i][j] = newChecked[i][j];
			}
		}
	}

}
