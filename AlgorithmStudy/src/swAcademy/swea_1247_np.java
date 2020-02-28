package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1247_np {

	private static int N;
	private static int TC;
	private static Pair company;
	private static Pair home;
	private static Pair[] person;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine()," ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Pair(x,y);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Pair(x,y);
			
			person = new Pair[N];
			for(int i=0;i<N;i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				person[i] = new Pair(x,y);
			}
			int[] numbers = new int[N];
			for(int i=0;i<N;i++) {
				numbers[i] = i;
			}
			result = Integer.MAX_VALUE;
			do {
//				System.out.println(Arrays.toString(numbers));
				solve(numbers);
			} while (np(numbers));
			
			System.out.println("#"+tc+" "+result);
		}
	}
	static boolean np(int[] p) {
		int len = p.length;
		int i= len -1;
		
		while(i>0 && p[i-1]>=p[i]) i--;
		if(i==0) return false;
		
		int j = len-1;
		while(p[i-1]>=p[j]) j--;
		
		int temp = p[i-1];
		p[i-1] = p[j];
		p[j] = temp;
		
		j = len-1;
		while(i<j) {
			temp = p[i];
			p[i] = p[j];
			p[j] = temp;
			i++;j--;
		}
		return true;
	}
	static void solve(int[] arr) {
		int sum = Math.abs(company.x-person[arr[0]].x)+Math.abs(company.y - person[arr[0]].y);
		for(int i=0;i<N-1;i++) {
			int cal = Math.abs(person[arr[i]].x-person[arr[i+1]].x)+Math.abs(person[arr[i]].y- person[arr[i+1]].y);
			sum+=cal;
		}
		int temp  = Math.abs(person[arr[N-1]].x-home.x)+Math.abs(person[arr[N-1]].y- home.y);
		sum+=temp;
		result = Math.min(result, sum);
	}
	
	static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
