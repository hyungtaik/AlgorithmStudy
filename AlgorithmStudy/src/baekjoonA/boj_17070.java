package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17070 {
	private static int N;
	private static int[][] map;
	private static int count;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[0][0] = true;
		visited[0][1] = true;
		count = 0;
		dfs(0,1,1);
		System.out.println(count);
	}

	static void dfs(int x, int y, int shape) {
		if (x == N-1 && y == N-1) {
			count++;
			return;
		} else {
			if (shape == 1) {
				int nx = x;
				int ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 1);
					visited[nx][ny] = false;
				}
				nx = x + 1;
				ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny - 1] == 0
						&& map[nx - 1][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 3);
					visited[nx][ny] = false;
				}
				

			} else if (shape == 2) {
				int nx = x + 1;
				int ny = y;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 2);
					visited[nx][ny] = false;
				}
				nx = x + 1;
				ny = y + 1;

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny - 1] == 0
						&& map[nx - 1][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 3);
					visited[nx][ny] = false;
				}
			} else {
				int nx = x;
				int ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 1);
					visited[nx][ny] = false;
				}
				nx = x + 1;
				ny = y;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 2);
					visited[nx][ny] = false;
				}
				nx = x + 1;
				ny = y + 1;
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 0 && map[nx][ny - 1] == 0
						&& map[nx - 1][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					dfs(nx, ny, 3);
					visited[nx][ny] = false;
				}
			}
		}
	}
}
