package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swea_1953 {

	private static int TC;
	private static int N,M,R,C,L;
	private static int[][] map;
	private static LinkedList<Pair> q;
	private static boolean[][] visited;
	private static int[] dx	 = {-1,0,1,0};
	private static int[] dy	 = {0,-1,0,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			q = new LinkedList<Pair>();
			visited = new boolean[N][M];
			q.add(new Pair(R,C));
			visited[R][C] = true;
			
			System.out.println("#"+tc+" "+bfs());
		}
	}
	static boolean[] isOK(int num) {
		boolean[] pass = new boolean[4];
		switch(num) {
		case 1: // 위,왼,아래,오
			break;
		case 2: // 위, 아래
			pass[1] = pass[3] = true;
			break;
		case 3: // 왼, 오
			pass[0] = pass[2] = true;
			break;
		case 4: // 위, 오
			pass[1] = pass[2] = true;
			break;
		case 5: // 아, 오
			pass[0] = pass[1] = true;
			break;
		case 6: // 왼, 아
			pass[0] = pass[3] = true;
			break;
		case 7: // 왼, 위
			pass[2] = pass[3] = true;
			break;
		}
		return pass;
	}
	static int bfs() {
		int count = L;
		int cnt = 0;
		while(--count>0) {
			int len = q.size();
			for(int i=0;i<len;i++) { // 위 왼쪽 아래  오른쪽 0123
				Pair temp = q.poll();
				int t = map[temp.x][temp.y];
				boolean[] pass = isOK(t);
				for(int dir=0;dir<4;dir++) {
					if(pass[dir]) continue;
					int nx = temp.x+dx[dir];
					int ny = temp.y+dy[dir];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny]>0) {
						boolean[] check = isOK(map[nx][ny]);
						int ddir = (dir+2)%4;
						
						if(check[ddir]) continue;
						q.add(new Pair(nx,ny));
						visited[nx][ny] = true;
						cnt++;
					}
				}
			}
		}
		return cnt+1;
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
