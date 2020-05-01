package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swea_1949 {

	private static int TC;
	private static int N;
	private static int K;
	private static int[][] map;
	private static int max;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	private static LinkedList<Pair> q;
	private static boolean[][] visited;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			result = Integer.MIN_VALUE;
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++	) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max,map[i][j]);
				}
			}
			q = new LinkedList<Pair>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++	) {
					if(max == map[i][j]) {
						q.add(new Pair(i,j));
					}
				}
			}
			visited = new boolean[N][N];
			while(!q.isEmpty()) {
				Pair temp = q.poll();
				visited[temp.x][temp.y] = true;
				dfs(temp.x,temp.y,1,true);
				visited[temp.x][temp.y] = false;
			}
			System.out.println("#"+tc+" "+result);
		}
	}
	static void dfs(int x,int y,int count,boolean dig) {
		boolean check = false;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
			if(visited[nx][ny]) continue;
			if(map[nx][ny] < map[x][y]) {
				check = true;
				visited[nx][ny] = true;
				dfs(nx,ny,count+1,dig);
				visited[nx][ny] = false;
			}else if(dig && map[nx][ny] >= map[x][y]) {
				int temp = K;
				for(int j=1;j<=temp;j++) {
					if(map[nx][ny]-j < map[x][y]) {
						check = true;
						map[nx][ny]-=j;
						visited[nx][ny] = true;
						dfs(nx,ny,count+1,false);
						visited[nx][ny] = false;
						map[nx][ny]+=j;
					}
				}
			}
		}
		if(!check) {
			result = Math.max(result,count);
		}
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
