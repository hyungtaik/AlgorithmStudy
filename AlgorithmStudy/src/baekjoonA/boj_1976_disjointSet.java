package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1976_disjointSet {

	private static int N;
	private static int M;
	private static ArrayList<Integer> list;
	private static int start;
	private static boolean check;
	private static int num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 도시의 수

		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 계획한 도시 수

		parents = new int[N];
		makeSet();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				num = Integer.parseInt(st.nextToken());
				if(num==1) {
					union(i,j);
				}
			}
		}
		
		list = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			list.add(temp - 1);
		}
		Collections.sort(list);
		start = list.get(0);
		int num = findSet(start); // 첫번째 노드의 부모노드
		
		check = true;

		for (int i = 1; i < M; i++) {
			if (num!=findSet(list.get(i))) {
				check = false;
				break;
			}
		}
		System.out.println(check ? "YES" : "NO");
	}
	
	private static int[] parents;
	private static void makeSet() { 
		for(int i=0;i<N;i++) {
			parents[i] = i;
		}
	}
	
	private static int findSet(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]); 
		
	}
	
	private static void union(int a,int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot<=bRoot) { 
			parents[bRoot] = aRoot;
		}else {
			parents[aRoot] = bRoot;
		}
	}
}
