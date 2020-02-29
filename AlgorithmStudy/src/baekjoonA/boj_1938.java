package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_1938 {

	private static int N;
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Tree start;
	private static Tree end;
	private static boolean[][][] visited;
	private static LinkedList<Tree> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int count = 0;
		int eCount = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				char temp = input.charAt(j);
				if (temp == 'B') {
					count++;
					map[i][j] = 2; // 시작
					if (count == 2) {
						if (i - 1 >= 0 && i - 1 <= N - 1 && map[i - 1][j] == 2)
							start = new Tree(i, j, 1, 0); // 세로
						else
							start = new Tree(i, j, 0, 0); // 가로
					}
				} else if (temp == 'E') {
					eCount++;
					map[i][j] = 3; // 도착
					if (eCount == 2) {
						if (i - 1 >= 0 && i - 1 <= N - 1 && map[i - 1][j] == 3)
							end = new Tree(i, j, 1, 0); // 세로
						else
							end = new Tree(i, j, 0, 0); // 가로
					}
				} else if (temp == '1')
					map[i][j] = 1;
				else
					map[i][j] = 0;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2 || map[i][j] == 3) {
					map[i][j] = 0;
				}
			}
		}

		visited = new boolean[N][N][2];
		visited[start.x][start.y][start.dir] = true;
		q = new LinkedList<Tree>();
		q.add(start);
		int result = bfs();

		System.out.println(result);
	}

	static int bfs() {
		while (!q.isEmpty()) {
			Tree temp = q.poll();
			int x = temp.x;
			int y = temp.y;
			int dir = temp.dir;
			int count = temp.count;
			if (x == end.x && y == end.y && dir == end.dir) {
				return count;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (dir == 0) { // 가로일때
					if (nx < 0 || nx > N - 1 || ny - 1 < 0 || ny + 1 > N - 1)
						continue;
					if (visited[nx][ny][dir])
						continue;
					if (map[nx][ny - 1] == 1 || map[nx][ny] == 1 || map[nx][ny + 1] == 1)
						continue;
					visited[nx][ny][dir] = true;
					q.add(new Tree(nx, ny, dir, count + 1));
				} else { // 세로일떄
					if (nx - 1 < 0 || nx + 1 > N - 1 || ny < 0 || ny > N - 1)
						continue;
					if (visited[nx][ny][dir])
						continue;
					if (map[nx - 1][ny] == 1 || map[nx][ny] == 1 || map[nx + 1][ny] == 1)
						continue;
					visited[nx][ny][dir] = true;
					q.add(new Tree(nx, ny, dir, count + 1));
				}
			}
			if (x - 1 < 0 || y - 1 < 0 || x + 1 > N - 1 || y + 1 > N - 1)
				continue;
			if(visited[x][y][dir^1]) continue;
			
			if ( dir == 0 && map[x - 1][y - 1] == 0 && map[x - 1][y] == 0 && map[x - 1][y + 1] == 0
					&& map[x + 1][y - 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
				visited[x][y][1] = true;
				q.add(new Tree(x, y, 1, count + 1));
			} else if (dir == 1 && map[x - 1][y - 1] == 0 && map[x][y - 1] == 0
					&& map[x + 1][y - 1] == 0 && map[x - 1][y + 1] == 0 && map[x][y + 1] == 0
					&& map[x + 1][y + 1] == 0) {
				visited[x][y][0] = true;
				q.add(new Tree(x, y, 0, count + 1));
			}
		}
		return 0;
	}

	static class Tree {
		int x, y, dir, count; // dir = 0: 가로 / 1: 세로

		public Tree(int x, int y, int dir, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.count = count;
		}

	}
}
