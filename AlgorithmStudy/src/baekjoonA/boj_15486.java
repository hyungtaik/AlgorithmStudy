package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15486 {

	private static int N;
	private static int[][] map;
	private static long max;
	private static long[] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[2][N+2];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			map[0][i] = Integer.parseInt(st.nextToken()); // T
			map[1][i] = Integer.parseInt(st.nextToken()); // P
		}
		max = Long.MIN_VALUE;
		dp = new long[N+2];
		solve();
		System.out.println(max);
	}
	static void solve() {
		for(int i=1;i<=N+1;i++) {
			max = Math.max(max,dp[i]);
			int T = map[0][i];
			int P = map[1][i];
			
			if(i+T > N+1) continue;
			
			dp[i+T] = Math.max(max+P,dp[i+T]);
		}
	}

}
