package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1563 {

	private static int N;
	private static int[][][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[1001][3][4];
		for(int i=0;i<N;i++) {
			for(int j=0;j<3;j++) {
				for(int k=0;k<4;k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		System.out.println(solve(0,0,0));
	}
	static int solve(int day,int late,int absent) {
		if(late>=2 || absent>=3) return 0;
		
		if(day==N) return 1;
		
		if(dp[day][late][absent] != -1) {
			return dp[day][late][absent];
		}
		// 출석, 지각, 결석
		return dp[day][late][absent] = (solve(day+1,late,0)+solve(day+1,late+1,0)+solve(day+1,late,absent+1))%1000000;
	}

}
