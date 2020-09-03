package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * @author TAEK
 * @category DFS (백트래킹)
 * 
 * @see 백준 3967번: 매직 스타 <br>
 *      메모리: 15280 KB <br>
 *      시간: 360 ms
 * @since 2020-09-03
 * 
 */

public class boj_3967 {

	private static int[] list;
	private static int totalCnt;
	private static boolean[] visited;
	private static int[] result;
	private static StringBuilder sb;
	private static boolean ok;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		list = new int[12];
		result = new int[12];
		visited = new boolean[13];
		int count = 0;
		totalCnt = 0;
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				char temp = input.charAt(j);
				if (temp == '.')
					continue;
				if (temp == 'x') {
					totalCnt++;
					count++;
				} else {
					list[count] = temp - 'A' + 1;
					visited[list[count]] = true;
					count++;
				}
			}
		}
		ok = false;
		dfs(0, 0);
		sb = new StringBuilder();
		print();
	}

	static void dfs(int count, int index) {
		if (ok)
			return;
		if (count == totalCnt) {
			if (check()) { // 어차피 순차적으로 조합을 만들기때문에 사전순
				result = Arrays.copyOf(list, list.length);
				ok = true;
			}
			return;
		}
		if (list[index] > 0) {
			dfs(count, index + 1);
		} else {
			for (int j = 1; j <= 12; j++) {
				if (visited[j])
					continue;
				list[index] = j;
				visited[j] = true;
				dfs(count + 1, index + 1);
				visited[j] = false;
				list[index] = 0;
			}

		}
	}

	static boolean check() {
		if ((list[0] + list[2] + list[5] + list[7]) != 26)
			return false;
		if ((list[7] + list[8] + list[9] + list[10]) != 26)
			return false;
		if ((list[0] + list[3] + list[6] + list[10]) != 26)
			return false;
		if ((list[1] + list[2] + list[3] + list[4]) != 26)
			return false;
		if ((list[1] + list[5] + list[8] + list[11]) != 26)
			return false;
		if ((list[4] + list[6] + list[9] + list[11]) != 26)
			return false;

		return true;
	}

	static void print() {
		for (int i = 0; i < 12; i++) {
			result[i] = result[i] - 1;
		}
		sb.append("....").append((char) (result[0] + 'A')).append("....").append("\n");
		sb.append(".").append((char) (result[1] + 'A')).append(".").append((char) (result[2] + 'A')).append(".")
				.append((char) (result[3] + 'A')).append(".").append((char) (result[4] + 'A')).append(".").append("\n");
		sb.append("..").append((char) (result[5] + 'A')).append("...").append((char) (result[6] + 'A')).append("..")
				.append("\n");
		sb.append(".").append((char) (result[7] + 'A')).append(".").append((char) (result[8] + 'A')).append(".")
				.append((char) (result[9] + 'A')).append(".").append((char) (result[10] + 'A')).append(".")
				.append("\n");
		sb.append("....").append((char) (result[11] + 'A')).append("....").append("\n");
		System.out.println(sb.toString());
	}

}
