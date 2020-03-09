package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3378_스타일리쉬들여쓰기_김형택 {

	private static int TC;
	private static int p;
	private static int q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#").append(tc);

			st = new StringTokenizer(br.readLine(), " ");

			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			int[][] arr = new int[p][7]; // ( ) { } [ ] .
			for (int i = 0; i < p; i++) {
				copy(arr, i);

				String s = br.readLine();

				int index = 0;
				while (s.charAt(index) == '.') {
					index++;
				}

				arr[i][6] = index;

				for (int j = index; j < s.length(); j++) {
					char temp = s.charAt(j);
					if (temp != '.') {
						if (temp == '(') {
							arr[i][0]++;
						} else if (temp == ')') {
							arr[i][1]++;
						} else if (temp == '{') {
							arr[i][2]++;
						} else if (temp == '}') {
							arr[i][3]++;
						} else if (temp == '[') {
							arr[i][4]++;
						} else if (temp == ']') {
							arr[i][5]++;
						}
					}
				}
			}

			int[][] user = new int[q][7]; // ( ) { } [ ] .

			for (int i = 0; i < q; i++) {
				copy(user, i);
				String s = br.readLine();

				for (int j = 0; j < s.length(); j++) {
					char temp = s.charAt(j);
					if (temp == '(') {
						user[i][0]++;
					} else if (temp == ')') {
						user[i][1]++;
					} else if (temp == '{') {
						user[i][2]++;
					} else if (temp == '}') {
						user[i][3]++;
					} else if (temp == '[') {
						user[i][4]++;
					} else if (temp == ']') {
						user[i][5]++;
					}
				}
			}

			for (int i = 1; i < q; i++) {
				user[i][6] = -2;
			}

			// 모든 순열 넣어보기
			for (int r = 1; r <= 20; r++) {
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						if (check(r, c, s, arr)) { // 값이 되는 경우
							calc(r, c, s, user); // 계산
						}
					}
				}
			}

			for (int i = 0; i < q; i++) {
				sb.append(" ").append(user[i][6]);
			}

			sb.append("\n");
		}
		System.out.print(sb);
	}

	public static void calc(int r, int c, int s, int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			int x = r * (arr[i - 1][0] - arr[i - 1][1]) + c * (arr[i - 1][2] - arr[i - 1][3]) + s * (arr[i - 1][4] - arr[i - 1][5]);
			if (arr[i][6] == -2) { // 처음 진입
				arr[i][6] = x;
			} 
			else if(arr[i][6] !=x) { // 이미 있는 값이 다르다면 -1
				arr[i][6] = -1;
			}

		}
	}

	public static boolean check(int r, int c, int s, int[][] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i][6] != r * (arr[i - 1][0] - arr[i - 1][1]) + c * (arr[i - 1][2] - arr[i - 1][3])
					+ s * (arr[i - 1][4] - arr[i - 1][5])) {
				return false;
			}
		}
		return true;
	}

	public static void copy(int[][] arr, int i) { // 괄호 수 갱신
		if (i == 0)
			return;
		for (int j = 0; j < 6; j++) {
			arr[i][j] = arr[i - 1][j];
		}
	}
}
