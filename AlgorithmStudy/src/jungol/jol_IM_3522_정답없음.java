package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (Dynamic Programming)

public class jol_IM_3522_정답없음{
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		System.out.println(Fib(N)%1000000007);
	}
	
	static int Fib(int num) {
		if(num == 1) return 1;
		else if(num == 2) return 1;
		else {
			return Fib(num-2)+Fib(num-1);
		}
	}

}
