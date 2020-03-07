package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_D4_1251_하나로_Kruskal {

	private static int TC;
	private static int N;
	private static Pair[] map;
	private static double E;
	private static int[] parents;
	private static double[][] visit;
	private static double result;
	private static ArrayList<Pair> list;

	public static void main(String[] args) throws Exception{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new Pair[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int x = Integer.parseInt(st.nextToken());
				map[i] = new Pair(x,0,0);
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				int y = Integer.parseInt(st.nextToken());
				map[i].y = y; 
			}
			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());

			list = new ArrayList<Pair>();
			
			for(int i=0;i<N;i++) {
				Pair p = map[i];
				for(int j=i+1;j<N;j++) {
					Pair t = map[j];
					long num = (long) (Math.pow(p.x - t.x,2)+Math.pow(p.y - t.y,2));
					list.add(new Pair(i,j,num));
//					list.add(new Pair(j,i,num));
				}
			}
			Collections.sort(list);
			parents = new int[N];
			makeSet();
			
			result = 0.0;
			solve();
			result*=E;
			System.out.println("#"+tc+" "+Math.round(result));
		}
	}
	
	static void solve() {
       for(int i=0;i<list.size();i++) {
    	   Pair temp = list.get(i);
    	   if(union(temp.x, temp.y)) {
    		   result+=temp.num;
    	   }
       }
	}
	static void makeSet() {
		Arrays.fill(parents, -1);
	}
	static int findSet(int x) {
		if(parents[x]<0) return x;
		return parents[x] = findSet(parents[x]); 
	}
	
	static boolean union(int x,int y) {
		int aRoot = findSet(x);
		int bRoot = findSet(y);
		
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	
	static class Pair implements Comparable<Pair>{
		int x,y;
		long num;
		@Override
		public int compareTo(Pair o) {
			return Long.compare(this.num, o.num);
		}
		public Pair(int x, int y, long num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

}
