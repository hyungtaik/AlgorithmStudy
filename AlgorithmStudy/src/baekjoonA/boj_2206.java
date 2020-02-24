package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
6 4
0100
1110
1000
0000
0111
0000
 * 
 */

public class boj_2206 {

	private static int M;
	private static int N;
	private static int[][] map;
	private static LinkedList<go> q;
	private static boolean[][][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new int[N][M];
		visited = new boolean[N][M][2]; // 0: 깬적없을때, 1: 깬적있을때
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String input = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - 48;
			}
		}
		min = Integer.MAX_VALUE;
		q = new LinkedList<go>();
		q.add(new go(0, 0, 1, 1));
		visited[0][0][0] = true;
		bfs();
//		dfs(0,0,1,1);
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visited[i][j]+" ");
//			}System.out.println();
//		}
		System.out.println(min);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			go temp = q.poll();

			int x = temp.x;
			int y = temp.y;
			int count = temp.count;
			int move = temp.move;
//				System.out.println(x+" "+y+" "+count+" "+move);
			if (x == N - 1 && y == M - 1) {
				min = Math.min(min, move);
				return;
			}

			if (move > min)
				continue;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1)
					continue;
//				if (visited[nx][ny] < move)
//					continue;
				if(count >0) {
					if(visited[nx][ny][0]) continue;
					
					if(map[nx][ny]==1) {
						q.add(new go(nx, ny, count - 1, move + 1));
						visited[nx][ny][1] = true;
					}else {
						q.add(new go(nx, ny, count, move + 1));
						visited[nx][ny][0] = true;
					}
				}else {
					if(visited[nx][ny][1]) continue;
					
					if(map[nx][ny]==1) {
						continue;
					}else {
						q.add(new go(nx, ny, count, move + 1));
						visited[nx][ny][1] = true;
					}
				}
				

			}

		}
	}

	static class go {
		int x, y, count, move;

		public go(int x, int y, int count, int move) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.move = move;
		}

	}
}