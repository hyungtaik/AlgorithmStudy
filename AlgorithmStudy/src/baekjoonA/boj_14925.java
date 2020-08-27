package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see ���� 14925��: ���� �Ǽ��ϱ� <br>
 *      �޸�: 78172 KB <br>
 *      �ð�: 428 ms
 * @since 2020-08-27
 * 
 */

public class boj_14925 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] dp;

	// 0�� ����, 1�� ���� �׸��� 2�� ��
	// ������ ���� �� �ִ� ���� ū ���簢�� ������ �� ���� ũ�� L
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
