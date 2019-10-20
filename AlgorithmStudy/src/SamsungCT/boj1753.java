package SamsungCT;
// 다익스트라

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1753 {
	static int V, E;
	static int start;
	static boolean[] visited;
	static int v1, v2;
	static int[] distance;
	static List<List<Node>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		distance = new int[V + 1];
		list = new ArrayList<List<Node>>();
		
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		
		list.add(new ArrayList<Node>());
		Arrays.fill(distance, Integer.MAX_VALUE);

		for (int i = 1; i <= V; i++) {
			list.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
//			System.out.println(index + "," + end + "," + cost);
			list.get(index).add(new Node(end, cost));
		}

		dijkstra();

		for (int i = 1; i <= V; i++) {
			if (visited[i]) {
				System.out.println(distance[i]);
			} else {
				System.out.println("INF");
			}
		}
	}

	
	static void dijkstra() {
		visited = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		distance[start] = 0;
		pq.add(new Node(start,0));
		

		while (!pq.isEmpty()) {
			int cur = pq.poll().end;

			if(visited[cur]) continue;

			visited[cur] = true;
			for (Node node : list.get(cur)) {
				int next = node.end;
				int cost = node.cost;

				if (distance[next] > distance[cur] + cost) {
					distance[next] = distance[cur] + cost;
					pq.add(new Node(next,distance[next]));
				}
			}

		}

	}
	static class Node implements Comparable<Node>{
		int end;
		int cost;

		Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
}
