import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_3109 {

	private static int C;
	private static int R;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1 };
	private static int[] dy = { 1, 1, 1 };
	private static int max;
	private static boolean[][] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		max = 0;
		for (int i = 0; i < R; i++) {
			String in = br.readLine();
			for (int j = 1; j < C - 1; j++) { // 양쪽 끝열 제외
				char ch = in.charAt(j);
				if (ch == 'x')
					map[i][j] = 1; // 건물 있을 경우 1, 그 외는 0
			}
		}
		for (int i = 0; i < R; i++) {
			if (solve(i, 0)) {
				max++;
			}
		}
		System.out.println(max);
	}

	// 시작 row, 열 도착 col
	static boolean solve(int row, int col) {
		if (col == C - 1) {// 열 도착
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nx = row + dx[i];
			int ny = col + dy[i];
			if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1)
				continue;
			if (map[nx][ny] == 1)
				continue;
			map[nx][ny] = 1;
			boolean result = solve(nx, ny);
			if (result) {
				return true;
			}
		}
		return false;

	}
}
