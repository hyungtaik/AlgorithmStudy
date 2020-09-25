package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투포인터
 * 
 * @see 백준 2003번: 수들의 합2 <br>
 *      메모리: 15764 KB <br>
 *      시간: 124 ms
 * @since 2020-09-25
 * 
 */

public class boj_2003 {

	private static int[] A;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int start =0,end = 0;
		int sum = 0;
		int count = 0;
		while(true) {
			if(sum == M) {
				count++;
			}
			if(sum >=M) {
				sum-=A[start];
				start++;
			}else if(end == N) {
				break;
			}else {
				sum+=A[end];
				end++;
			}
		}
		System.out.println(count);
		
		
	}

}
