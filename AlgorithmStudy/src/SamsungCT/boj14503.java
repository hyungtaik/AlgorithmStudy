package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14503 {

	static int N,M,x,y,dir;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		count = 0;
		
		Point cur = new Point(x,y,dir);
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(cur);
		print();
		System.out.println(count);
	}
	static void solve(Point cur) {
		int cdir = cur.dir;
		if(map[cur.x][cur.y] == 2) {
			cdir = cur.dir - 2;
			if(cdir == -1) cdir = 3;
			if(cdir == -2) cdir = 2;
			int nx = cur.x + dx[cdir];
			int ny = cur.y + dy[cdir];
			Point next = new Point(nx,ny,cdir);
			if(map[nx][ny] == 0) {
				return;
			}else {
				solve(next);
			}
		}
		map[cur.x][cur.y] = 2;
		count++;
		// 왼쪽부터 검사
		
		for(int i=0;i<4;i++) {
			cdir = cdir - 1;
			if(cdir == -1) cdir = 3;
			int nx = cur.x + dx[cdir];
			int ny = cur.y + dy[cdir];
			
			if(map[nx][ny] < 1) {
				Point next = new Point(nx,ny,cdir);
				solve(next);
				break;
			}
		}
		
		
	}
	static class Point{
		int x,y;
		int dir;
		
		Point(int x,int y,int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	static void print() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
