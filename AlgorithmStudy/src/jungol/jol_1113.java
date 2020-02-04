package jungol;

import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class jol_1113 {
	static int M, N, m, n; // ¸ÊÅ©±â M,n ¸ñÇ¥ µµÂøÁö m,n
	static int[][] map; // Áöµµ ¸Ê
	static int min; // ÃÖ¼Ò°ª
	static int[] dx = {-1,1,0,0}; //»ó ÇÏ ÁÂ ¿ì
	static int[] dy = {0,0,-1,1};
	static boolean[][] checked;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		map = new int[M][N];
		checked = new boolean[M][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		map[m][n] = 2; // µµÂøÁö
		
		solve(0,0,0,0,0);
		System.out.println(min);
	}
	static void solve(int x,int y,int count,int dirx,int diry) {
		if(count > min) return;
		if(map[x][y] == 2) {
			min = Math.min(min, count-1);
			return;
		}
		checked[x][y] = true;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx <0 || nx > N-1 || ny < 0 || ny > M-1 ) continue;
			
			if(map[nx][ny] == 0 || checked[nx][ny] == true ) continue;
			if(dirx == dx[i] && diry==dy[i]) {
				solve(nx,ny,count,dx[i],dy[i]);
			}else {
				solve(nx,ny,count+1,dx[i],dy[i]);
			}
		}
		checked[x][y] = false;
		return;
	}

}
