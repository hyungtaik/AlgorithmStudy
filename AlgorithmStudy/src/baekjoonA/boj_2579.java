package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2579 {
	private static int N;
	private static int[] stair;
	private static int[] dp;
	private static int jumpYes;
	private static int jumpNo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		stair = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[N + 1];
		dp[1] = stair[1];
		if(N>1) {
			dp[2] = stair[1]+stair[2];
			for(int i=3;i<=N;i++) {
				jumpYes = stair[i]+dp[i-2];
				jumpNo = stair[i]+stair[i-1]+dp[i-3];
				
				dp[i] = Math.max(jumpNo,jumpYes);
			}
		}
		System.out.println(dp[N]);
		
	}
	
}
