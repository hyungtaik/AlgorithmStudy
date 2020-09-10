package programmers;

/**
 * 
 * @author TAEK
 * @category �ùķ��̼�
 * 
 * @see ���α׷��ӽ� : KAKAO BLIND RECRUITMENT ��հ� �� ��ġ <br>
 * 
 * @since 2020-09-10
 * 
 */

public class KAKAO2020_��հ�����ġ {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 5;
		int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
				{ 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		int[][] result = s.solution(n, build_frame);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Solution {
		boolean[][] pillar, bo; // ���, ��

		public int[][] solution(int n, int[][] build_frame) {
			int count = 0;

			pillar = new boolean[n + 1][n + 1];
			bo = new boolean[n + 1][n + 1];

			for (int i = 0; i < build_frame.length; i++) {
				int x = n - build_frame[i][1]; // row
				int y = build_frame[i][0]; // col
				int arch = build_frame[i][2]; // ���๰
				int ok = build_frame[i][3]; // ��ġ ���� ����

				// ok - ��ġ : 1 | ���� : 0
				// arch - ��� : 0 | �� : 1
				if (ok == 1) { // ��ġ
					if (arch == 0) {
						if (pillarOk(x, y, n)) {
							pillar[x][y] = true;
							count++;
						}
					} else {
						if (boOk(x, y,n)) {
							bo[x][y] = true;
							count++;
						}
					}
				} else {
					if (arch == 0) {
						pillar[x][y] = false;
					} else if (arch == 1) {
						bo[x][y] = false;
					}

					if (destroy(x, y, arch, n)) {
						count--;
						continue;
					}

					if (arch == 0) {
						pillar[x][y] = true;
					} else if (arch == 1) {
						bo[x][y] = true;
					}
				}

			}

			int index = 0;
			int[][] answer = new int[count][3];

			for (int j = 0; j <= n; j++) {
				for (int i = n; i >= 0; i--) {
					if (pillar[i][j])
						answer[index++] = new int[] { j, n - i, 0 };
					if (bo[i][j])
						answer[index++] = new int[] { j, n - i, 1 };
				}
			}
			return answer;
		}

		private boolean pillarOk(int x, int y, int n) {
			// �Ʒ� ���, �¿� �� Ȯ��
			if (x == n || pillar[x + 1][y] || bo[x][y])
				return true;
			if(y!=0 && bo[x][y - 1]) return true;
			return false;
		}

		private boolean boOk(int x, int y,int n) {
			if (pillar[x + 1][y])
				return true;
			if(y!=n && pillar[x + 1][y + 1]) {
				return true;
			}
			if(y!=0 && y!=n && (bo[x][y - 1] && bo[x][y + 1])){
				return true;
			}
			return false;
		}

		private boolean destroy(int y, int x, int arch, int n) {
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n; j++) {
					if (pillar[i][j] && !pillarOk(i, j, n)) {
						return false;
					}
					if (bo[i][j] && !boOk(i, j,n)) {
						return false;
					}
				}
			}

			return true;
		}
	}
}
