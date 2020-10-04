package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 18352번: 특정 거리의 도시 찾기 <br>
 *      메모리: 316328 KB <br>
 *      시간: 1636 ms
 * @since 2020-10-04
 * 
 */

public class boj_18352 {

	private static int N,M,K,X;
	private static int[] memo;
	private static ArrayList<Integer> list;
	private static ArrayList<Integer>[] map;
	private static PriorityQueue<Pair> pq;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start].add(end);
		}
		
		list = new ArrayList<Integer>();
		
		pq = new PriorityQueue<Pair>();
		pq.add(new Pair(X,0));
		visited = new boolean[N+1];
		visited[X] = true;
		
		BFS();
		if(list.size()==0) {
			System.out.println(-1);
		}else {
			Collections.sort(list);
			for(int i=0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
	static void BFS() {
		while(!pq.isEmpty()) {
			Pair temp = pq.poll();
			if(temp.cost == K) {
				list.add(temp.index);
				continue;
			}
			for(int i=0;i<map[temp.index].size();i++) {
				int next = map[temp.index].get(i);
				if(visited[next]) continue;
				
				visited[next] = true;
				pq.add(new Pair(next,temp.cost+1));
			}
		}
	}
	static class Pair implements Comparable<Pair>{
		int index,cost;

		public Pair(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pair o) {
			return this.cost - o.cost;
		}
	}
}
