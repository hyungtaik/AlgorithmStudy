package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 2225번: 합분해 <br>
 *      메모리: 12060 KB <br>
 *      시간: 104 ms
 * @since 2020-11-07
 * 
 */

public class boj_2225 {

	private static long[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]); // 정수 0 ~ n
		int k = Integer.parseInt(input[1]); // K개
		
		dp = new long[k+1][n+1];
		
		for(int i=0;i<=k;i++) {
			dp[i][0] = 1;
		}
		for(int i=0;i<=n;i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2;i<=k;i++) {
			for(int j=1;j<=n;j++) {
				for(int idx=0;idx<=j;idx++) {
					dp[i][j] += dp[i-1][idx]%1000000000;
				}
			}
		}
		
		System.out.println(dp[k][n]%1000000000);
	}

}
