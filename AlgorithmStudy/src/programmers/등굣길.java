package programmers;

/**
 * 
 * @author TAEK
 * @category DP (Bottom up)
 * 
 * @see 프로그래머스 : 코딩테스트 연습 > DP > 등굣길 <br>
 * 
 * @since 2020-09-18
 * 
 */

public class 등굣길 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int m = 4;
		int n = 3;
		int[][] puddles = { { 2, 2 } };
		s.solution(m, n, puddles);
	}

	static class Solution {
		public int solution(int m, int n, int[][] puddles) {
			int answer = 0;

			int[][] dp = new int[n + 1][m + 1];
			for (int i = 0; i < puddles.length; i++) {
				int x = puddles[i][1];
				int y = puddles[i][0];
				dp[x][y] = -1;
			}
			dp[1][1] = 1;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (dp[i][j] == -1)
						continue;
					if(dp[i][j-1] >= 0 && dp[i][j] >= 0) {
	                    dp[i][j] += dp[i][j-1] %1000000007 ;
	                }
	                if(dp[i-1][j] >= 0 && dp[i][j] >= 0) {
	                    dp[i][j] += dp[i-1][j] % 1000000007;
	                }
				}
			}
	        answer = dp[n][m]%1000000007;
//			System.out.println(answer);
			return answer;
		}
	}
}
