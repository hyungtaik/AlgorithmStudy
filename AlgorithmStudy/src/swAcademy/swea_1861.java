import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1861 {

	private static int TC;
	private static int N;
	private static int[][] map;
	private static boolean[][] check;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int max;
	private static int resultX;
	private static boolean checking;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			max = Integer.MIN_VALUE;
			resultX = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					checking = false;
					check = new boolean[N][N];
				
					solve(i, j, 1,map[i][j]);
				}
			}

			System.out.println("#" + tc + " " + resultX + " " + max);
		}
	}

	static void solve(int x, int y, int index,int num) {
		check[x][y] = true;
		if (index >= max) {
			if(index == max) resultX = Math.min(resultX, num);
			else {
				max = index;
				resultX = num;
			}
			
		}
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1)
				continue;
			if (check[nx][ny] == true)
				continue;
			if (map[nx][ny] - map[x][y] == 1) {
				solve(nx, ny, index + 1,num);
				check[nx][ny] = false;
			}
		}

	}
	

}
