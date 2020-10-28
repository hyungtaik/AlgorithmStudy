package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 12865번: 평범한 배낭 <br>
 *      메모리: 54200 KB <br>
 *      시간: 212 ms
 * @since 2020-10-28
 * 
 */

public class boj_12865 {

	private static int N;
	private static int K;
	private static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<Pair> list = new ArrayList<Pair>();
		list.add(new Pair(0,0)); // 첫번째는 초기화
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			list.add(new Pair(W,V));
		}
		dp = new int[N+1][K+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				dp[i][j] = dp[i-1][j];
				Pair temp = list.get(i);
				if(j >= temp.W ) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-temp.W]+ temp.V);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
	
	static class Pair{
		int W,V;

		public Pair(int w, int v) {
			super();
			W = w;
			V = v;
		}
	}

}
