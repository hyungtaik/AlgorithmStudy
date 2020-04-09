package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1197 {

	private static int V;
	private static int E;
	private static ArrayList<Node> list;
	private static int[] numbers;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		list = new ArrayList<Node>();
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Node(a,b,c));
		}
		numbers = new int[V+1];
		Collections.sort(list);
		
		int count = 0;
		int index = 0;
		makeSet();
		min = 0;
		while(count<V-1) {
			Node temp = list.get(index);
			if(union(temp.x,temp.y)) {
				min += temp.pay;
				count++;
			}
			
			index++;
		}
		System.out.println(min);
	}
	static void makeSet() {
		Arrays.fill(numbers, -1);
	}
	static int findSet(int a) {
		if(numbers[a] <0  ) return a;
		return numbers[a] = findSet(numbers[a]);
	}
	static boolean union(int a,int b) {
		int first = findSet(a);
		int second = findSet(b);
		if(first == second) {
			return false; // 이미 같은 집합
		}else {
			if(first>second) {
				numbers[first] = second;
			}else {
				numbers[second] = first;
			}
		}
		return true;
		
	}
	
	static class Node implements Comparable<Node>{
		int x,y,pay;

		public Node(int x, int y, int pay) {
			super();
			this.x = x;
			this.y = y;
			this.pay = pay;
		}
		@Override
		public int compareTo(Node o) {
			
			return this.pay - o.pay;
		}
	}

}
