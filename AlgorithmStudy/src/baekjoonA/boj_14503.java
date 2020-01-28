package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14503 {
	static int N; // map ����
	static int M; // map ����

	static int r, c, d; // �κ��� y��ǥ, x��ǥ,����
	static int[][] map;
	static int[][] checked;

	static int[] dx = { -1, 0, 1, 0 }; // 0 1 2 3 ���� => �� �� �� ��
	static int[] dy = { 0, 1, 0, -1 };

	static int num;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()); // y��ǥ �״��
		c = Integer.parseInt(st.nextToken()); // x��ǥ �״��
		d = Integer.parseInt(st.nextToken());
		num = 0;

		map = new int[N][M];
		checked = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				checked[i][j] = map[i][j]; // 1: �� / 2: û���� �� / 0: ��ĭ,û���� ��
			}
		}
		solve(r, c, d, 0);
		System.out.println(num);
	}

	static void solve(int x, int y, int dir, int cnt) {
		int nx;
		int ny;
		if (cnt == 4) {
			int tdir = dir - 2; // ���� - ������������ ���ο� ����
			if (tdir == -1)
				tdir = 3;
			else if (tdir == -2)
				tdir = 2;

			nx = x + dx[tdir];
			ny = y + dy[tdir];

			if (checked[nx][ny] != 1) {
				solve(nx, ny, dir, 0);
			} else {
				return;
			}
		} else {
			if (checked[x][y] == 0) {
//				System.out.println(x+"    "+y+" "+dir+"*******");
				num++;
				checked[x][y] = 2; // û���� ���� 2
			}
			dir = dir - 1;
			if (dir < 0) {
				dir = 3;
			}
			nx = x + dx[dir];
			ny = y + dy[dir];

			if (checked[nx][ny] == 0) {
				solve(nx, ny, dir, 0);
			} else {
				solve(x, y, dir, cnt + 1);
			}
		}

	}

}
