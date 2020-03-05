package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2105_2 {

	private static int TC;
	private static int result;
	private static int[][] map;
	private static int N;
	private static int[] dx = {1,1,-1,-1};
	private static int[] dy = {-1,1,1,-1};
	private static boolean[][] visited;
	private static boolean[] dessert;
	private static int startY;
	private static int startX;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		TC = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = -1;
			for(int i=0;i<N-1;i++) {
				for(int j=1;j<N-1;j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					startX = i;
					startY = j;

					dfs(i,j,0,1);
					
					visited[i][j] = false;
					dessert[map[i][j]] = false;
				}
			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
	static void dfs(int x,int y,int dir,int sum) {
		if(dir ==4) return;
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		if(nx<0||nx>N-1||ny<0||ny>N-1) {
			return;
		}else {
			if(dir==3) {
				if(startX == nx && startY == ny) {
					result = Math.max(result, sum);
					return;
				}
			}
			if(visited[nx][ny]) return;
			if(dessert[map[nx][ny]]) return;
			visited[nx][ny] = true;
			dessert[map[nx][ny]] = true;
			dfs(nx,ny,dir,sum+1);
			dfs(nx,ny,dir+1,sum+1);
			visited[nx][ny] = false;
			dessert[map[nx][ny]] = false;
			
		}
		
	}
	
}
