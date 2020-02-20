package com.ssafy.subset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1931 {

	private static int N;
	private static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken()); // 회의의 수
		ArrayList<Pair> list = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Pair(start,end));
		}
		Collections.sort(list);
		int len = list.size();
		Pair start = list.get(0);
		int count=0;
		for(int i=1;i<len;i++) {
			Pair temp = list.get(i);
			if(start.y>temp.x) continue;
			start = list.get(i);
			count++;
			
		}
		System.out.println(count+1);
	}
	static class Pair implements Comparable<Pair>{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.y == o.y) return this.x-o.x; 
			return this.y - o.y;
		}
	}
}
