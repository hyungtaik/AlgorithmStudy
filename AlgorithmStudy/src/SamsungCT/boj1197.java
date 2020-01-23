package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 크루스칼 - 최소스패닝 트리
public class boj1197 {

	static int V,E;
	
	static class Node implements Comparable<Node>{
		int v1,v2;
		int cost;
		
		Node(int v1,int v2,int cost){
			this.v1 = v1;
			this.v2 = v2;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.cost < o.cost) {
				return -1;
			}
			else if(this.cost == o.cost) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	
	static int[] parent;
	
	static boolean sameParent(int x,int y) {
		x = find(x);
		y = find(y);
		if(x==y) {
			return true;
		}
		else return false;
	}
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y) {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		List<Node> list = new ArrayList<Node>();
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int v1= Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.add(new Node(v1,v2,cost));
		}
		for(int i=0;i<V;i++) {
			parent[i] = i;
		}
		
		Collections.sort(list);
		
		int result = 0;
		for(int i=0;i<list.size();i++) {
			Node nd = list.get(i);
			if(!sameParent(nd.v1,nd.v2)) {
				System.out.println(nd.v1+","+nd.v2);
				result+=nd.cost;
				union(nd.v1,nd.v2);
			}
		}
		System.out.println(result);
	}

}
