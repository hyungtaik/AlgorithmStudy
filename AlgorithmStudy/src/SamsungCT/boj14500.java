package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14500 {
	static int MAX = Integer.MIN_VALUE;
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static boolean[][] checked;
	
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
		checked = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				dfs(i,j,0,0);
				exception(i,j);
			}
		}
		
		System.out.println(MAX);
	}
	
	static void exception(int x,int y) {
		int wing= 4;
		int min = Integer.MAX_VALUE;
		int sum = map[x][y];
		
		for(int i=0;i<4;i++) {
			int nextX = x+dx[i];
			int nextY = y+dy[i];
			
			if(wing == 2) {
				return;
			}
			if(nextX <0 || nextX >N-1 || nextY <0 || nextY >M-1) {
				wing--;
				continue;
			}
			sum+=map[nextX][nextY];
			min = Math.min(min, map[nextX][nextY]);
			
		}
		
		if(wing == 4) {
			sum -= min;
		}
		
		MAX = Math.max(MAX, sum);

		
	}
	
	static void dfs(int x,int y,int max, int count) {
		
		count++;
		
		if(x<0||x>N-1||y<0||y>M-1) {
			return;
		}
		
		if(checked[x][y]) {
			return;
		}
		
		if(count==5) {
			if(MAX < max) {
				MAX = max;
			}
			checked[x][y] = false;
			return;
		}
		
		checked[x][y] = true;
		max += map[x][y];
		
		
		
		for(int i=0;i<4;i++) {
			dfs(x+dx[i],y+dy[i],max, count);
		}
		checked[x][y] = false;
		return;
	}

}
