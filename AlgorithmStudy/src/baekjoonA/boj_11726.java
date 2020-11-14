package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see ���� 11726��: 2Xn Ÿ�ϸ� <br>
 *      �޸�: 11584 KB <br>
 *      �ð�: 80 ms
 * @since 2020-11-14
 * 
 */

public class boj_11726 {

	private static int[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		
		System.out.println(dfs(n));
	}
	static int dfs(int n) {
		if(dp[n] > 0) return dp[n];
		dp[n] = dfs(n-1) + dfs(n-2);
		dp[n] %= 10007;
		return dp[n];
	}

}
