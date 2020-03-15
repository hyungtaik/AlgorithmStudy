package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1976 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static ArrayList<Integer> list;
	private static int start;
	private static boolean check;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 도시의 수

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 계획한 도시 수
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		list = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			list.add(temp - 1);
		}
		check = true;
		Collections.sort(list);
		start = list.get(0);
		visited = new boolean[N];
		dfs(start);
		check = true;

		for (int i = 1; i < M; i++) {
			if (!visited[list.get(i)]) {
				check = false;
				break;
			}
		}
		System.out.println(check ? "YES" : "NO");
	}

	public static void dfs(int index) {
		visited[index] = true;
		for (int i = 0; i < N; i++) {
			if (map[index][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
		return;
	}
}
