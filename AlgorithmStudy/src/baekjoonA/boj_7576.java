package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_7576 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] checked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		map = new int[N][M];
		checked = new boolean[N][M];
		int count = 0;
		PriorityQueue<tomato> list = new PriorityQueue<tomato>();

		for (int i = 0; i < N; i++) { // -1: 토마토 없음 / 1: 익은 토마토 / 0: 익지않은 토마토
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					list.add(new tomato(i, j, 0));
				}
			}
		}

		while (!list.isEmpty()) {
			tomato item = list.poll();
			int x = item.x;
			int y = item.y;
			checked[x][y] = true;
			int temp = item.num;
			count = Math.max(count, temp);
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1)
					continue;
				if (checked[nx][ny] == true)
					continue;
				if (map[nx][ny] == 0) {
					map[nx][ny] = 1;
					list.offer(new tomato(nx, ny, temp + 1));
				}
			}
		}

		for (int i = 0; i < N; i++) { // -1: 토마토 없음 / 1: 익은 토마토 / 0: 익지않은 토마토
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					count = -1;
				}
			}
		}
		System.out.println(count);
	}

	static class tomato implements Comparable<tomato> {
		int x;
		int y;
		int num;

		@Override
		public int compareTo(tomato o) {
			// TODO Auto-generated method stub
			return this.num - o.num;
		}

		public tomato(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

	}
}