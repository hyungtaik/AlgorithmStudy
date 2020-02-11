import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class swea_1258 {

	private static int TC;
	private static int n;
	private static int[][] map;
	private static boolean[][] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			check = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ArrayList<Point> list = new ArrayList<Point>();

			int cx = 0;
			int cy = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n - 1; j++) {
					if (check[i][j] == true)
						continue;
					if (map[i][j] != 0) {
						cy++;
						check[i][j] = true;

						if (map[i][j + 1] == 0) {
							for (int k = i; k < n; k++) {
								if (map[k][j] != 0) {
									Arrays.fill(check[k], j - cy + 1, j + 1, true);
									cx++;
								} else {
									break;
								}
							}
							list.add(new Point(cx, cy));
							cx = 0;
							cy = 0;
						}
					}
				}
			}
			Collections.sort(list);
			System.out.print("#" + tc + " " + list.size());
			for (Point p : list) {
				System.out.print(" " + p.x + " " + p.y);
			}
			System.out.println();
		}
	}

	static class Point implements Comparable<Point> {
		int x, y;

		@Override
		public int compareTo(Point o) {
			int first = this.x * this.y;
			int second = o.x * o.y;
			if (first == second) {
				return this.x - o.x;
			} else {
				return first - second;
			}

		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
