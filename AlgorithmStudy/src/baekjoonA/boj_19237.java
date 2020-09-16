package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 19237번: 어른 상어 <br>
 *      메모리: 17516 KB <br>
 *      시간: 144 ms
 * @since 2020-09-16
 * 
 */

public class boj_19237 {

	// 위 아래 왼 오
	private static int[] dx = { 0, -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, 0, -1, 1 };
	private static int N, M, K;
	private static Pair[] shark;
	private static Trace[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 냄새 지속시간

		map = new Trace[N][N];
		shark = new Pair[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = new Trace(Integer.parseInt(st.nextToken()), -1);
				if (map[i][j].num > 0) {
					shark[map[i][j].num] = new Pair(i, j);
					map[i][j].time = K;
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			int dir = Integer.parseInt(st.nextToken());
			shark[i].dir = dir;
		}

		// 위 아래 왼 오
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					shark[i].priority[j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		int count = 0;
		while (count < 1001) {

			// 냄새 지우기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j].time--;
				}
			}

			int sharkcount = 0;
			L: for (int i = M; i > 0; i--) {
				if (!shark[i].live)
					continue;
				if (map[shark[i].x][shark[i].y].num != i) {
					shark[i].live = false;
					continue;
				}
				sharkcount++;
				int nx = 0, ny = 0;

				int sharkdir = shark[i].dir;
				for (int j = 1; j <= 4; j++) {
					int tmp = shark[i].priority[sharkdir][j];
					nx = shark[i].x + dx[tmp];
					ny = shark[i].y + dy[tmp];
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
						continue;

					if (map[nx][ny].time < 0) { // 이동할 수 있으니 이동하고 전체 continue
						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].dir = tmp;
						continue L;
					}
				}

				// 갈 수 없으니 다른 방법
				sharkdir = shark[i].dir;
				for (int j = 1; j <= 4; j++) {
					int tmp = shark[i].priority[sharkdir][j];
					nx = shark[i].x + dx[tmp];
					ny = shark[i].y + dy[tmp];
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
						continue;

					if (map[nx][ny].num == i && map[nx][ny].time >= 0) {

						shark[i].x = nx;
						shark[i].y = ny;
						shark[i].dir = tmp;
						break;
					}
				}
			}

			// 상어 이동
			for (int i = M; i > 0; i--) {
				if (!shark[i].live)
					continue;
				map[shark[i].x][shark[i].y].time = K;
				map[shark[i].x][shark[i].y].num = i;
			}

			if (sharkcount == 1)
				break;
			count++;

		}
		if (count >= 1001) {
			System.out.println(-1);
		} else {
			System.out.println(count);
		}
	}

	static class Trace {
		int num;
		int time;

		public Trace(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
	}

	static class Pair {
		int x, y;
		int dir;
		int[][] priority;
		boolean live;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.dir = 0;
			this.priority = new int[5][5];
			this.live = true;
		}
	}

}
