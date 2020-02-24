package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1062 {

	private static int N;
	private static int K;
	private static String[] arr;
	private static int result;
	private static int idx;
	private static boolean[] alpha;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new String[N];
		
		alpha = new boolean[26]; //¾ËÆÄºª
		alpha['a'-97] = true;
		alpha['n'-97] = true;
		alpha['t'-97] = true;
		alpha['i'-97] = true;
		alpha['c'-97] = true;
		result = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			arr[i] = input.substring(4, input.length()-4);
		}
		
		if(K<5) {
			System.out.println(0);
		}else if(K>=26) {
			System.out.println(N);
		}else {
			K-=5;
			dfs(0,0);
			System.out.println(result);
			
		}
	}
	static void dfs(int index, int count) {
		if(count == K) {
			int cnt = 0;
			for(int i=0;i<N;i++) {
				boolean check=false;
				for(int j=0;j<arr[i].length();j++) {
					if(!alpha[arr[i].charAt(j)-97]) {
						check = true;
						break;
					}
				}if(!check) cnt++;
			}
			result=Math.max(result, cnt);
			return;
		}
		
		for(int i=index;i<26;i++) {
			if(!alpha[i]) {
				alpha[i] = true;
				dfs(i,count+1);
				alpha[i]=false;
			}
		}
	}
}
