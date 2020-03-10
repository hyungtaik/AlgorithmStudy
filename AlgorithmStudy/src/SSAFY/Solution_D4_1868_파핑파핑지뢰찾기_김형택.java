package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D4_1868_파핑파핑지뢰찾기_김형택 {

	private static int TC;
	private static int N;
	private static char[][] map;
	private static int[] dx = {-1,-1,-1,0,0,1,1,1}; // 팔방탐색
	private static int[] dy = {-1,0,1,-1,1,-1,0,1};
	private static boolean[][] visited;
	private static LinkedList<Pair> q;
	private static int[][] result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			map = new char[N][N];
			for(int i=0;i<N;i++) {
				String input = br.readLine();
				for(int j=0;j<N;j++) {
					char ch = input.charAt(j);
					map[i][j] = ch;
				}
			}
			result = new int[N][N];
			q = new LinkedList<Pair>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == '.') {
						check(i,j);
					}else {
						result[i][j] = 9; // 지뢰는 9
					}
				}
			}
			visited = new boolean[N][N];
			int count = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(result[i][j] == 0 && !visited[i][j]) {
						q.add(new Pair(i,j));
						visited[i][j] = true;
						bfs();
						count++;
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j] && result[i][j] != 9) {
						count++;
					}
				}
			}
			System.out.println("#"+tc+" "+count);
		}
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			
			for(int i=0;i<8;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
				if(visited[nx][ny]) continue;
				if(result[nx][ny] == 0) {
					visited[nx][ny] = true;	
					q.add(new Pair(nx,ny));
				}
				visited[nx][ny] = true;
			}
		}
	}
	static void check(int x,int y) {
		int count = 0;
		for(int i=0;i<8;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx<0||nx>N-1||ny<0||ny>N-1) continue;
			if(map[nx][ny] == '*') {
				count++;
			}
		}
		result[x][y] = count;
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
