package programmers;

/**
 * 
 * @author TAEK
 * @category DFS
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > DP > N으로 표현 <br>
 * 
 * @since 2020-09-12
 * 
 */

public class N으로표현 {

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
			for (int i = 0; i < 8-count; i++) { // N 사용횟수
				nn = nn * 10 + N; // N 사용해서 자릿수 증가
				dfs(N, count + 1 + i, num + nn, number);
				dfs(N, count + 1 + i, num - nn, number);
				dfs(N, count + 1 + i, num * nn, number);
				dfs(N, count + 1 + i, num / nn, number);
			}
		}
	}
}
