package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 시뮬레이션
 * 
 * @see 백준 14719번: 빗물 <br>
 *      메모리: 13080 KB <br>
 *      시간: 84 ms
 * @since 2020-10-26
 * 
 */

public class boj_14719 {
	private static int H;
	private static int W;
	private static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[W];
		for(int i=0;i<W;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int height = arr[start];
		int sum = 0;
		
		for(int i=1;i<W;i++) {
			if(height<=arr[i]) {
				// count 세기
				for(int j=start+1;j<i;j++) {
					sum+=height - arr[j];
				}
				start = i;
				height = arr[start];
			}
		}
		if(start != W-1) {
			while(start < W-1) {
				int index = -1;
				int max = -1;
				for(int i=start+1;i<W;i++) {
					if(max <= arr[i]) {
						max = arr[i];
						index = i;
					}
				}
				for(int i=start+1;i<index;i++) {
					sum+=max-arr[i];
				}
				start = index;
			}
		}
		System.out.println(sum);
	}

}
