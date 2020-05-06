package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_3954 {

	private static int TC;
	private static int m;
	private static int cc;
	private static int ii;
	private static int[] input;
	private static char[] comm;
	private static char[] arr;
	private static Stack<Integer> stack;
	private static Pair[] pair;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 메모리 크기
			cc = Integer.parseInt(st.nextToken()); // 프로그램 코드의 크기
			ii = Integer.parseInt(st.nextToken()); // 입력의 크기

			input = new int[m];
			comm = new char[cc];
			arr = new char[ii];
			String temp = br.readLine();
			for (int i = 0; i < cc; i++) { // 명령어
				comm[i] = temp.charAt(i);
			}
			stack = new Stack<Integer>();
			pair = new Pair[cc];

			for (int i = 0; i < cc; i++) { // 명령어 [] 검사
				if (comm[i] == '[') {
					stack.push(i);
					pair[i] = new Pair(i, 0);
				} else if (comm[i] == ']') {
					int tmp = stack.pop();
					pair[i] = new Pair(tmp, i);
					pair[tmp].right = i;
				}
			}

			temp = br.readLine();
			for (int i = 0; i < ii; i++) { // 문자
				arr[i] = temp.charAt(i);
			}
			solve();

		}
	}

	static void solve() {
		int pointer = 0; // 포인터
		int index = 0; // 명령어 수행 인덱스
		int loop = 0; // 루프 몇번 도는지
		int j = 0; // 문자열 입력하기 위한 인덱스
		int max = 0; // 루프 안에 갇힌 인덱스 좌우값 출력을 위한 인덱스
		while (true) {
			char c = comm[index];
			if (c == '+') {
				input[pointer]++;
				if (input[pointer] == 256) {
					input[pointer] = 0;
				}
			} else if (c == '-') {
				input[pointer]--;
				if (input[pointer] == -1) {
					input[pointer] = 255;
				}
			} else if (c == '<') {
				pointer--;
				if (pointer == -1) {
					pointer = m - 1;
				}
			} else if (c == '>') {
				pointer++;
				if (pointer == m) {
					pointer = 0;
				}
			} else if (c == '[') {
				if (input[pointer] == 0) {
					index = pair[index].right;
					index--;
					loop++;
				}
			} else if (c == ']') {
				if (input[pointer] != 0) {
					index = pair[index].left;
					index--;
					loop++;
				}
			} else if (c == '.') {
//				System.out.println();
			} else if (c == ',') {
				if (j == ii) {
					input[pointer] = 255;
				} else {

					input[pointer] = arr[j++];
				}
			}
			index++;
			loop++;

			if (index > max) {
				max = index;
			}

			if (index == cc) {
				System.out.println("Terminates");
				break;
			}

			if (loop > 50000000) {
				System.out.println("Loops " + pair[max].left + " " + pair[max].right);
				break;
			}
		}
	}

	// [ ] 의 짝 검사(오른쪽 왼쪽)
	static class Pair {
		int left, right;

		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
}
