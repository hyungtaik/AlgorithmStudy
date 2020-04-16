package baekjoonA;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_11727 {

	private static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int result = solve();
		System.out.println(result);
	}
	static int solve() {
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++) {
			if(i==1) dp[i] = 1;
			else if(i==2) dp[i] = 3;
			else {
				// (n-1)에 1x2 막대기 1가지 + (n-2)에 2x1, 2x2 막대기 경우 2가지
					// -> (n-2)에 1x2 는 (n-1)에 1x2 막대기에 속하므로 제외
				dp[i] = (dp[i-1]+dp[i-2]*2)%10007;
			}
		}
		
		return dp[n];
	}

}
