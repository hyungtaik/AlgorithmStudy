package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_9282 {

	private static int TC;
	private static int N;
	private static int M;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			memo = new int[N][M][N+1][M+1];
			int temp = solve(0,0,N,M);
			System.out.println("#"+tc+" "+temp);
		}
	}
	
	static int[][][][] memo;
	static int solve(int startX,int startY,int rLen,int cLen) {
		if(rLen == 1 && cLen == 1) {
			return 0;
		}
		if(memo[startX][startY][rLen][cLen]>0) {
			return memo[startX][startY][rLen][cLen];
		}
		
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for(int i=startX;i<startX+rLen;i++) {
			for(int j=startY;j<startY+cLen;j++) {
				sum+=map[i][j];
			}
		}
		// 가로
		for(int i=1;i<rLen;i++) {
			int temp = sum + solve(startX,startY,i,cLen) + solve(startX+i,startY,rLen-i,cLen);
			min = Math.min(min, temp);
		}
		// 세로 
		for(int j=1;j<cLen;j++) {
			int temp = sum + solve(startX,startY,rLen,j) + solve(startX,startY+j,rLen,cLen-j);
			min = Math.min(min, temp);
		}
		memo[startX][startY][rLen][cLen] = min;
		
		return min;
	}


}
