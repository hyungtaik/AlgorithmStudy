package baekjoonA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_17471 {
	static int N;
	static int[] people;
	static boolean[][] map;
	static int[] election; // 1 or 2
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		election = new int[N + 1];
		visited = new boolean[N + 1];
		map = new boolean[N + 1][N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		int cnt, temp;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			for (int c = 0; c < cnt; c++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][temp] = true;
			}
		}

		dfs(1);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);

	}

	static void dfs(int index) {

		if (index == N + 1) {
			int first = 0, second = 0;
			for (int e = 1; e <= N; e++) {
				if (election[e] == 1) {
					first += people[e];
				} else {
					second += people[e];
				}
			}
			Arrays.fill(visited, false);

			int cnt = 0;
			for (int e = 1; e <= N; e++) {
				if (!visited[e]) {
					solve(e, election[e]);
					cnt++;
				}
			}

			if (cnt == 2) {
				if (min > Math.abs(first - second)) {
					min = Math.abs(first - second);
				}
			}
			return;
		}

		election[index] = 1;
		dfs(index + 1);

		election[index] = 2;
		dfs(index + 1);
	}

	static void solve(int e, int num) {
		visited[e] = true;
		for (int i = 1; i <= N; i++) {
			if (map[e][i] && !visited[i] && election[i] == num) {
				solve(i, num);
			}
		}
	}

}