package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class jol_BG_1438 {
	static int N; // 색종이 수
	static boolean[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
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
				if(map[i][j] == true ) {
					if(i == 0 || i == 99) count++;
					if(j ==0||j==99) count++;
					for(int dir=0;dir<4;dir++) {
						int nextX = i+dx[dir];
						int nextY = j+dy[dir];
						if(nextX < 0 || nextX >= 100 || nextY <0||nextY >=100) {
							continue;
						}
						if(map[nextX][nextY]==false) {
							count++;
						}
					}
				}
			}
		}
		System.out.println(count);
	}
	
	

}
