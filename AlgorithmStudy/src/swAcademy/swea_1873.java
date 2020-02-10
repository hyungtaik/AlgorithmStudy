import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1873 {

	private static int W;
	private static int H;
	private static int TC;
	private static Character[][] map;
	private static int N;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Tank tank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new Character[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String temp = st.nextToken();
				for (int j = 0; j < W; j++) {
					map[i][j] = temp.charAt(j);
					if (map[i][j] == '<') {
						tank = new Tank(i, j, 2);
					} else if (map[i][j] == '>') {
						tank = new Tank(i, j, 3);
					} else if (map[i][j] == '^') {
						tank = new Tank(i, j, 0);
					} else if (map[i][j] == 'v') {
						tank = new Tank(i, j, 1);
					}

				}
			}
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			String temp = st.nextToken();
			for (int i = 0; i < N; i++) {
				char dir = temp.charAt(i);
//				System.out.println(i+"   %%");
				solve(dir);

			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static void solve(char dir) { // 명령수행

		switch (dir) {
		case ('S'):
			if (tank.dir == 0) { // 상
				for (int i = tank.x - 1; i >= 0; i--) {
					if(map[i][tank.y] == '#') break;
					if (map[i][tank.y] == '*') {
						map[i][tank.y] = '.';
						break;
					}
				}
			} else if (tank.dir == 1) { // 하
				for (int i = tank.x + 1; i < H; i++) {
					if(map[i][tank.y] == '#') break;
					if (map[i][tank.y] == '*') {
						map[i][tank.y] = '.';
						break;
					}
				}
			} else if (tank.dir == 2) {
				for (int i = tank.y - 1; i >= 0; i--) {
					if(map[tank.x][i] == '#') break;
					if (map[tank.x][i] == '*') {
						map[tank.x][i] = '.';
						break;
					}
				}
			} else if (tank.dir == 3) {
				for (int i = tank.y + 1; i < W; i++) {
					if(map[tank.x][i] == '#') break;
					if (map[tank.x][i] == '*') {
						map[tank.x][i] = '.';
						break;
					}
				}
			}
			break;
		case ('U'):
			tank.dir = 0;
			int nx = tank.x + dx[0];
			int ny = tank.y + dy[0];
			if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1) {
				map[tank.x][tank.y] = '^';
				break;
			}
			if (map[tank.x + dx[0]][tank.y + dy[0]] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x += dx[0];
				tank.y += dy[0];
			}
			map[tank.x][tank.y] = '^';

			break;
		case ('D'):
			tank.dir = 1;
			nx = tank.x + dx[1];
			ny = tank.y + dy[1];
			if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1) {
				map[tank.x][tank.y] = 'v';
				break;
			}
			if (map[tank.x + dx[1]][tank.y + dy[1]] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x += dx[1];
				tank.y += dy[1];
			}
			map[tank.x][tank.y] = 'v';
			break;
		case ('L'):
			tank.dir = 2;
			nx = tank.x + dx[2];
			ny = tank.y + dy[2];
			if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1) {
				map[tank.x][tank.y] = '<';
				break;
			}
			if (map[tank.x + dx[2]][tank.y + dy[2]] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x += dx[2];
				tank.y += dy[2];
			}
			map[tank.x][tank.y] = '<';
			break;
		case ('R'):
			tank.dir = 3;
			nx = tank.x + dx[3];
			ny = tank.y + dy[3];
			if (nx < 0 || nx > H - 1 || ny < 0 || ny > W - 1) {
				map[tank.x][tank.y] = '>';
				break;
			}
			if (map[tank.x + dx[3]][tank.y + dy[3]] == '.') {
				map[tank.x][tank.y] = '.';
				tank.x += dx[3];
				tank.y += dy[3];
			}
			map[tank.x][tank.y] = '>';
			break;

		}
	}

	static class Tank {
		int x, y;
		int dir;

		public Tank(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}
}
