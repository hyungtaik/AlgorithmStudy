package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 10844번: 쉬운 계단 수 <br>
 *      메모리: 11484 KB <br>
 *      시간: 76 ms
 * @since 2020-11-04
 * 
 */

public class boj_10844 {

	private static long[][] dp;
	private static long result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		dp = new long[n+1][10];
		
		for(int i=1;i<10;i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2;i<=n;i++) {
			for(int j=0;j<10;j++) { // 마지막 자리수
				if(j==0) {
					dp[i][j] = dp[i-1][j+1];
				}else if(j==9) {
					dp[i][j] = dp[i-1][j-1];
				}else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				}
				dp[i][j] %= 1000000000;
			}
		}
		result = 0L;
		for(int i=0;i<10;i++) {
			result+=dp[n][i];
		}
		result %= 1000000000;
		System.out.println(result);
	}

}
