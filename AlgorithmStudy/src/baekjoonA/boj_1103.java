package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 1103번: 게임 <br>
 *      메모리: 14376 KB <br>
 *      시간: 112 ms
 * @since 2020-10-14
 * 
 */

public class boj_1103 {

	private static int N;
	private static int M;
	private static char[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int result;
	private static int[][] dp;
	private static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // row
		M = Integer.parseInt(st.nextToken()); // col
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		visited = new boolean[N][M];
		dp = new int[N][M];
		result = -1;
		flag = false;
		dfs(0, 0, 0);
		if(flag) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}

	}

	static void dfs(int x, int y, int count) {
		if(flag) return;
		dp[x][y] = count;

		for (int dir = 0; dir < 4; dir++) {
			char num = map[x][y];
			int n = num - '0';

			int nx = x + dx[dir] * n;
			int ny = y + dy[dir] * n;
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || map[nx][ny] == 'H') {
				result = Math.max(result, count + 1);
				continue;
			}
			if (visited[nx][ny]) {
				flag = true;
				return;
			}

			if(dp[nx][ny] > count) continue;
			
			visited[nx][ny] = true;
			dfs(nx, ny, count + 1);
			visited[nx][ny] = false;

		}
	}

}
