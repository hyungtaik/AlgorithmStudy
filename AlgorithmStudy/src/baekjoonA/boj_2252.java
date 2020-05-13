package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_2252 {

	private static int N;
	private static int M;
	private static ArrayList<Integer>[] list;
	private static LinkedList<Integer> q;
	private static int[] check;
	private static StringBuilder sb; 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		check = new int[N+1];
		for(int i=1;i<=N;i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			list[first].add(second);
			check[second]++;
		}
		q = new LinkedList<Integer>();
		for(int i=1;i<=N;i++) {
			if(check[i]==0) q.add(i);
		}
		sb = new StringBuilder();
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			sb.append(temp+" ");
			for(int i=0;i<list[temp].size();i++) {
				int ttemp = list[temp].get(i);
				check[ttemp]--;
				
				if(check[ttemp]==0) q.add(ttemp);
				
			}
		}
		System.out.println(sb.toString());
		
	}

}
