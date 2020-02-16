package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10971 {

	private static int N;
	private static int[][] map;
	private static boolean[] check;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 도시의 수
		map = new int[N][N];
		min = Integer.MAX_VALUE;
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 모든 순열만큼 순회
		perm(new int[N],0);
		System.out.println(min);
	}
	
	static void solve(int[] output) {
		int start = output[0];
		int sum = 0;
		for(int i=0;i<N-1;i++) {
			int a = map[output[i]][output[i+1]];
			if(a == 0) return;
			sum+=a;
		}
		int b = map[output[N-1]][start];
		if(b == 0) return;
		sum+= b;
		min = Math.min(min, sum);
	}
	static void perm(int[] output, int index) {
		if(index == N) {
			solve(output);
			return;
		}
		for(int i=0;i<N;i++) {
			if(check[i] == false) {
				check[i] = true;
				output[index] = i;
				perm(output,index+1);
				check[i] = false;
			}
		}
	}

}
