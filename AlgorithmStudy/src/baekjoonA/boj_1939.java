package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS + 이분 탐색 / Union-Find 알고리즘도 가능
 * 
 * @see 백준 1939번: 중량제한 <br>
 *      메모리: 65104 KB <br>
 *      시간: 616 ms
 * @since 2020-09-30
 * 
 */

public class boj_1939 {

	private static int N;
	private static int M;
	private static ArrayList<Pair>[] list;
	private static int start;
	private static int end;
	private static boolean[] visited;
	private static LinkedList<Integer> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		int s = Integer.MAX_VALUE, e = Integer.MIN_VALUE;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			s = Math.min(s, num);
			e = Math.max(e, num);
			list[start].add(new Pair(end, num));
			list[end].add(new Pair(start, num));
		}
		st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		int mid = 0;
		while (s <= e) {
			visited = new boolean[N + 1];
			mid = (s + e) / 2;

			if (bfs(mid)) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		System.out.println(s-1);
	}

	static boolean bfs(int mid) {
		q = new LinkedList<Integer>();
		visited[start] = true;
		q.add(start);

		while (!q.isEmpty()) {
			int temp = q.poll();
			if (temp == end) {
				return true;
			}
			for (int i = 0; i < list[temp].size(); i++) {
				int node = list[temp].get(i).node;
				int value = list[temp].get(i).value;
				if (visited[node])
					continue;
				if (mid > value)
					continue;
				visited[node] = true;
				q.add(node);
			}
		}

		return false;
	}

	static class Pair {
		int node;
		int value;

		public Pair(int node, int value) {
			super();
			this.node = node;
			this.value = value;
		}
	}

}
