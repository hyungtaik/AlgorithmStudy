package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS + 시뮬레이션
 * 
 * @see 백준 19238번: 스타트 택시 <br>
 *      메모리: 22960 KB <br>
 *      시간: 144 ms
 * @since 2020-09-07
 * 
 */

public class boj_19238 {

	private static int N;
	private static int M;
	private static int fuel;
	private static int[][] map;
	private static int x;
	private static int y;
	private static LinkedList<Person> customer;
	private static LinkedList<Person> q;
	private static boolean[][] visited;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		q = new LinkedList<Person>();
		q.add(new Person(x, y, -1, -1));

		customer = new LinkedList<Person>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int curX = Integer.parseInt(st.nextToken())-1;
			int curY = Integer.parseInt(st.nextToken())-1;
			int destX = Integer.parseInt(st.nextToken())-1;
			int destY = Integer.parseInt(st.nextToken())-1;

			customer.add(new Person(curX, curY, destX, destY));
			map[curX][curY] = 2;
		}
		System.out.println(bfs());
	}

	static int bfs() {
		while(true) {
			visited = new boolean[N][N];
			visited[x][y] = true;
			int tx = -1;
			int ty = -1;
			if(customer.size() == 0) break;
			
			// 가까운 승객 찾기
			while (!q.isEmpty()) {
				int len = q.size();
				tx = -1;
				ty = -1;
				
				if (fuel <= 0)
					return -1;
				int tempX = q.peek().curX;
				int tempY = q.peek().curY;
				if(map[tempX][tempY]==2) {
					tx = tempX;
					ty = tempY;
					map[tx][ty] = 0;
					q.clear();
					break;
				}else {
					for (int l = 0; l < len; l++) {
						Person taxi = q.poll();
						
						for (int dir = 0; dir < 4; dir++) {
							int nx = taxi.curX + dx[dir];
							int ny = taxi.curY + dy[dir];
							if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || map[nx][ny] == 1)
								continue;
							if (visited[nx][ny])
								continue;

							if (map[nx][ny] == 2) {
								if (tx == -1) {
									tx = nx;
									ty = ny;
								} else {
									if (tx > nx) {
										tx = nx;
										ty = ny;
									} else if (tx == nx && ty > ny) {
										tx = nx;
										ty = ny;
									}
								}
							} else {
								visited[nx][ny] = true;
								q.add(new Person(nx, ny, -1, -1));
							}
						}
					}
					fuel--;
					if (tx != -1) {
						map[tx][ty] = 0;
						q.clear();
						break;
					}
				}
			}
			if(tx == -1) return -1;
			// 목적지 찾기
			int len = customer.size();
			for (int l = 0; l < len; l++) {
				Person temp = customer.poll();
				if (temp.curX == tx && temp.curY == ty) {
					q.add(new Person(tx, ty, temp.destX, temp.destY));
					break;
				}
				customer.add(temp);
			}
			// 목적지로 이동
			int destinationX = q.peek().destX;
			int destinationY = q.peek().destY;
			int cnt = 0;
			visited = new boolean[N][N];
			visited[tx][ty] = true;
			L:while(!q.isEmpty()) {
				len = q.size();
				cnt++;
				
				if(fuel<=0) return -1;
				for (int l = 0; l < len; l++) {
					Person taxi = q.poll();
					if (fuel <= 0)
						return -1;

					for (int dir = 0; dir < 4; dir++) {
						int nx = taxi.curX + dx[dir];
						int ny = taxi.curY + dy[dir];
						if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || map[nx][ny] == 1)
							continue;
						if (visited[nx][ny])
							continue;
						visited[nx][ny] = true;
						if(nx == destinationX && ny == destinationY) {
							q.clear();
							q.add(new Person(nx,ny,-1,-1));
							x = nx;
							y = ny;
							fuel += cnt*2-1;
							break L;
						}
						q.add(new Person(nx,ny,-1,-1));
					}
				}
				fuel--;
			}
		}
		return fuel;

	}

	static class Person {
		int curX, curY;
		int destX, destY;

		public Person(int curX, int curY, int destX, int destY) {
			super();
			this.curX = curX;
			this.curY = curY;
			this.destX = destX;
			this.destY = destY;
		}

	}
}
