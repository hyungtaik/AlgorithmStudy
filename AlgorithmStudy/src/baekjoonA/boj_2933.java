package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션 + BFS
 * 
 * @see 백준 2933번: 미네랄 <br>
 *      메모리: 111076 KB <br>
 *      시간: 396 ms
 * @since 2020-09-02
 * 
 */

public class boj_2933 {

	private static int R;
	private static int C;
	private static char[][] map;
	private static int N;
	private static int[] command;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static LinkedList<Pair> q;
	private static boolean[][] visited;
	private static ArrayList<Pair> list;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		N = Integer.parseInt(br.readLine());
		command = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			command[i] = Integer.parseInt(st.nextToken());
		}
		sb = new StringBuilder();

		// 1. 왼쪽 -> 오른쪽 || 2. 오른쪽 -> 왼쪽
		for (int i = 0; i < N; i++) {
			int height = R - command[i];
			if (i % 2 == 0) { // 1. 왼쪽 -> 오른쪽
				int y = 0;
				while (true) {
					if (y > C - 1)
						break;
					if (map[height][y] == 'x') {
						// bfs 돌려서
						map[height][y] = '.';
						for (int dir = 0; dir < 4; dir++) {
							if (dir == 2)
								continue;
							int nx = height + dx[dir];
							int ny = y + dy[dir];
							if (nx<0||nx > R - 1 || ny < 0 || ny > C - 1)
								continue;
							if (map[nx][ny] == 'x') {
								if (bfs(nx, ny))
									continue;
								// 클러스터 아래로 내리기
								downCluster(nx,ny);
								break;
							}
						}
						break;
					}
					y += dy[3];
				}

			} else { // 2. 오른쪽 -> 왼쪽
				int y = C-1;
				while (true) {
					if (y < 0)
						break;
					if (map[height][y] == 'x') {
						// bfs 돌려서
						map[height][y] = '.';
						for (int dir = 0; dir < 4; dir++) {
							if (dir == 3)
								continue;
							int nx = height + dx[dir];
							int ny = y + dy[dir];
							if (nx<0||nx > R - 1 || ny < 0 || ny > C - 1)
								continue;
							if (map[nx][ny] == 'x') {
								if (bfs(nx, ny))
									continue;
								// 클러스터 아래로 내리기
								downCluster(nx,ny);
								break;
							}
						}
						break;
					}
					y += dy[2];
				}
			}
		}
		printMap();
		System.out.println(sb.toString());
	}
	
	static void printMap() {
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				sb.append(map[i][j]);
			}sb.append("\n");
		}
	}
	
	static void downCluster(int x,int y) {
		int count = 1;
		
		boolean[][] checked = new boolean[R][C];
		for(int i=0;i<list.size();i++) {
			Pair temp = list.get(i);
			checked[temp.x][temp.y] = true;
		}
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]=='.') {
					checked[i][j] = true;
				}
			}
		}
		
		Loop:while(true) {
			for(int i=0;i<list.size();i++) {
				Pair temp = list.get(i);
				int nx = temp.x +count;
				if(nx > R-1) {
					count--;
					break Loop;
				}
				
				if(!checked[nx][temp.y]) {
					count--;
					break Loop;
				}
			}
			count++;
		}
		for(int i=0;i<list.size();i++) {
			Pair temp = list.get(i);
			map[temp.x][temp.y] = '.';
		}
		for(int i=0;i<list.size();i++) {
			Pair temp = list.get(i);
			int nx = temp.x +count;
			map[nx][temp.y] = 'x';
		}
	}
	
	// 맨 바닥일 경우 true
	static boolean bfs(int x, int y) {
		q = new LinkedList<Pair>();
		visited = new boolean[R][C];
		q.add(new Pair(x, y));
		visited[x][y] = true;
		int minX = 0;
		list = new ArrayList<Pair>();
		list.add(new Pair(x,y));
		
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = temp.x + dx[dir];
				int ny = temp.y + dy[dir];
				if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1)
					continue;
				if (visited[nx][ny])
					continue;
				if(map[nx][ny] == 'x') {
					q.add(new Pair(nx, ny));
					list.add(new Pair(nx,ny));
					visited[nx][ny] = true;
					minX = Math.max(minX, nx);
				}
			}
		}
		if (minX == R - 1)
			return true;
		return false;
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
