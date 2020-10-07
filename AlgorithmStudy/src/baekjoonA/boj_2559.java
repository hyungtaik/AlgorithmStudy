package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투 포인터
 * 
 * @see 백준 2559번: 수열 <br>
 *      메모리: 23312 KB <br>
 *      시간: 216 ms
 * @since 2020-10-08
 * 
 */

public class boj_2559 {
	private static int N;
	private static int K;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		while(end < N) {
			sum += arr[end];
			if(end-start == K-1) {
				max = Math.max(max, sum);
				sum-=arr[start];
				start++;
				end++;
			}else {
				end++;
			}
		}
		System.out.println(max);
	}
}
