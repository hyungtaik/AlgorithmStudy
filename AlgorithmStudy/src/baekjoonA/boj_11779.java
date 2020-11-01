package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 다익스트라
 * 
 * @see 백준 11779번: 최소비용 구하기 2 <br>
 *      메모리: 46680 KB <br>
 *      시간: 456 ms
 * @since 2020-11-01
 * 
 */

public class boj_11779 {

	private static int n;
	private static int m;
	private static ArrayList<Pair>[] list;
	private static int start;
	private static int finish;
	private static long[] dp;
	private static PriorityQueue<Pair> q;
	private static int[] path;
	private final static long MAX_NUM = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<Pair>();
		}
		dp = new long[n];
		path = new int[n];
		Arrays.fill(dp, MAX_NUM);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			list[from].add(new Pair(to, cost));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken()) - 1;
		finish = Integer.parseInt(st.nextToken()) - 1;
		dijkstra();
	}

	static void dijkstra() {
		q = new PriorityQueue<Pair>();
		dp[start] = 0;
		q.add(new Pair(start, 0));

		while (!q.isEmpty()) {
			Pair start = q.poll();
			if (dp[start.from] < start.cost)
				continue;
			if (start.from == finish)
				break;

			int len = list[start.from].size();
			for (int i = 0; i < len; i++) {
				Pair tmp = list[start.from].get(i);
				if (dp[tmp.from] >= dp[start.from] + tmp.cost) {
					dp[tmp.from] = dp[start.from] + tmp.cost;
					q.add(new Pair(tmp.from, dp[tmp.from]));
					path[tmp.from] = start.from;
				}
			}
		}
		System.out.println(dp[finish]);

		ArrayDeque<Integer> result = new ArrayDeque<Integer>();
		int s = finish;
		int n = path[finish];
		result.push(s);
		while (s != start) {
			result.push(n);
			s = n;
			n = path[s];
		}

		System.out.println(result.size());
		int len = result.size();
		for (int i = 0; i < len; i++) {
			System.out.print(result.pop() + 1 + " ");
		}
	}

	static class Pair implements Comparable<Pair> {
		int from;
		long cost;

		public Pair(int from, long cost) {
			super();
			this.from = from;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair o) {
			return Long.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "Pair [from=" + from + ", cost=" + cost + "]";
		}

	}
}
