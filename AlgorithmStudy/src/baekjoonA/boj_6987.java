package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_6987 {

	private static int[][] map;
	private static boolean check;
	private static Pair[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[6][3];
		for (int k = 0; k < 4; k++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					sum += map[i][j];
				}
			}
			// 승패 수가 같아야 하고 무승부도 짝수여야한다. 한 국가당 5경기여야한다.
			if (sum == 30) {
				int victory = 0;
				int defeat = 0;
				int draw = 0;
				for (int i = 0; i < 6; i++) {
					int ssum = map[i][0] + map[i][1] + map[i][2];
					victory += map[i][0];
					defeat += map[i][2];
					draw += map[i][1];
					if (ssum != 5) {
						System.out.print(0 + " ");
						continue;
					}
				}
				if (victory != defeat)
					System.out.print(0 + " ");
				else if (draw % 2 != 0)
					System.out.print(0 + " ");
				else {
					check = false;
					arr = combi();
					solve(victory, defeat, draw, 0);
					if (check) {
						System.out.print(1 + " ");
					} else {
						System.out.print(0 + " ");
					}
				}
			} else {
				System.out.print(0 + " ");
			}
		}
	}

	static Pair[] combi() {
		Pair[] temp = new Pair[15];
		for (int i = 0, index = 0; i < 6; i++) {
			for (int j = i+1; j < 6; j++) {
				temp[index++] = new Pair(i, j);
			}
		}
		return temp;
	}

	static void solve(int victory, int defeat, int draw, int game) {
		if (check)
			return;
		if (game == 15) {
			check = true;
			return;
		}
		int i = arr[game].x;
		int j = arr[game].y;

		// 0열: 승리   2열: 패배
		
		// 승리
		if (victory > 0) {
			if (map[j][2] > 0 && map[i][0] > 0) {
				map[j][2]--;
				map[i][0]--;
				solve(victory - 1, defeat - 1, draw, game + 1);
				map[j][2]++;
				map[i][0]++;
			}
		}
		// 패배
		if (defeat > 0) {
			if (map[i][2] > 0 && map[j][0] > 0) {
				map[i][2]--;
				map[j][0]--;
				solve(victory - 1, defeat - 1, draw, game + 1);
				map[j][0]++;
				map[i][2]++;
			}
		}
		// 무승부
		if (draw > 0) {
			if (map[i][1] > 0 && map[j][1] > 0) {
				map[j][1]--;
				map[i][1]--;
				solve(victory, defeat, draw - 2, game + 1);
				map[j][1]++;
				map[i][1]++;
			}
		}
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
