package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1238 {

	private static int N;
	private static int M;
	private static int X;
	private static int[][] map;
	private static PriorityQueue<Pair> pq;
	private static int[] dist;
	private static int[] rdist;
	private static boolean[] visited;
	private static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[start][end] = time;
		}
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(X, 0));
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra_go();

		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(X, 0));
		rdist = new int[N + 1];
		Arrays.fill(rdist, Integer.MAX_VALUE);
		dijkstra_back();
		

		max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dist[i] + rdist[i]);
		}
		System.out.println(max);
	}

	static void print(int[] table) {
		for (int i = 0; i <= N; i++) {
			System.out.print(table[i] + " ");
		}
	}

	static void dijkstra_go() {
		visited = new boolean[N + 1];
		dist[X] = 0;
		while (!pq.isEmpty()) {
			Pair temp = pq.poll();
			if (visited[temp.start])
				continue;
			visited[temp.start] = true;

			for (int i = 1; i <= N; i++) {
				if (map[i][temp.start] > 0) {
					if (dist[i] > temp.cost + map[i][temp.start]) {
						dist[i] = temp.cost + map[i][temp.start];
						pq.add(new Pair(i, dist[i]));
					}
				}
			}
		}
	}
	
	static void dijkstra_back() {
		visited = new boolean[N + 1];
		rdist[X] = 0;
		while (!pq.isEmpty()) {
			Pair temp = pq.poll();
			if (visited[temp.start])
				continue;
			visited[temp.start] = true;

			for (int i = 1; i <= N; i++) {
				if (map[temp.start][i] > 0) {
					if (rdist[i] > temp.cost + map[temp.start][i]) {
						rdist[i] = temp.cost + map[temp.start][i];
						pq.add(new Pair(i, rdist[i]));
					}
				}
			}
		}
	}

	

	static class Pair implements Comparable<Pair> {
		int start, cost;

		public Pair(int start, int cost) {
			super();
			this.start = start;
			this.cost = cost;
		}

		@Override
		public int compareTo(Pair o) {
			return this.cost - o.cost;
		}
	}

}
