package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1699 {
	private static int N;
	private static int[] memo;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		memo = new int[N+1];
		memo[1] = 1;
		
		for(int i=2;i<=N;i++) {	 
			memo[i]=i;
			for(int j=1;j*j<=i;j++) { 						
				memo[i] = Math.min(memo[i-(j*j)]+1,memo[i]); 	
			}
		}
		
		System.out.println(memo[N]);
	}
	
}
