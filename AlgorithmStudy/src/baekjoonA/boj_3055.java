package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_3055 {

	private static int C;
	private static int R;
	private static int[][] map;
	private static LinkedList<Point> q;
	private static LinkedList<Point> water;
	private static int[] dx = {-1,1,0,0}; // 상하좌우
	private static int[] dy = {0,0,-1,1};
	private static int result;
	private static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		map = new int[R][C]; 
		q = new LinkedList<Point>();
		water = new LinkedList<Point>();
		result = Integer.MAX_VALUE;
		visited = new boolean[R][C];
		for(int i=0;i<R;i++) {
			String input = br.readLine();
			for(int j=0;j<input.length();j++) {
				char temp = input.charAt(j);
				if(temp == 'S') { // 고슴도치
					map[i][j] = 4;
					q.add(new Point(i,j));
				}else if(temp == '*') { // 물
					water.add(new Point(i,j));
					map[i][j] = 1;
				}else if(temp == 'X') { // 돌
					map[i][j] = 2;
				}else if(temp == 'D') { // 비버 굴
					map[i][j] = 3;
				}
			}
		}
		bfs();
		if(result == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}
	
	static void bfs() {
		// 물먼저
		// 고슴도치
		int count = 0;
		while(!q.isEmpty()) {
			count++;
			int len = water.size();
			for(int i=0;i<len;i++) {
				Point river = water.poll();
				int rx = river.x;
				int ry = river.y;
				for(int dir=0;dir<4;dir++) {
					int nx = rx+dx[dir];
					int ny = ry+dy[dir];
					if(nx<0||nx>R-1||ny<0||ny>C-1) continue;
					if(map[nx][ny] == 3 || map[nx][ny]==2 || map[nx][ny]==1) continue;
					water.add(new Point(nx,ny));
					map[nx][ny] = 1;
				}
			} // 물 차오름
			len = q.size();
			for(int i=0;i<len;i++) {
				Point ani = q.poll();
				int x = ani.x;
				int y = ani.y;
				
				for(int dir=0;dir<4;dir++) {
					int nx = x+dx[dir];
					int ny = y+dy[dir];
					if(nx<0||nx>R-1||ny<0||ny>C-1) continue;
					if( map[nx][ny]==2 || map[nx][ny]==1) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny]== 3) {
						result = Math.min(result,count);
						continue;
					}
					visited[nx][ny] = true;
					q.add(new Point(nx,ny));
				}
			}
		}
	}
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
