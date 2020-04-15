package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17281 {

	private static int N;
	private static int[][] map;
	private static int[] line;
	private static boolean[] visited;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[9];
		line = new int[9];
		max = Integer.MIN_VALUE;
		perm(0);
		System.out.println(max);
	}

	static void perm(int count) {
		if (count == 9) {
			int result = baseBall();
			if (result > max) {
				max = result;
			}
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			line[count] = i;
			if (count == 2) {
				perm(count + 2);
			} else {
				perm(count + 1);
			}
			visited[i] = false;
		}
	}

	static int baseBall() {
		int score = 0;
		int start = 0;
		boolean[] roo = new boolean[3];
		for (int i = 0; i < N; i++) {
			int count = 3;
			Arrays.fill(roo, false);
			while (count > 0) {
				int now = map[i][line[start]]; // 현재 선수의 타석
				if (now == 0) {
					count--;
				} else {
					if(now == 3) { // 3루타
						for(int j=0;j<3;j++) {
							if(roo[j]) {
								roo[j] = false;
								score++;
							}
						}
						roo[2] = true;
					}else if(now == 2) { // 2루타
						for(int j=2;j>=0;j--) {
							if(roo[j]) {
								roo[j] = false;
								if(j==0) {
									roo[j+2] = true;
								}else {
									score++;
								}
							}
						}
						roo[1] = true;
					}else if(now == 1){ // 1루타
						for(int j=2;j>=0;j--) {
							if(roo[j]) {
								roo[j] = false;
								if(j==2) {
									score++;
								}else {
									roo[j+1] = true;
								}
							}
						}
						roo[0] = true;
					}else { // 홈런
						for(int j=0;j<3;j++) {
							if(roo[j]) {
								roo[j] = false;
								score++;
							}
						}
						score++;
					}
				}
				if (start == 8) {
					start = 0;
				} else {
					start++;
				}
			}
			
		}
		return score;
	}

}
