package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 
 * 
 * @see 백준 6087번: 레이저 통신 <br>
 *      메모리: 66212 KB <br>
 *      시간: 256 ms
 * @since 2020-09-04
 * 
 */

public class boj_6087 {

	private static int W;
	private static int H;
	private static char[][] map;
	private static Pair start;
	private static Pair end;
	private static int[][] visited;
	private static LinkedList<Pair> q;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		start = new Pair(-1,-1,0,0);
		end = new Pair(-1,-1,0,0);
		
		visited = new int[H][W];
		for(int i=0;i<H;i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		for(int i=0;i<H;i++) {
			String input = br.readLine();
			for(int j=0;j<W;j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='C') {
					if(start.x == -1 ) {
						start = new Pair(i,j,-1,0);
					}else {
						end = new Pair(i,j,-1,0);
					}
				}
			}
		}
		q = new LinkedList<Pair>();
		q.add(start);
		visited[start.x][start.y] = 0;
		bfs();
		
	}
	static void bfs() {
		while(!q.isEmpty()) {
			int len = q.size();
			for(int i=0;i<len;i++) {
				Pair temp = q.poll();
				if(temp.x == end.x && temp.y == end.y) {
					continue;
				}
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if(nx<0||nx>H-1||ny<0||ny>W-1) continue;
					if(map[nx][ny]=='*') continue;
					if(visited[nx][ny] < temp.count) {
						continue;
					}
					if(temp.dir>=0) {
						if(temp.dir == dir) {
							visited[nx][ny] = temp.count;
							q.add(new Pair(nx,ny,dir,temp.count));
						}else {
							if(visited[nx][ny] <= temp.count) continue; 
							visited[nx][ny] = temp.count+1;
							q.add(new Pair(nx,ny,dir,temp.count+1));
						}
					}else { // 초기 값은 -1
						q.add(new Pair(nx,ny,dir,temp.count));
						visited[nx][ny] = temp.count;
					}
				}
			}
		}
		System.out.println(visited[end.x][end.y]);
	}
	
	static class Pair{
		int x,y;
		int dir,count;
		public Pair(int x, int y, int dir, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}
	}

}
