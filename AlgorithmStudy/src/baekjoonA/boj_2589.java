package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2589 {

	private static int col;
	private static int row;
	private static char[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static boolean[][] visited;
	private static int max;
	private static LinkedList<Pair> q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];
		visited = new boolean[row][col];
		for(int i=0;i<row;i++) {
			String input = br.readLine();
			for(int j=0;j<col;j++) {
				char temp = input.charAt(j);
				map[i][j] = temp;
			}
		}
		max = Integer.MIN_VALUE;
		q = new LinkedList<Pair>();
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(map[i][j] == 'L') {
					q.clear();
					visited = new boolean[row][col];
					visited[i][j] = true;
					q.add(new Pair(i,j,0));
					bfs();
				}
			}
		}
		if(max == Integer.MIN_VALUE) {
			max = 0;
		}
		System.out.println(max);
	}
	// L: À°Áö
	static void bfs() {
		int count = 0;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			int x = p.x;
			int y = p.y;
			count = p.count;
			max = Math.max(max, count);
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>row-1 || ny<0||ny>col-1) continue;
				if(map[nx][ny] == 'W') continue;
				if(visited[nx][ny]) continue;
				visited[nx][ny] = true;
				q.add(new Pair(nx,ny,count+1));
			}
		}
		
	}
	static class Pair{
		int x,y,count;

		public Pair(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
	}
}
