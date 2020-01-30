package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14889 {
	static int N; // 총 인원
	static int[][] map; // 능력치 map
	static int min;
	static boolean[] checked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		checked = new boolean[N];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(0, 0);
		System.out.println(min);
	}

	static void combi(int index, int count) {
		if (index == N)
			return;
		if (count == N / 2) {
			min = Math.min(min, differ());
			return;
		}
		checked[index] = true;
		combi(index + 1, count + 1);
		checked[index] = false;
		combi(index+1,count);

	}

	static int differ() {
		int sumStart = 0;
		int sumLink = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (checked[i] && checked[j]) {
					sumStart += map[i][j];
				}
				if (checked[i] == false && checked[j] == false) {
					sumLink += map[i][j];
				}

			}
		}
		return Math.abs(sumStart - sumLink);
	}
}
