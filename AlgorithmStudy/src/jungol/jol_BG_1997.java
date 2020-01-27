package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_BG_1997 {
	static int day;
	static int total;
	static int A,B;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		day = Integer.parseInt(st.nextToken());
		total = Integer.parseInt(st.nextToken());
		
		A = 1;
		B = 1;
		solve(day);
		System.out.println(A);
		System.out.println(B);
	}
	static void solve(int day) {
		int num1 = Fib(day-2); //a¿¡ °öÇÒ ¼ö
		int num2 = Fib(day-1); //b¿¡ °öÇÒ ¼ö
		int a = 1;
		int b = total/num2;
		while(a<=(total/num1)) {
			for(int i = b;i>=a;i--) {
				if((total - (num1*a)) == (num2*i)) {
					A = a;
					B = i;
					return;
				}
			}
			a++;
		}
				
		
	}
	static int Fib(int num) {
		if(num == 1 || num ==2) {
			return 1;
		}
		return Fib(num-1)+Fib(num-2);
	}
}
