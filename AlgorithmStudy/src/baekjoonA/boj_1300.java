package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author TAEK
 * @category 이분 탐색
 * 
 * @see 백준 1300번: K번째 수 <br>
 *      메모리: 12984 KB <br>
 *      시간: 124 ms
 * @since 2020-09-28
 * 
 */

public class boj_1300 {

	private static long result;
	private static long k;
	private static long N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine());
		k = Long.parseLong(br.readLine());
		result = 0L;
		long left = 1;
		long right = k;
		System.out.println(binarySearch(left,right));
	}
	static long binarySearch(long left,long right) {
		int count = 0;
		
		long mid = (left + right)/2;
		if(left>right) return result;
		
		for(int i=1;i<=N;i++) {
			count+= Math.min(mid/i, N);
		}
		if(count >= k) {
			result = mid;
			return binarySearch(left, mid-1);
		}else {
			return binarySearch(mid+1, right);
		}
		
	}
	

}
