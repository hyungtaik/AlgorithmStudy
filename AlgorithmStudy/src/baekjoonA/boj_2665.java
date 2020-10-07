package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 2665번: 미로 만들기 <br>
 *      메모리: 16900 KB <br>
 *      시간: 132 ms
 * @since 2020-10-07
 * 
 */

public class boj_2665 {

	private static int N;
	private static int[][] map;
	private static LinkedList<Pair> q;
	private static int min;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(input.charAt(j)+"");
			}
		}
		visited = new int[N][N];
		q = new LinkedList<Pair>();
		q.add(new Pair(0,0,0));
		for(int i=0;i<N;i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		visited[0][0] = 0;
		min = Integer.MAX_VALUE;
		bfs();
		if(min == Integer.MAX_VALUE) min = 0;
		System.out.println(min);
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			for(int i=0;i<4;i++) {
				int nx = temp.x+dx[i];
				int ny = temp.y+dy[i];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				if(nx == N-1 && ny == N-1) {
					min = Math.min(min, temp.count);
					continue;
				}
				int cnt = temp.count;
				if(map[nx][ny]==0) cnt++;
				if(visited[nx][ny] <= cnt) continue;
				visited[nx][ny] = cnt;
				
				q.add(new Pair(nx,ny,cnt));
			}
		}
	}

	static class Pair{
		int x,y;
		int count;
		
		public Pair(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
	}
}
