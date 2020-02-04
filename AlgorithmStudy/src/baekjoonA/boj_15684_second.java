package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15684_second {
	static int N, M, H; // 세로, 가로, 놓을 수 있는 가로선
	static int[][] map; // 사다리 map
	static int max;
	static boolean check; // 제대로 자기 인덱스로 내려갔을 경우 true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H + 2][N + 1]; // 1, 1 시작
		check = false;
		for (int i = 0; i < M; i++) { // 가로 정보
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1; // 왼쪽
		}
		for (int i = 0; i < 4; i++) { // 3번까지 그릴 수 있음
			max = i;
			solve(1, 1, 0); // count
			if (check) break; // 제대로 들어갔을 경우 탈출
		}
		if(!check) max = -1;
		System.out.println(max);
	}

	static void solve(int x, int y, int count) {
		if (check) return; // 이미 완료되었다면 리턴
		if (max == count) {
			if (checkMap()) {
				check = true;
			}
			return;
		}
		for (int i = x; i < H + 1; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 1 || map[i][j + 1] == 1 || map[i][j - 1] == 1)
					continue;
				map[i][j] = 1;
				solve(i, j, count + 1);
				map[i][j] = 0;
			}
		}
	}

	// 자기 인덱스로 내려가는지 확인하는 함수
		static boolean checkMap() {
			for (int j = 1; j <= N; j++) {
				int temp = j; // 처음 시작 위치
				int i= 1;
				while(i<=H+1) {
					if (map[i][temp] == 1) { // 오른쪽으로 이동
						temp++;
					} else if (map[i][temp - 1] == 1) { // 좌로 이동
						temp--;
					}
					i++; // 아래로 이동
				}
				if (j != temp) {
					return false;
				}
			}
			return true;
		}

}
