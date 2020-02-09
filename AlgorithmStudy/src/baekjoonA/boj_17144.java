package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17144 {

	private static int R;
	private static int C;
	private static int T;
	private static int[][] map;
	private static int[][] tmap;
	private static int air;
	private static int[] dx = { -1, 1, 0, 0 }; // �� �� �� ��
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken()); // ��
		C = Integer.parseInt(st.nextToken()); // ��
		T = Integer.parseInt(st.nextToken()); // �ð�

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) { // ����û����
					air = i; // ����û���Ⱑ �ִ� ������ ��
					map[i][j] = 0;
				}
			}
		}

		// T�ʸ�ŭ ������.
		for (int i = 0; i < T; i++) {
			tmap = new int[R][C];
			solve();
		}
		int sum = 0;
//			System.out.println();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	static void solve() {
		// 1. Ȯ��
		// Ar,c - (Ar,c/5)��(Ȯ��� ������ ����)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0)
					continue;
				int temp = map[i][j] / 5;
				int count = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1)
						continue;
					if ((nx == air - 1 || nx == air) && ny == 0)
						continue;
					tmap[nx][ny] += temp;
					count++;
				}
				tmap[i][j] += map[i][j] - temp * count;
			}
		}
		map = tmap;

		// 2. ��ȭ
		// air-1�� ������

		for (int i = air - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		map[air - 1][0] = 0;

		for (int i = 0; i < C - 1; i++) { // ����
			map[0][i] = map[0][i + 1];
		}

		for (int i = 0; i < air - 1; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = C - 1; i > 0; i--) { // �߰� �Ʒ�
			map[air - 1][i] = map[air - 1][i - 1];
		}

		for (int i = air; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		map[air][0] = 0;

		for (int i = 0; i < C - 1; i++) { // �� �Ʒ�
			map[R - 1][i] = map[R - 1][i + 1];
		}

		for (int i = R - 1; i > air; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		for (int i = C - 1; i > 0; i--) { // �߰� ��
			map[air][i] = map[air][i - 1];
		}

	}

}
