package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 다익스트라
 * 
 * @see 백준 10282번: 해킹 <br>
 *      메모리: 161404 KB <br>
 *      시간: 1088 ms
 * @since 2020-10-31
 * 
 */

public class boj_10282 {

	private static int TC;
	private static int n;
	private static int d;
	private static int c;
	private static int a,b,s;
	private static PriorityQueue<Pair> pq;
	private static int[] visited;
	private static ArrayList<Pair>[] list;
	private static ArrayList<Integer> result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n + 1];
			for(int i=1;i<=n;i++) {
				list[i] = new ArrayList<Pair>();
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				list[b].add(new Pair(a,s));
			}
			
			pq = new PriorityQueue<Pair>();
			visited = new int[n+1];
			Arrays.fill(visited, Integer.MAX_VALUE);
			visited[c] = 0;
			
	        pq.offer(new Pair(c, visited[c]));
	        int count = 1;
	        while (!pq.isEmpty()) {
	        	Pair temp = pq.poll();
	 
	            if (temp.time > visited[temp.num])
	                continue;
	 
	            for (Pair a : list[temp.num]) {
	                if (visited[a.num] > visited[temp.num] + a.time) {
	                    if(visited[a.num] == Integer.MAX_VALUE) 
	                        count++;         
	                    visited[a.num] = visited[temp.num] + a.time;
	                    pq.offer(new Pair(a.num, visited[a.num]));
	                }
	            }
	        }
	        result = new ArrayList<Integer>();
	        for(int i=1;i<=n;i++) {
	        	if(visited[i] == Integer.MAX_VALUE) continue;
	        	result.add(visited[i]);
	        }
	        Collections.sort(result);
            System.out.println(count+" "+result.get(result.size()-1));
		}
	}
	static class Pair implements Comparable<Pair>{
		int num, time;

		public Pair(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
		@Override
		public int compareTo(Pair o) {
			return this.time - o.time;
		}
	}

}
