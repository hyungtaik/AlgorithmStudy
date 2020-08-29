package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 14442번: 벽 부수고 이동하기 2
 * 		메모리: 361148 KB <br>
 * 		시간: 1288 ms
 * @since 2020-08-29
 * 
 */

public class boj_14442 {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static LinkedList<Point> q;
	private static boolean[][][] visited;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		q = new LinkedList<Point>();
		q.add(new Point(0,0,K));
		
		visited = new boolean[N][M][K+1];
		int count = 1;
		boolean valid = false;
		L:while(!q.isEmpty()) {
			int len = q.size();
			count++;
			for(int l=0;l<len;l++) {
				Point temp = q.poll();
				for(int dir=0;dir<4;dir++) {
					int nx = temp.x+dx[dir];
					int ny = temp.y+dy[dir];
					
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					
					if(nx == N-1 && ny == M-1) {
						valid = true;
						break L;
					}
					
					if(map[nx][ny] == 0 && !visited[nx][ny][temp.k]) {
						visited[nx][ny][temp.k] = true;
						q.add(new Point(nx,ny,temp.k));
					}else if(map[nx][ny] == 1 && temp.k > 0 && !visited[nx][ny][temp.k] ){
						visited[nx][ny][temp.k] = true;
						q.add(new Point(nx,ny,temp.k-1));
					}
				}
			}
		}
		if(valid) {
			System.out.println(count);
		} else if(N == 1 && M == 1){
			System.out.println(1);
		} else {
			System.out.println(-1);
		}
		
	}
	static class Point{
		int x,y;
		int k;
		
		public Point(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}
		
		
	}

}
