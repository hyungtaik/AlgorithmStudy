package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투 포인터
 * 
 * @see 백준 2531번: 회전 초밥 <br>
 *      메모리: 18020 KB <br>
 *      시간: 168 ms
 * @since 2020-10-08
 * 
 */

public class boj_2531 {
	private static int N,d,k,c;
	private static int[] arr;
	private static int max;
	private static int[] eatList;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[N*2];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			arr[N+i] = arr[i];
		}
		max = 0;
		
		eatList = new int[d+1];
		int start = 0;
		int end = 0;
		int count = 0;
		while(end<N+k) {
			if(eatList[arr[end]]==0) count++;
			eatList[arr[end]]++;
			if(end - start == k-1) {
				int temp = count;
				if(eatList[c]==0) {
					temp++;
				}
				max = Math.max(max, temp);
				if(max > d) break;
				eatList[arr[start]]--;
				if(eatList[arr[start]]==0) count--;
				start++;
				end++;
			}else {
				end++;
			}
		}
		
		
		if(max > d) max = d;
		System.out.println(max);
	}

}
