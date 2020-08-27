package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 14925번: 목장 건설하기 <br>
 *      메모리: 78172 KB <br>
 *      시간: 428 ms
 * @since 2020-08-27
 * 
 */

public class boj_14925 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] dp;

	// 0은 들판, 1은 나무 그리고 2는 돌
	// 땅에서 지을 수 있는 가장 큰 정사각형 목장의 한 변의 크기 L
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M + 1][N + 1];
		dp = new int[M + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 0;

		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == 0) {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		System.out.println(max);

	}

}
