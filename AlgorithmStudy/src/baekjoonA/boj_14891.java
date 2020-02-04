package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14891 {
	static int[][] map; // ��Ϲ���
	static int K; // ȸ��Ƚ��
	static int[][] ndir; // num,dir ��Ϲ��� ��ȣ, ���� ��� �迭
	static int sum;
	static boolean[] checked; // �湮 üũ

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[4][8];
		checked = new boolean[4];
		sum = 0;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken(); // ��Ϲ���
			for (int j = 0; j < 8; j++) {
				map[i][j] = temp.charAt(j) - 48;
			}
		}
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		ndir = new int[2][K]; // K���� ����� ����, ���� ����

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			ndir[0][i] = Integer.parseInt(st.nextToken());
			ndir[1][i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < K; i++) {
			checked = new boolean[4];
			solve(ndir[0][i] - 1, ndir[1][i]);
		}
		
		sum = map[0][0] + map[1][0] * 2 + map[2][0] * 4 + map[3][0] * 8;
		System.out.println(sum);
	}

	static void solve(int num, int dir) { // ȸ����Ų ��Ϲ��� ��ȣ, ����
		checked[num] = true; // �湮�ϸ� üũ

		int next = num - 1;
		if (next >= 0 && checked[next] == false) {
			if (map[num][6] != map[next][2]) {
				solve(next, dir * -1);
			}
		}
		next = num + 1;
		if (next < 4 && checked[next] == false) {
			if (map[num][2] != map[next][6]) {
				solve(next, dir * -1);
			}
		}
		rotate(num, dir);
	}

	static void rotate(int num, int dir) {
		if (dir == 1) { // �ð����
			int temp = map[num][7];
			for (int i = 7; i > 0; i--) {
				map[num][i] = map[num][i-1];
			}
			map[num][0] = temp;
		} else {
			int temp = map[num][0];
			for (int i = 0; i < 7; i++) {
				map[num][i] = map[num][i + 1];
			}
			map[num][7] = temp;
		}
	}

}
