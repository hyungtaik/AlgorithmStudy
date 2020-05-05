package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1520 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] dp;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(solve(0,0));
	}

	static int solve(int x, int y) {
		if (x == M - 1 && y == N - 1) {
			return 1;
		}
		if (dp[x][y] != -1)
			return dp[x][y];
		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx > M - 1 || ny < 0 || ny > N - 1)
				continue;
			if (map[nx][ny] < map[x][y]) {
				dp[x][y]+=solve(nx,ny);
			}
		}
		return dp[x][y];
	}

}
