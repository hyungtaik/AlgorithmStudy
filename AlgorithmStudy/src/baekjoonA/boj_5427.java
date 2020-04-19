package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_5427 {

	private static int TC;
	private static int w;
	private static int h;
	private static char[][] map;
	private static LinkedList<Fire> fire;
	private static LinkedList<Fire> person;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int result;
	private static boolean[][] checked;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new char[h][w];
			fire = new LinkedList<Fire>();
			person = new LinkedList<Fire>();
			checked = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				String temp = st.nextToken();
				for (int j = 0; j < w; j++) {
					map[i][j] = temp.charAt(j);
					if (map[i][j] == '*') {
						fire.add(new Fire(i, j));
					}
					else if (map[i][j] == '@') {
						person.add(new Fire(i, j));
						checked[i][j] = true;
					}
				}
			}
			result = 0;
			solve();
			if(result == 0) {
				sb.append("IMPOSSIBLE").append("\n");
			}else {
				sb.append(result).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	static void solve() {
		int count = 0;
		while (!person.isEmpty()) {
			int len = person.size();
			if(len == 0) return;
			int flen = fire.size();
			for (int i = 0; i <flen; i++) {
				Fire temp = fire.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1)
						continue;
					if (map[nx][ny] == '.' || map[nx][ny] == '@') {
						fire.add(new Fire(nx, ny));
						map[nx][ny] = '*';

					}
				}
			}
			for (int i = 0; i < len; i++) {
				Fire temp = person.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nx = temp.x + dx[dir];
					int ny = temp.y + dy[dir];
					if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1) {
						result = count+1;
						return;
					}
					if(checked[nx][ny]) continue;
					if (map[nx][ny] == '.') {
						person.add(new Fire(nx, ny));
						checked[nx][ny] = true;
					}
				}
			}
			if(person.isEmpty()) return;
			count++;


		}
		
	}

	static class Fire {
		int x, y;

		public Fire(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
