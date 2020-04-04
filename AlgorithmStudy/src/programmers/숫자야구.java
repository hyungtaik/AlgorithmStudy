package programmers;

import java.util.Arrays;

public class 숫자야구 {

	public static void main(String[] args) {
		int[][] arr = { { 123, 1, 1 }, { 356, 1, 0 }, { 327, 2, 0 }, { 489, 0, 1 } };
		System.out.println(solution(arr));
	}

	private static boolean[] visited;
	private static int[] numbers;
	private static int answer;

	public static int solution(int[][] baseball) {
		answer = 0;
		visited = new boolean[10];
		numbers = new int[3];
		perm(0, baseball);
		return answer;

	}

	static boolean[] nope; // 영구 제외

	static void perm(int count, int[][] baseball) {
		if (count == 3) {
			for (int i = 0; i < baseball.length; i++) { // 각 예상
				String temp = baseball[i][0] + "";
				int strike = 0;
				int ball = 0;
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						if ((numbers[j]) == ((int) temp.charAt(k) - 48)) {
							if (j == k) {
								strike++;
								break;
							} else {
								ball++;
								break;
							}
						}
					}
				}
				if (strike != baseball[i][1] || ball != baseball[i][2]) {
					return;
				}
			}
			answer++;
			return;
		}
		for (int i = 1; i <= 9; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			numbers[count] = i;
			perm(count + 1, baseball);
			visited[i] = false;
		}
	}
}
