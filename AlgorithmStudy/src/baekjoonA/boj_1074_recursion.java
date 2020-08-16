package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1074_recursion {
	
	private static int count;
	private static int N;
	private static int r;
	private static int c;
	private static boolean flag;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		r = Integer.parseInt(input[1]);
		c = Integer.parseInt(input[2]);
		
		int n = 1;
		for(int i=0;i<N;i++) {
			n*=2;
		}
		count = 0;
		flag = false;
		recursion(0,0,n);
		System.out.println(count);
	}
	
	static void recursion(int x, int y, int n) {
		if(flag) return;
		if(n == 1) {
			if(r == x && c == y) {
				flag = true;
				return;
			}
			count++;
			return;
		}
		recursion(x,y,n/2);
		recursion(x,y+(n/2),n/2);
		recursion(x+(n/2),y,n/2);
		recursion(x+(n/2),y+(n/2),n/2);
	}
}
