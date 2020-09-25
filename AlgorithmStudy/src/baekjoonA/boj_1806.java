package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투포인터
 * 
 * @see 백준 1806번: 부분합 <br>
 *      메모리: 24156 KB <br>
 *      시간: 232 ms
 * @since 2020-09-26
 * 
 */

public class boj_1806 {

	private static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
	
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0,end = 0;
		long sum = 0;
		int len = Integer.MAX_VALUE;
		while(true) {
			if(sum >= S) {
				len = Math.min(len, end - start);
				sum -= arr[start];
				start++;
			}
			else if(end == N) {
				break;
			}else {
				sum+=arr[end];
				end++;
			}
		}
		
		if(len == Integer.MAX_VALUE) len = 0;
		System.out.println(len);
	}

}
