package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2606 {

	private static int n;
	private static int c;
	private static ArrayList<Integer>[] list;
	private static boolean[] virus;
	private static LinkedList<Integer> q;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		c = Integer.parseInt(st.nextToken());
		list = new ArrayList[n+1];
		for(int i=0;i<n+1;i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<c;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		virus = new boolean[n+1];
		visited = new boolean[n+1];
		virus[1] = true;
		visited[1] = true;
		q = new LinkedList<Integer>();
		for(int i=0;i<list[1].size();i++) {
			visited[list[1].get(i)] = true;
			q.add(list[1].get(i));
		}
		solve();
		int count = 0;
		for(int i=2;i<n+1;i++) {
			if(virus[i]) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	static void solve() {
		while(!q.isEmpty()) {
			int temp = q.poll();
			virus[temp] = true;
			for(int i=0;i<list[temp].size();i++) {
				if(visited[list[temp].get(i)]) continue;
				q.add(list[temp].get(i));
				visited[list[temp].get(i)] = true;
			}
		}
	}

}
