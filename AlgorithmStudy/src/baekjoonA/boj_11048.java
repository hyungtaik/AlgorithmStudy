package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 11048번: 이동하기 <br>
 *      메모리: 72424 KB <br>
 *      시간: 476 ms
 * @since 2020-11-09
 * 
 */

public class boj_11048 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = { -1, -1, 0 };
	private static int[] dy = { 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int x = i;
				int y = j;
				int max = 0;
				for (int dir = 0; dir < 3; dir++) {
					x = i + dx[dir];
					y = j + dy[dir];
					if (x < 0 || y < 0)
						continue;
					max = Math.max(max, map[x][y]);
				}
				map[i][j] += max;
			}
		}
		System.out.println(map[N - 1][M - 1]);
	}

}
