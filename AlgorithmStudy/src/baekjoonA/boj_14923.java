package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 14923번: 미로 탈출 <br>
 *      메모리: 118340 KB <br>
 *      시간: 608 ms
 * @since 2020-08-28
 * 
 */

public class boj_14923 {

	private static int N,M;
	private static int[][] map;
	private static int Hx,Hy;
	private static int Ex,Ey;
	private static boolean[][][] visited;
	private static LinkedList<Point> q;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 전체 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];

		// 현재 위치
		st = new StringTokenizer(br.readLine(), " ");
		Hx = Integer.parseInt(st.nextToken()) - 1;
		Hy = Integer.parseInt(st.nextToken()) - 1;

		// 도착점
		st = new StringTokenizer(br.readLine(), " ");
		Ex = Integer.parseInt(st.nextToken()) - 1;
		Ey = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 마법 횟수는 1번
		q = new LinkedList<Point>();
		q.add(new Point(Hx, Hy, 1));
		visited[Hx][Hy][1] = true;
		int count = 0;
		boolean valid = false;
		L: while (!q.isEmpty()) {
			int len = q.size();
			count++;
			for(int l=0;l<len;l++) {
				Point temp = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = temp.x + dx[i];
					int ny = temp.y + dy[i];
					
					if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1)
						continue;
					
					if(nx == Ex && ny == Ey) {
						valid = true;
						break L;
					}
					
					if(map[nx][ny] == 1 && temp.cost == 1 && !visited[nx][ny][temp.cost]) {
						visited[nx][ny][temp.cost] = true;
						q.add(new Point(nx,ny,temp.cost-1));
					}else if(map[nx][ny] == 0 && !visited[nx][ny][temp.cost]) {
						visited[nx][ny][temp.cost] = true;
						q.add(new Point(nx,ny,temp.cost));
					}
				}
			}
		}
		if(valid) {
			System.out.println(count);
		}else {
			System.out.println(-1);
		}
	}

	static class Point {
		int x, y;
		int cost;

		public Point(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

}
