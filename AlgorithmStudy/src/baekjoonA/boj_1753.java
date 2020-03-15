package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1753 {

	private static int V;
	private static int E;
	private static int start;
	private static int[] ans;
	private static PriorityQueue<Pair> pq;
	private static ArrayList<ArrayList<Pair>> slist;
	private static boolean[] visited;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
//		map = new int[V+1][V+1];
		slist = new ArrayList<ArrayList<Pair>>(); //시작 리스트
		
		for(int i=0;i<=V;i++) {
			slist.add(new ArrayList<Pair>());
		}
		
		st = new StringTokenizer(br.readLine()," ");
		start = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			slist.get(u).add(new Pair(v, w));
		}
		
		ans = new int[V+1];
		Arrays.fill(ans, Integer.MAX_VALUE);
		ans[start] = 0;
		visited = new boolean[V+1];
		dijkstra();
		sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (visited[i]) {
				sb.append(ans[i]+"\n");
			} else {
				sb.append("INF"+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	static void dijkstra() {
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(start,0));
		while(!pq.isEmpty()) {
			int cur = pq.poll().end;
			if(visited[cur]) continue;
			
			visited[cur] = true;
			for(Pair p:slist.get(cur)) {
				int end = p.end;
				int cost = p.w;
				if(ans[end]>cost+ans[cur]) {
					ans[end] = cost+ans[cur];
					pq.add(new Pair(end,ans[end]));
				}
			}
		}
	}
	static class Pair implements Comparable<Pair>{
		int end,w;

		public Pair(int end, int w) {
			super();
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Pair o) {
			return this.w-o.w;
		}
	}

}
