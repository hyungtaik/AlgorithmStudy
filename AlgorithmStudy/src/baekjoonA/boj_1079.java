package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 브루트포스
 * 
 * @see 백준 1079번: 마피아 <br>
 *      메모리: 19288 KB <br>
 *      시간: 264 ms
 * @since 2020-10-13
 * 
 */

public class boj_1079 {

	private static int N;
	private static int[] person;
	private static int[][] reaction;
	private static int num;
	private static boolean[] live;
	private static int max;
	private static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		person = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		live = new boolean[N];

		reaction = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				reaction[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken()); // 은진이 번호
		int count = N;
		max = Integer.MIN_VALUE;
		flag = false;
		if (N == 1)
			System.out.println(0);
		else {
			dfs(count, 0);
			System.out.println(max);
		}
	}

	static void dfs(int index, int count) {
		if (flag)
			return;
		if (live[num] || index==1) {
			max = Math.max(max, count);
			if(index == 1) flag = true;
			return;
		}

		if (index % 2 == 0) { // 밤
			for (int i = 0; i < N; i++) { // 랜덤으로 골라서 죽이기
				if (flag)
					return;
				if (i == num)
					continue;
				if (live[i])
					continue;
				checkNight(i);
				dfs(index - 1, count+1);
				uncheckNight(i);
			}
		} else { // 낮
			int temp = checkAfternoon();
			if(temp == N) {
				max = Math.max(max,count);
				uncheckAfternoon(temp);
				return;
			}
			dfs(index - 1, count);
			uncheckAfternoon(temp);

		}
	}

	// 유죄 점수 확인 - 밤
	static void checkNight(int n) {
		live[n] = true;
		for (int i = 0; i < N; i++) {
			if (live[i])
				continue;
			person[i] += reaction[n][i];
		}
	}

	static void uncheckNight(int n) {
		for (int i = 0; i < N; i++) {
			if (live[i])
				continue;
			person[i] -= reaction[n][i];
		}
		live[n] = false;
	}

	// 낮
	static int checkAfternoon() {
		int score = Integer.MIN_VALUE;
		int index = -1;
		for (int i = 0; i < N; i++) {
			if (live[i])
				continue;
			if (score < person[i]) {
				score = person[i];
				index = i;
			}
		}
		live[index] = true;
		return index;
	}

	static void uncheckAfternoon(int n) {
		live[n] = false;
	}

}
