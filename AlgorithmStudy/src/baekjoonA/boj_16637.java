package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16637 {

	private static int N;
	private static String input;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		input = st.nextToken();
		max = Integer.MIN_VALUE;
		// 3 + 8 * 7 - 9 * 2
		dfs(1,input.charAt(0)-'0',0,false); // false: 괄호 x
		System.out.println(max);
	}
	static void dfs(int index,int sum,int lastSum, boolean ok) {
		if(index == N) {
			max = Math.max(max,sum);
			return;
		}
		dfs(index+2, cal(sum,input.charAt(index+1)-'0',input.charAt(index)),sum,false);
		if(index>1 && !ok) {
			dfs(index+2,cal(lastSum,cal(input.charAt(index-1)-'0',input.charAt(index+1)-'0',input.charAt(index)),input.charAt(index-2)),0,true);
		}
	}
	
	static int cal(int a,int b,char operation) {
		if(operation == '+') {
			return a+b;
		}else if(operation == '-') {
			return a-b;
		}else {
			return a*b;
		}
	}

}
