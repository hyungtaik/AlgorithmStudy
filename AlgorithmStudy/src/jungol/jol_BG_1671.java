package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class jol_BG_1671 {
	static int N; // 색종이 수
	static boolean[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new boolean[100][100];
		
		int x,y;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			for(int ty = 100-y-1;ty>90-y-1;ty--) {
				for(int tx = x;tx<x+10;tx++) {
					map[ty][tx] = true;
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] == true) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	

}
