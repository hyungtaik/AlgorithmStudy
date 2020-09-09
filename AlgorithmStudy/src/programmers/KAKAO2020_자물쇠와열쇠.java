package programmers;

/**
 * 
 * @author TAEK
 * @category
 * 
 * @see 프로그래머스 : KAKAO BLIND RECRUITMENT 자물쇠와 열쇠 <br>
 * 
 * @since 2020-09-09
 * 
 */

public class KAKAO2020_자물쇠와열쇠 {

	public static void main(String[] args) {
		Solution sol = new Solution();

		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		sol.solution(key, lock);
	}

	static class Solution {

		private int len;
		private int[][] realLock;
		static boolean answer = false;

		// 열쇠 돌기와 자물쇠 돌기가 만나면 안된다.
		// 열 수 있으면 true, 열 수 없으면 false
		public boolean solution(int[][] key, int[][] lock) {
			len = lock.length;
			realLock = new int[len * 3][len * 3];

			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					realLock[i + len][j + len] = lock[i][j];
				}
			}
			// 상,하,좌,우로 칸을 배로 늘려서 회전시키고 체크해보기
			dfs(key, realLock, 0);

			return answer;
		}

		public void dfs(int[][] key, int[][] lock, int cnt) {
			checkMap(key, lock, 0, 0);
			if (answer)
				return;
			if (cnt == 4)
				return;

			// 회전시키고 재귀
			int[][] temp = rotate(key);

			dfs(temp, lock, cnt + 1);
		}

		// 회전한 것을 차례대로 체크
		public void checkMap(int[][] key, int[][] lock, int x, int y) {
			if (answer)
				return;
			if (y + key.length > lock.length) {
				y = 0;
				x++;
			}
			if (x + key.length > lock.length)
				return;

			int[][] tempLock = new int[lock.length][lock.length];
			for (int i = 0; i < lock.length; i++) {
				tempLock[i] = lock[i].clone();
			}

			boolean check = false;
			L: for (int i = 0; i < key.length; i++) {
				for (int j = 0; j < key.length; j++) {
					if (key[i][j] == 1) {
						if (tempLock[i + x][j + y] == 1) {
							check = true;
							break L;
						}
						tempLock[i + x][j + y] = key[i][j];
					}
				}
			}
			if (!check) {
				L: for (int i = 0; i < lock.length / 3; i++) {
					for (int j = 0; j < lock.length / 3; j++) {
						if (tempLock[i + lock.length / 3][j + lock.length / 3] != 1) {
							check = true;
							break L;
						}
					}
				}
			}
			if (!check) {
				answer = true;
			}
			checkMap(key, lock, x, y + 1);
		}

		// 90도 key 회전
		int[][] rotate(int[][] key) {
			int len = key.length;
			int[][] result = new int[len][len];

			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					result[i][j] = key[len - j - 1][i];
				}
			}
			return result;
		}
	}
}
