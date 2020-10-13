package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category ���Ʈ����
 * 
 * @see ���� 1079��: ���Ǿ� <br>
 *      �޸�: 19288 KB <br>
 *      �ð�: 264 ms
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
		num = Integer.parseInt(st.nextToken()); // ������ ��ȣ
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

		if (index % 2 == 0) { // ��
			for (int i = 0; i < N; i++) { // �������� ��� ���̱�
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
		} else { // ��
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

	// ���� ���� Ȯ�� - ��
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

	// ��
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
