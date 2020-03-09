package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_D4_7701_염라대왕의이름정렬_김형택 {

	private static int TC;
	private static int N;
	private static ArrayList<String> list;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			HashSet<String> set = new HashSet<String>();
			for(int i=0;i<N;i++) {
				String input = br.readLine();
				set.add(input);
				
			}
			list = new ArrayList<String>(set);
			sb = new StringBuilder();
			Collections.sort(list,new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					if(o1.length() == o2.length()) return o1.compareTo(o2); 
					return o1.length() - o2.length();
				}
			});
			sb.append("#"+tc+'\n');
			solve();
			System.out.print(sb.toString());
		}
	}

	private static void solve() {
		for(String name:list) {
			sb.append(name+'\n');
		}
	}
	
}
