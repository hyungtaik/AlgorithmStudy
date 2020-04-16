package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_2156 {

	private static int n;
	private static int[] dp;
	private static int[] drink;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		drink = new int[n+1];
		for(int i=1;i<=n;i++) {
			drink[i] = Integer.parseInt(br.readLine());
		}
		max = Integer.MIN_VALUE;
		dp[1] = drink[1];
		if(n > 1) {
			dp[2] = drink[1] + drink[2];
			max = dp[2];
			for(int i=3;i<=n;i++) {
				int temp = dp[0];
				for(int j=1;j<=i-2;j++) {
					temp = Math.max(temp,dp[j]);
				}
				int straight = temp+drink[i];
				temp = dp[0];
				for(int j=1;j<=i-3;j++) {
					temp = Math.max(temp,dp[j]);
				}
				int noStraight = temp + drink[i]+drink[i-1];
				dp[i] = Math.max(straight,noStraight);
				max = Math.max(max,dp[i]);
			}
			
		} else {
			max = dp[1];
		}
		
		System.out.println(max);
	}

}
