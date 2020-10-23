package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투포인터
 * 
 * @see 백준 2470번: 두 용액 <br>
 *      메모리: 32732 KB <br>
 *      시간: 284 ms
 * @since 2020-10-23
 * 
 */

public class boj_2470 {

	private static int N;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int left = 0;
		int right = N-1;
		int leftNum = arr[left];
		int rightNum = arr[right];
		int min = leftNum+rightNum;
		if(min < 0) {
			left = 1;
		}else if(min >0) {
			right = N-2;
		}
		
		min = Math.abs(min);
		if(min == 0) {
			System.out.println(leftNum + " "+rightNum);
		}else {
			while(left<right) {
				int sum = arr[left] + arr[right];
				int temp = Math.abs(sum);
				if(min > temp) {
					leftNum = arr[left];
					rightNum = arr[right];
					min = temp;
				}
				if(sum < 0) {
					left++;
				}else if(sum >0) {
					right--;
				}else {
					System.out.println(leftNum + " "+rightNum);
					break;
				}
				
			}
			System.out.println(leftNum + " "+rightNum);
		}
		
		
		
		
	}

}
