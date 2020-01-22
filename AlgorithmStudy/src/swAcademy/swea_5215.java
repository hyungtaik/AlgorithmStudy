package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
*/
public class swea_5215 {
	static int N,L,max;
	static int[] score, kal;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testcase = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=testcase;tc++) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score = new int[N];
			kal = new int[N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				score[i] =Integer.parseInt(st.nextToken());
				kal[i] =Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0,0);
			System.out.println("#"+tc+" "+max);
		}
	}
	static void dfs(int sc,int cal,int index) {
		if(cal>L) {
			return;
		}
		
		if(index == N) {
			max = Math.max(max, sc);
			return;
		}
		
		dfs(sc+score[index],cal+kal[index],index+1);
		dfs(sc,cal,index+1);
	}
}
