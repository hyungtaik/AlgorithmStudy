package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_모의_4012_요리사_김형택 {

	private static int TC;
	private static int N;
	private static int[][] map;
	private static boolean[] number;
	private static int[] pick;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			number = new boolean[N];
			pick = new int[N/2];
			dfs(0,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	static void dfs(int index,int count) {
		if(count == N/2) {
			int a = sum(pick);
			int[] picked = new int[N/2];
			for(int i=0,temp=0;i<N;i++) {
				if(!number[i]) {
					picked[temp++] = i;
				}
			}
			int b = sum(picked);
			int result = Math.abs(a-b);
			if(min>result) {
				min = result;
			}
			return;
		}
		for(int i=index;i<N;i++) {
			if(!number[i]) {
				pick[count]=i;
				number[i] = true;
				dfs(i+1,count+1);
				number[i] = false;
			}
		}
	}
	static int sum(int[] arr) {
		int sum = 0;
		for(int i=0;i<N/2;i++) {
			int a = arr[i];
			for(int j=i+1;j<N/2;j++) {
				int b = arr[j];
				sum += map[a][b] + map[b][a];
			}
		}
		return sum;
		
	}

}
