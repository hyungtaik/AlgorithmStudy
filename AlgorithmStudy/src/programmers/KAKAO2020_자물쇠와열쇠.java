package programmers;

/**
 * 
 * @author TAEK
 * @category
 * 
 * @see ���α׷��ӽ� : KAKAO BLIND RECRUITMENT �ڹ���� ���� <br>
 * 
 * @since 2020-09-09
 * 
 */

public class KAKAO2020_�ڹ���Ϳ��� {

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

		// ���� ����� �ڹ��� ���Ⱑ ������ �ȵȴ�.
		// �� �� ������ true, �� �� ������ false
		public boolean solution(int[][] key, int[][] lock) {
			len = lock.length;
			realLock = new int[len * 3][len * 3];

			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					realLock[i + len][j + len] = lock[i][j];
				}
			}
			// ��,��,��,��� ĭ�� ��� �÷��� ȸ����Ű�� üũ�غ���
			dfs(key, realLock, 0);

			return answer;
		}

		public void dfs(int[][] key, int[][] lock, int cnt) {
			checkMap(key, lock, 0, 0);
			if (answer)
				return;
			if (cnt == 4)
				return;

			// ȸ����Ű�� ���
			int[][] temp = rotate(key);

			dfs(temp, lock, cnt + 1);
		}

		// ȸ���� ���� ���ʴ�� üũ
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

		// 90�� key ȸ��
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
