package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_8895 {

	private static int TC;
	private static int n;
	private static int l;
	private static int r;
	private static long result;
	private static long[][][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			result = 0L;

			dp = new long[21][21][21];
			dp[1][1][1] = 1; // 1 1 1 인경우 넣고 시작
			
			result = dp_solve(n,l,r);
			
			System.out.println(result);
		}
	}

	public static long dp_solve(int n, int l, int r) {
		if (l < 1 || r < 1)
			return 0;
		if (l + r - 1 > n ) { // 높이 두개 더한 것이 n보다 작아야 한다.
			return 0;		  // 왼쪽에서 개수 + 오른쪽에서 개수 - 두번세어진것(1개)
		}
		if (dp[n][l][r] != 0) return dp[n][l][r];
		dp[n][l][r] += dp_solve(n - 1, l - 1, r); // 높이 1인 막대 위치 제일 왼쪽일때
		dp[n][l][r] += dp_solve(n - 1, l, r - 1); // 높이 1인 막대 위치 제일 오른쪽
		dp[n][l][r] += dp_solve(n - 1, l, r) * (n-2); // 왼쪽 오른쪽 뺀 n-2개만큼 1층짜리를 넣을수 있다. 

		return dp[n][l][r];
	}

}
