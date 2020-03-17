package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14501 {

	private static int N;
	private static int[][] map;
	private static int T,P;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[2][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			map[0][i] = T;
			map[1][i] = P;
		}
		max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			solve(i,0);
		}
		System.out.println(max);
	}
	static void solve(int day,int sum) {
		if(day > N) {
			return;
		}
		if(day == N) {
			max = Math.max(max, sum);
			return;
		}
		solve(day+map[0][day],sum+map[1][day]);
		solve(day+1,sum);
	}

}
