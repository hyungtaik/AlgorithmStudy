package programmers;

/**
 * 
 * @author TAEK
 * @category DFS
 * 
 * @see ���α׷��ӽ� : �ڵ��׽�Ʈ ���� > DP > N���� ǥ�� <br>
 * 
 * @since 2020-09-12
 * 
 */

public class N����ǥ�� {

	public static void main(String[] args) {
		Solution s = new Solution();
		int N = 5;
		int number = 12;
		System.out.println(s.solution(N, number));
	}

	static class Solution {

		private int answer;

		public int solution(int N, int number) {
			answer = -1;
			dfs(N, 0, 0, number);
			return answer;
		}

		public void dfs(int N, int count, int num, int number) {
			if (count > 8)
				return;
			if (num == number) {
				if (count < answer || answer == -1) {
					answer = count;
				}
				return;
			}
			int nn = 0;
			for (int i = 0; i < 8-count; i++) { // N ���Ƚ��
				nn = nn * 10 + N; // N ����ؼ� �ڸ��� ����
				dfs(N, count + 1 + i, num + nn, number);
				dfs(N, count + 1 + i, num - nn, number);
				dfs(N, count + 1 + i, num * nn, number);
				dfs(N, count + 1 + i, num / nn, number);
			}
		}
	}
}
