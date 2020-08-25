package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DP
 * 
 * @see 백준 1890번: 점프 <br>
 * 		메모리: 13280 KB <br>
 * 		시간: 88 ms
 * @since 2020-08-25
 * 
 */


public class boj_1890 {

	private static int[] dx = {1,0};
	private static int[] dy = {0,1};
	private static long[][] dp;
	private static int[][] map;
	private static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		// 0,0 -> N-1,N-1 : 경로 개수
		// 점프할 때 방향 바꾸기 x - 오른쪽 or 아래
		// 0은 종착점
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		dp = new long[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], -1);
		}
		System.out.println(solve(0,0));
	}
	
	// DP (Top Down + 메모이제이션)
	static long solve(int x,int y) {
		if(dp[x][y] != -1) return dp[x][y];
		
		if(x == N-1 && y == N-1) return 1;
		
		if(map[x][y] == 0) return 0;
		
		dp[x][y] = 0;
		
		for(int dir=0;dir<2;dir++) {
			int nx = x+dx[dir]*map[x][y];
			int ny = y+dy[dir]*map[x][y];
			
			if(nx>N-1 || ny > N-1) continue;
			
			dp[x][y] += solve(nx,ny);
		}
		return dp[x][y];
	}
}
