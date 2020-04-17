package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_5373 {
	private static int TC;
	private static int n;
	private static LinkedList<Problem> q;
	private static StringBuilder sb;
	private static char[][][] cube;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		q = new LinkedList<Problem>();
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {

				String temp = st.nextToken();
				q.add(new Problem(temp.charAt(0), temp.charAt(1)));
			}
			cube = new char[6][3][3];
			init();

			while (!q.isEmpty()) {
				Problem temp = q.poll();
				rotateCube(temp.loc, temp.dir);
			}
			sb = new StringBuilder();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(cube[0][i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}

	// 추후 알게 된 사실: 반시계는 시계방향 3번 돌리면 된다는 사실.....
	private static void rotateCube(char loc, char dir) {
		if (loc == 'U') {
			rotate(0, dir);
			UR(dir);
		}

		else if (loc == 'D') {
			rotate(1, dir);
			DR(dir);
		}

		else if (loc == 'F') {
			rotate(2, dir);
			FR(dir);
		}

		else if (loc == 'B') {
			rotate(3, dir);
			BR(dir);
		}

		else if (loc == 'L') {
			rotate(4, dir);
			LR(dir);
		}

		else if (loc == 'R') {
			rotate(5, dir);
			RR(dir);
		}
	}

	// 해당 인덱스의 큐브를 시계 / 반시계 돌린다.
	private static void rotate(int index, char dir) {
		char[][] temp = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp[i][j] = cube[index][i][j];
			}
		}
		if (dir == '+') {
			int row = 0, col = 0;
			for (int i = 2; i >= 0; i--) {
				for (int j = 0; j < 3; j++) {
					cube[index][j][i] = temp[row][col];
					col++;
				}
				row++;
				col = 0;
			}
		} else {
			int row = 0, col = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 2; j >= 0; j--) {
					cube[index][j][i] = temp[row][col];
					col++;
				}
				row++;
				col = 0;
			}
		}
	}

	// 0:U, 1:D, 2:F, 3:B, 4:L, 5:R
	// 전개도 그려서 이동하는 부분을 체크해서 이동
	
	// U Rotate
	private static void UR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[2][0][i];
			for (int i = 0; i < 3; i++)
				cube[2][0][i] = cube[5][0][i];
			for (int i = 0; i < 3; i++)
				cube[5][0][i] = cube[3][2][2 - i];
			for (int i = 0; i < 3; i++)
				cube[3][2][2 - i] = cube[4][0][i];
			for (int i = 0; i < 3; i++)
				cube[4][0][i] = temp[i];
		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[2][0][i];
			for (int i = 0; i < 3; i++)
				cube[2][0][i] = cube[4][0][i];
			for (int i = 0; i < 3; i++)
				cube[4][0][i] = cube[3][2][2 - i];
			for (int i = 0; i < 3; i++)
				cube[3][2][2 - i] = cube[5][0][i];
			for (int i = 0; i < 3; i++)
				cube[5][0][i] = temp[i];
		}
	}
	// D Rotate
	private static void DR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[2][2][i];
			for (int i = 0; i < 3; i++)
				cube[2][2][i] = cube[4][2][i];
			for (int i = 0; i < 3; i++)
				cube[4][2][i] = cube[3][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[3][0][2 - i] = cube[5][2][i];
			for (int i = 0; i < 3; i++)
				cube[5][2][i] = temp[i];
		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[2][2][i];
			for (int i = 0; i < 3; i++)
				cube[2][2][i] = cube[5][2][i];
			for (int i = 0; i < 3; i++)
				cube[5][2][i] = cube[3][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[3][0][2 - i] = cube[4][2][i];
			for (int i = 0; i < 3; i++)
				cube[4][2][i] = temp[i];
		}
	}
	// F Rotate
	private static void FR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][2][i];
			for (int i = 0; i < 3; i++)
				cube[0][2][i] = cube[4][2 - i][2];
			for (int i = 0; i < 3; i++)
				cube[4][2 - i][2] = cube[1][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[1][0][2 - i] = cube[5][i][0];
			for (int i = 0; i < 3; i++)
				cube[5][i][0] = temp[i];
		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][2][i];
			for (int i = 0; i < 3; i++)
				cube[0][2][i] = cube[5][i][0];
			for (int i = 0; i < 3; i++)
				cube[5][i][0] = cube[1][0][2 - i];
			for (int i = 0; i < 3; i++)
				cube[1][0][2 - i] = cube[4][2 - i][2];
			for (int i = 0; i < 3; i++)
				cube[4][2 - i][2] = temp[i];
		}
	}
	// B Rotate
	private static void BR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][0][i];
			for (int i = 0; i < 3; i++)
				cube[0][0][i] = cube[5][i][2];
			for (int i = 0; i < 3; i++)
				cube[5][i][2] = cube[1][2][2 - i];
			for (int i = 0; i < 3; i++)
				cube[1][2][2 - i] = cube[4][2 - i][0];
			for (int i = 0; i < 3; i++)
				cube[4][2 - i][0] = temp[i];
		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][0][i];
			for (int i = 0; i < 3; i++)
				cube[0][0][i] = cube[4][2 - i][0];
			for (int i = 0; i < 3; i++)
				cube[4][2 - i][0] = cube[1][2][2 - i];
			for (int i = 0; i < 3; i++)
				cube[1][2][2 - i] = cube[5][i][2];
			for (int i = 0; i < 3; i++)
				cube[5][i][2] = temp[i];
		}
	}
	// L Rotate
	private static void LR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][i][0];
			for (int i = 0; i < 3; i++)
				cube[0][i][0] = cube[3][i][0];
			for (int i = 0; i < 3; i++)
				cube[3][i][0] = cube[1][i][0];
			for (int i = 0; i < 3; i++)
				cube[1][i][0] = cube[2][i][0];
			for (int i = 0; i < 3; i++)
				cube[2][i][0] = temp[i];

		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][i][0];
			for (int i = 0; i < 3; i++)
				cube[0][i][0] = cube[2][i][0];
			for (int i = 0; i < 3; i++)
				cube[2][i][0] = cube[1][i][0];
			for (int i = 0; i < 3; i++)
				cube[1][i][0] = cube[3][i][0];
			for (int i = 0; i < 3; i++)
				cube[3][i][0] = temp[i];

		}
	}
	// R Rotate
	private static void RR(char dir) {
		char[] temp = new char[3];
		if (dir == '+') {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][i][2];
			for (int i = 0; i < 3; i++)
				cube[0][i][2] = cube[2][i][2];
			for (int i = 0; i < 3; i++)
				cube[2][i][2] = cube[1][i][2];
			for (int i = 0; i < 3; i++)
				cube[1][i][2] = cube[3][i][2];
			for (int i = 0; i < 3; i++)
				cube[3][i][2] = temp[i];

		} else {
			for (int i = 0; i < 3; i++)
				temp[i] = cube[0][i][2];
			for (int i = 0; i < 3; i++)
				cube[0][i][2] = cube[3][i][2];
			for (int i = 0; i < 3; i++)
				cube[3][i][2] = cube[1][i][2];
			for (int i = 0; i < 3; i++)
				cube[1][i][2] = cube[2][i][2];
			for (int i = 0; i < 3; i++)
				cube[2][i][2] = temp[i];
		}
	}

	private static void init() {
		char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' };

		for (int c = 0; c < 6; c++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cube[c][i][j] = color[c];
				}
			}
		}
	}

	static class Problem {
		char loc, dir; // 위치, 방향

		public Problem(char loc, char dir) {
			super();
			this.loc = loc;
			this.dir = dir;
		}
	}
}
