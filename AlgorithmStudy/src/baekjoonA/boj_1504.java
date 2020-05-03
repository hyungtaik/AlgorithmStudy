package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1504 {

	private static int N;
	private static int E;
	private static int[][] map;
	private static long[] dist;
	private static int v1;
	private static int v2;
	private static PriorityQueue<Pair> pq;
	private static int INF = Integer.MAX_VALUE - 1;


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(map[start][end]>0) {
				map[start][end] = Math.min(map[start][end],cost);
				map[end][start] = Math.min(map[end][start],cost);
			}else {
				map[start][end] = cost;
				map[end][start] = cost;
			}
		}
		dist = new long[N+1];
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		// 1->v1->v2->N
		long first = dijkstra(1,v1);
		long second = dijkstra(v1,v2);
		long third = dijkstra(v2,N);
		long result1 = 0;
		if(first>=INF ||second>=INF ||third>=INF) {
			result1 = -1;
		}else {
			result1 = first + second + third;
		}
		// 1->v2->v1->N
		first = dijkstra(1,v2);
		second = dijkstra(v2,v1);
		third = dijkstra(v1,N);
		long result2 = 0;
		if(first>=INF ||second>=INF ||third>=INF ) {
			result2 = -1;
		}else {
			result2 = first + second + third;
		}
		long result = 0;
		if(result1==-1 && result2 == -1 ) {
			result = -1;
		}else if(result1!=-1 && result2 == -1 ) {
			result = result1;
		}else if(result1==-1 && result2 != -1 ) {
			result = result2;
		}else {
			result = Math.min(result1,result2);
		}
		System.out.println(result);
	}
	static long dijkstra(int start,int end) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(start,0));
		while(!pq.isEmpty()) {
			Pair temp = pq.poll();
			for(int i=1;i<=N;i++) {
				if(map[temp.index][i]>0) {
					if(dist[i]>temp.cost+map[temp.index][i]) {
						dist[i]=temp.cost+map[temp.index][i];
						pq.add(new Pair(i,dist[i]));
					}
				}
			}
		}
		return dist[end];
	}
	
	static class Pair implements Comparable<Pair>{
		int index;
		long cost;

		public Pair(int index, long cost) {
			super();
			this.index = index;
			this.cost = cost;
		}
		@Override
		public int compareTo(Pair o) {
			return (int) (this.cost-o.cost);
		}
	}
	

}
