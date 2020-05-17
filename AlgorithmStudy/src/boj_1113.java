package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1113 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static boolean[][] visited;
	private static int max;
	private static int min;
	private static LinkedList<Pair> q;
	private static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	private static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	private static LinkedList<Pair> qq;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input.charAt(j) + "");
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		if(min == max ) {
			System.out.println(0);
		}else {
			visited = new boolean[N][M];
			q = new LinkedList<Pair>();
			qq = new LinkedList<Pair>();
			result = 0;
				for (int h = min+1; h <= max; h++) {
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (!visited[i][j] && map[i][j] >= h) {
							q.add(new Pair(i, j));
							visited[i][j] = true;
							bfs(h);
						}
						else {
							if(!visited[i][j]) {
								if(i==0 || i==N-1||j==0||j==M-1 ) {
									qq.add(new Pair(i,j));
									visited[i][j] = true;
								}
							}
						}
					}
				}
				bfs2(h);
				visited = new boolean[N][M];
			}
			System.out.println(result);
		}
		
	}
	static void bfs2(int h) {
		while(!qq.isEmpty()) {
			Pair temp = qq.poll();
			int x = temp.x;
			int y = temp.y;
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if (!visited[nx][ny]) {
					qq.add(new Pair(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		int sum = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(!visited[i][j]) {
					int water = h-map[i][j];
					sum+=water;
					map[i][j] = h;
				}
			}
		}
		result+=sum;
	}
	
	static void bfs(int h) {
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			for(int i=0;i<8;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx<0||nx>N-1||ny<0||ny>M-1) continue;
				if (!visited[nx][ny] && map[nx][ny] >= h) {
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
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
