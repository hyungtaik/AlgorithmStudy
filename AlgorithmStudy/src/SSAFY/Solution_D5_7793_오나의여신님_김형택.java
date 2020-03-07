package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_D5_7793_오나의여신님_김형택 {

	private static int TC;
	private static int N;
	private static int M;
	private static char[][] map;
	private static LinkedList<Pair> devil;
	private static Pair start;
	private static Pair end;
	private static LinkedList<Pair> q;
	private static int min;
	private static boolean[][] visited;
	private static boolean[][] check;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			devil = new LinkedList<Pair>();
			q = new LinkedList<Pair>();
			
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < M; j++) {
					char temp = input.charAt(j);
					map[i][j] = temp;
					if (temp == '*') {
						devil.add(new Pair(i, j));
						visited[i][j] = true;
					}
					else if (temp == 'S')
						start = new Pair(i, j);
					else if (temp == 'D')
						end = new Pair(i, j);
				}
			}
			min = Integer.MAX_VALUE;
			q.add(start);
			visited[start.x][start.y] = true;
			bfs();
			if (min == Integer.MAX_VALUE)
				System.out.println("#" + tc + " GAME OVER");
			else {
				System.out.println("#" + tc + " " + min);
			}
		}
	}

	static void bfs() {
		int count = 0;
		while(!q.isEmpty()) {
			check = new boolean[N][M];
			count++;
			// 악마먼저
			int len = devil.size();
			for(int l=0;l<len;l++	) {
				Pair temp = devil.poll();
				for(int i=0;i<4;i++) {
					int nx = temp.x+dx[i];
					int ny = temp.y+dy[i];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny]) continue;
					if(check[nx][ny]) continue;
					if(map[nx][ny]=='.' || map[nx][ny] == 'S') {
						check[nx][ny] = true;
						devil.add(new Pair(nx,ny));
					}
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(check[i][j]) {
						visited[i][j] =true;
					}
				}
			}
			// 수연 이동
			len = q.size();
			for(int l=0;l<len;l++) {
				Pair su = q.poll();
				for(int i=0;i<4;i++) {
					int nx = su.x+dx[i];
					int ny = su.y+dy[i];
					if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny]=='D') {
						min = Math.min(min, count);
						return;
					}
					if(map[nx][ny]=='.') {
						visited[nx][ny] = true;
						q.add(new Pair(nx,ny));
					}
				}
			}
			
		}
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
