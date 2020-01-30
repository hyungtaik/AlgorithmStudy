package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea_1215 {

	static int plen; // 회문의 길이
	static Character[][] map; // map
	static int result; // 정답

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			result = 0;
			plen = Integer.parseInt(st.nextToken());
			map = new Character[8][8];
			for (int i = 0; i < 8; i++) {
				st = new StringTokenizer(br.readLine());
				String temp = st.nextToken();
				for (int j = 0; j < 8; j++) {
					map[i][j] = temp.charAt(j);
				}
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					solve(i, j);
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	// 아래 오른쪽만 검사
	// 배열 범위 넘어가는 코드 제약조건 추가할 것
	// 홀수일 경우 제약
	static void solve(int i, int j) {
		Stack<Character> temp = new Stack<>();
		int len = plen / 2;
		// 행
		if(i+plen-1<8) {
			for (int p = i; p < i + len; p++) {
				temp.push(map[p][j]);
			}
			if(plen%2 == 0) {
				if(isOK(temp, i + len, j, len, 1)) {
					result++;
				}
			}else {
				if(isOK(temp, i + len+1, j, len, 1)) {
					result++;
				}
			}
			
			temp.clear();
			// 열
		}
		if(j+plen-1<8) {
			for (int p = j; p < j + len; p++) {
				temp.push(map[i][p]);
			}
			if(plen%2 == 0) {
				if(isOK(temp, i, j + len, len, 2)) {
					result++;
				}	
			}else {
				if(isOK(temp, i, j + len+1, len, 2)) {
					result++;
				}
			}
			
		}
		
	}

	// case 1: 행, case 2: 열
	static boolean isOK(Stack<Character> temp, int i, int j, int len, int caseN) {
		if (caseN == 1) {
			for (int x = i; x < i + len; x++) {
				if (temp.pop() != map[x][j]) {
					return false;
				}
			}
		} else {
			for (int y = j; y < j + len; y++) {
				if (temp.pop() != map[i][y]) {
					return false;
				}
			}
		}
		return true;
	}

}
