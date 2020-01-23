package SamsungCT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14501 {

	static int N;
	static Pair[] table;
	static int MAX = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		
		table = new Pair[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			table[i] = new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
	
		
		for(int i=0;i<N;i++) {
			solve(i,0);
		}
		
		System.out.println(MAX);

	}
	
	static void solve(int num,int temp) {
		
		if(num >= N) {
			MAX = Math.max(MAX, temp);
			return;
		}
				
		if(num + table[num].T <= N ) {
			solve(num+table[num].T,temp+table[num].P);
		}
		solve(num+1,temp);	
	
		return;
		
	}
	
	static class Pair{
		int T;
		int P;
		
		Pair(int T,int P){
			this.T = T;
			this.P = P;
		}
		
	}
}
