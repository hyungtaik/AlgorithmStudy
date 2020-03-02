package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_7699 {

	private static int TC;
	private static int[][] map;
	private static int C;
	private static int R;
	private static int result;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static boolean[] alpha;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			
			for(int i=0;i<R;i++) {
				String input = br.readLine();
				for(int j=0;j<C;j++) {
					map[i][j] = input.charAt(j)-65;
				}
			}
			result = Integer.MIN_VALUE;
			alpha = new boolean[26]; // ¾ËÆÄºª
			alpha[map[0][0]] = true;
			dfs(0,0,1);
			System.out.println("#"+tc+" "+result);
		}
	}
	
	static void dfs(int x,int y,int count) {
		if(count>result) {
			result = count;
		}
		if(result == 26) return;
		
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>R-1||ny<0||ny>C-1) continue;
			if(alpha[map[nx][ny]]) continue;
			int number = map[nx][ny];
			alpha[number] = true;
			dfs(nx,ny,count+1);
			alpha[number] = false;
		}
		
	}
	
	static class Pair{
		int x,y;
		int alphabet;
		public Pair(int x, int y, int alphabet) {
			super();
			this.x = x;
			this.y = y;
			this.alphabet = alphabet;
		}
		
	}
}
