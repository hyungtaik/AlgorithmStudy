package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DFS(백트래킹)
 * 
 * @see 백준 9944번: NxM 보드 완주하기 <br>
 *      메모리: 16564 KB <br>
 *      시간: 324 ms
 * @since 2020-09-06
 * 
 */

public class boj_9944 {

	private static int N;
	private static int M;
	private static char[][] map;
	private static int min;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = 1;
		String input = null;
		while ((input = br.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(input);

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				input = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == '.')
						cnt++;
				}
			}
			min = Integer.MAX_VALUE;
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '.') {
						visited[i][j] = true;
						dfs(i, j, 0, 1);
						visited[i][j] = false;
					}
				}
			}
			if (min == Integer.MAX_VALUE)
				min = -1;
			System.out.println("Case "+testcase + ": " + min);
			testcase++;
		}
	}

	static void dfs(int x, int y, int count, int index) {

		if (min <= count) {
			return;
		}
		if (index == cnt) { // 모든 칸을 채운 경우
			min = Math.min(min, count);
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			int nx = x;
			int ny = y;
			int go = 0;
			
			while(true) {
				int nnx = nx+dx[dir];
				int nny = ny+dy[dir];
				if (nnx < 0 || nnx > N - 1 || nny < 0 || nny > M - 1)
					break;
				
				if (map[nnx][nny] == '*' || visited[nnx][nny])
					break;
				
				visited[nnx][nny] = true;
				go++;
				nx = nnx;
				ny = nny;
			}
			
			if(nx == x && ny == y) continue;
			
			dfs(nx,ny,count+1,index+go);
			
			for(int i=0;i<go;i++) {
				visited[nx][ny] = false;
				nx = nx - dx[dir];
				ny = ny - dy[dir];
			}
		}
	}
}
