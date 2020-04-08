package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1922 {
	private static int N;
	private static int M;
	private static ArrayList<Pair> list;
	private static int[] computers;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Pair>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Pair(a,b,c));
		}
		Collections.sort(list);
		computers = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			computers[i] = -1;
		}
		
		int count = 0;
		int index = 0;
		int sum = 0;
		while(count<N-1) {
			Pair temp = list.get(index);
			if(union(temp.x,temp.y)) {
				count++;
				sum+=temp.pay;
			}
			index++;	
		}
		System.out.println(sum);
		
	}
	static int findSet(int a) {
		if(computers[a] <0) return a;
		return computers[a] = findSet(computers[a]);
	}
	static boolean union(int a,int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot!=bRoot) {
			if(aRoot>bRoot) {
				computers[aRoot] = bRoot;
			}else {
				computers[bRoot] = aRoot;
			}
			return true;
		}
		
		return false;
	}
	
	static class Pair implements Comparable<Pair>{
		int x,y,pay;

		public Pair(int x, int y, int pay) {
			super();
			this.x = x;
			this.y = y;
			this.pay = pay;
		}
		@Override
		public int compareTo(Pair o) {
			return this.pay - o.pay;
		}
	}
}
