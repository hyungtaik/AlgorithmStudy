package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author TAEK
 * @category DFS(백트래킹)
 * 
 * @see 백준 16637번: 괄호 추가하기 <br>
 *      메모리: 12972 KB <br>
 *      시간: 84 ms
 * @since 2020-10-10
 * 
 */

public class boj_16637_2 {

	private static int N;
	private static ArrayList<Integer> number;
	private static ArrayList<Character> calc;
	private static int max;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		number = new ArrayList<Integer>();
		calc = new ArrayList<Character>();
		for(int i=0;i<input.length();i++) {
			if(input.charAt(i)=='+' || input.charAt(i)=='-'||input.charAt(i)=='*' ) {
				calc.add(input.charAt(i));
			}else {
				number.add(input.charAt(i)-'0');
			}
		}
		max = Integer.MIN_VALUE;
		dfs(0,number.get(0));
		System.out.println(max);
	}
	static void dfs(int index,int sum) {
		if(index == calc.size()) {
			max = Math.max(sum, max);
			return;
		}
		int num1 = cal(sum,number.get(index+1),calc.get(index));
		dfs(index+1,num1);
		if(index+1 < calc.size()) {
			int num2 = cal(number.get(index+1),number.get(index+2),calc.get(index+1));
			dfs(index+2,cal(sum,num2,calc.get(index)));
		}
	}
	static int cal(int a,int b,char op) {
		switch(op) {
		case '*':
			return a*b;
		case '+':
			return a+b;
		case '-':
			return a-b;
		}
		return 0;
	}

}
