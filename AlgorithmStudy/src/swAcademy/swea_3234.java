package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_3234 {

	private static int TC;
	private static int N;
	private static int[] arr;
	private static boolean[] visited;
	private static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			cnt = 0;
			dfs(0, 0, 0,arr,visited);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	static void dfs(int count, int left, int right, int[] arr, boolean[] visited) {
		if (count == arr.length) {
			cnt++;
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (visited[i])
				continue;
			int temp = arr[i]; // 추 무게
			visited[i] = true;
			dfs(count + 1, left + temp, right, arr, visited);
			// 왼쪽이 항상 무겁게
			if ((right + temp) <= left) {
				visited[i] = true;
				dfs(count + 1, left, right + temp, arr, visited);
			}
			visited[i] = false;
			
		}
	}

}
