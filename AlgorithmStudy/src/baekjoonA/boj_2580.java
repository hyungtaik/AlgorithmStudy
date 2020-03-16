package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2580 {

	private static int[][] map;
	private static ArrayList<Pair> list;
	private static boolean check;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[9][9];
		list = new ArrayList<Pair>();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					list.add(new Pair(i, j));
				}
			}
		}
		check = false;
		sb = new StringBuilder();
		dfs(0);
	}

	static void dfs(int index) {
		if (index == list.size()) {
			check = true;
			show();
			System.out.println(sb.toString());
			return;
		}
		if (check)
			return;
		Pair temp = list.get(index);
		int x = temp.x;
		int y = temp.y;
		for (int i = 1; i < 10; i++) {
			if (!checkX(x, i) || !checkY(y, i) || !checkGroup(x, y, i))
				continue;
			map[x][y] = i;
			dfs(index + 1);
			map[x][y] = 0;
		}
	}

	static void show() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
	}

	static boolean checkX(int x, int index) {
		for (int j = 0; j < 9; j++) {
			if (map[x][j] > 0) {
				if (map[x][j] == index) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean checkY(int y, int index) {
		for (int j = 0; j < 9; j++) {
			if (map[j][y] > 0) {
				if (map[j][y] == index) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean checkGroup(int x, int y, int index) {
		int xlen = (x / 3) * 3;
		int ylen = (y / 3) * 3;
		for (int i = xlen; i < xlen + 3; i++) {
			for (int j = ylen; j < ylen + 3; j++) {
				if (map[i][j] > 0 && map[i][j] == index)
					return false;
			}
		}
		return true;
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
