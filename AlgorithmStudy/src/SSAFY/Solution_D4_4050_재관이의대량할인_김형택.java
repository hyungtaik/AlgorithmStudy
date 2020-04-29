package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_D4_4050_재관이의대량할인_김형택 {

	private static int TC;
	private static int N;
	private static int cost;
	private static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			list = new ArrayList<Integer>();
			for(int i=0;i<N;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list);
			int remain = N%3;
			cost = 0;
			if(remain == 1) {
				cost += list.get(0);
				list.remove(0);
				for(int i=list.size()-1;i>=0;i--) {
					if(i%3 == 0) continue;
					cost+=list.get(i);
				}
			}else if(remain == 2) {
				cost += list.get(0);
				list.remove(0);
				cost += list.get(0);
				list.remove(0);
				for(int i=list.size()-1;i>=0;i--) {
					if(i%3 == 0) continue;
					cost+=list.get(i);
				}
			}else {
				for(int i=list.size()-1;i>=0;i--) {
					if(i%3 == 0) continue;
					cost+=list.get(i);
				}
			}
			System.out.println("#"+tc+" "+cost);
			
			
		}
	}

}
