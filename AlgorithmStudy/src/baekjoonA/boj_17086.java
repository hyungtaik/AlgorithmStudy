package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_17086 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static LinkedList<Pair> q ;
	private static boolean[][] visited;
	private static int max;
	private static int[] dx = {-1,-1,-1,0,0,1,1,1};
	private static int[] dy = {-1,0,1,-1,1,-1,0,1};
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q = new LinkedList<Pair>();
		max = Integer.MIN_VALUE;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j] == 0) {
					q.clear();
					visited = new boolean[N][M];
					q.add(new Pair(i,j));
					visited[i][j] = true;
					bfs();
					visited[i][j] = false;
				}
			}
		}
		System.out.println(max);
	}
	static void bfs() {
		int count = 0;
		L:while(!q.isEmpty()) {
			int size = q.size();
			count++;
			for(int i=0;i<size;i++) {
				Pair temp = q.poll();
				for(int dir =0;dir<8;dir++) {
					int nx = temp.x+dx[dir];
					int ny = temp.y+dy[dir];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny]) continue;
//					System.out.println(temp.x+" "+temp.y+" => "+count);
					if(map[nx][ny] == 0) {
						q.add(new Pair(nx,ny));
						visited[nx][ny] = true;
					}else if(map[nx][ny]==1) {
						break L;
					}
				}
			}
		}
		if(max<count) max = count;
	}
	static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
