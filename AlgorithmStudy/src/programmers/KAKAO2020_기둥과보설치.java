package programmers;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 프로그래머스 : KAKAO BLIND RECRUITMENT 기둥과 보 설치 <br>
 * 
 * @since 2020-09-10
 * 
 */

public class KAKAO2020_기둥과보설치 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 5;
		int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
				{ 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		int[][] result = s.solution(n, build_frame);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Solution {
		boolean[][] pillar, bo; // 기둥, 보

		public int[][] solution(int n, int[][] build_frame) {
			int count = 0;

			pillar = new boolean[n + 2][n + 2];
			bo = new boolean[n + 2][n + 2];

			for (int i = 0; i < build_frame.length; i++) {
				int y = build_frame[i][0] + 1; // col
				int x = build_frame[i][1] + 1; // row
				int arch = build_frame[i][2]; // 건축물
				int ok = build_frame[i][3]; // 설치 삭제 여부
				
				// ok   - 설치 : 1 | 삭제 : 0
				// arch - 기둥 : 0 | 보 : 1
				if (ok == 1) { // 설치
					if (arch == 0 ) {
						if(pillarOk(y, x)) {
							pillar[y][x] = true;
							count++;
						}
					}
					if (arch == 1 ) {
						if(boOk(y, x)) {
							bo[y][x] = true;
							count++;
						}
					}
				} else {
					if (arch == 0) {
						pillar[y][x] = false;
					} else if (arch == 1) {
						bo[y][x] = false;
					}

					if (destroy(y, x, arch, n)) {
						count--;
						continue;
					}

					if (arch == 0) {
						pillar[y][x] = true;
					} else if (arch == 1) {
						bo[y][x] = true;
					}
				}

			}

			int index = 0;
			int[][] answer = new int[count][3];

			for (int i = 1; i < n + 2; i++) {
				for (int j = 1; j < n + 2; j++) {
					if (pillar[i][j])
						answer[index++] = new int[] { i - 1, j - 1, 0 };
					if (bo[i][j])
						answer[index++] = new int[] { i - 1, j - 1, 1 };
				}
			}
			return answer;
		}

		private boolean pillarOk(int y, int x) {
			if(x == 1 || pillar[y][x - 1] || bo[y][x] || bo[y - 1][x]) return true;
			return false;
		}

		private boolean boOk(int y, int x) {
			if(pillar[y][x - 1] || pillar[y + 1][x - 1] || (bo[y - 1][x] && bo[y + 1][x])) return true;
			return false;
		}

		private boolean destroy(int y, int x, int arch, int n) {
			for (int i = 1; i < n + 2; i++) {
				for (int j = 1; j < n + 2; j++) {
					if (pillar[i][j] && !pillarOk(i, j)) {
						return false;
					}
					if (bo[i][j] && !boOk(i, j)) {
						return false;
					}
				}
			}

			return true;
		}
	}
}
