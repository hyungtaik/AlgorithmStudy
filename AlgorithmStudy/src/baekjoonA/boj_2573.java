package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2573 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int[][] tmap;
	private static LinkedList<Pair> q;
	private static LinkedList<Pair> qlist;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		
		map = new int[N][M];
		tmap = new int[N][M];
		q = new LinkedList<Pair>();
		
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>0) {
					q.add(new Pair(i,j,map[i][j]));
				}
			}
		}
		solve();
	}
	private static void solve() {
		int count = 0;
		while(!q.isEmpty()) {
			
			tmap = new int[N][M];
			
			boolean	[][] visited = new boolean[N][M];
			int cnt = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]!=0 && !visited[i][j]) {
						dfs(i,j,visited);
						cnt++;
					}
				}
			}
//			if(!bfs()) { // 연결되어있는지 확인
//				System.out.println(count);
//				return;
//			}
			if(cnt == 0) {
				System.out.println(0);
				return;
			}
			else if(cnt>1) {
				System.out.println(count);
				return;
			}else {
				int len = q.size();
				count++;
				for(int i=0;i<len;i++) {
					Pair temp = q.poll();
					int x = temp.x;
					int y = temp.y;
					int height = temp.height;
					for(int dir = 0;dir<4;dir++) {
						int nx = x+dx[dir];
						int ny = y+dy[dir];
						if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
						if(map[nx][ny] == 0) {
							height--;
						}
					}
					if(height>0) {
						q.add(new Pair(x,y,height));
						tmap[x][y] = height;
					}
				}
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<M;j++) {
						map[i][j] = tmap[i][j];
					}
				}
				
			}
		}
		System.out.println(0);
	}
	private static void dfs(int x,int y,boolean[][] visited) {
		visited[x][y] = true;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
			if(visited[nx][ny]) continue;
			if(map[nx][ny]==0) continue;	
			dfs(nx,ny,visited);
		}
	}
	
	private static boolean bfs() {
		qlist = new LinkedList<Pair>(); // 임시 리스트
		boolean	[][] visited = new boolean[N][M];
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]!=0 && !visited[i][j]) {
					count++;
					if(count>1) {
						return false; // 2개 이상
					}
					visited[i][j] = true;
					qlist.add(new Pair(i,j,map[i][j]));
					while(!qlist.isEmpty()) {
						Pair temp = qlist.poll();
						int x = temp.x;
						int y = temp.y;
						for(int dir=0;dir<4;dir++) {
							int nx = x+dx[dir];
							int ny = y+dy[dir];
							if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
							if(visited[nx][ny]) continue;
							if(map[nx][ny] == 0) continue;
							visited[nx][ny] = true;
							qlist.add(new Pair(nx,ny,map[nx][ny]));
						}
					}
				}
			}
		}
		return true; // 다 연결
	}
	
	static class Pair{
		int x,y,height;

		public Pair(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}

}
