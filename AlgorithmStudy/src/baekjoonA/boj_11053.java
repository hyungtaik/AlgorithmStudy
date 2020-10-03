package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 이분 탐색 /
 * 
 * @see 백준 11053번: 가장 긴 증가하는 부분 수열 <br>
 *      메모리: 13304 KB <br>
 *      시간: 92 ms
 * @since 2020-10-03
 * 
 */

public class boj_11053 {

	private static int N;
	private static int[] arr;
	private static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<Integer>();
		list.add(arr[0]);
		
		for(int i=1;i<N;i++) {
			int left = 0;
			int right = list.size()-1;
			int num = arr[i];
			
			if(list.get(right)<num) list.add(num);
			else {
				int idx = 0;
				while(left<=right) {
					int mid = (left+right)/2;
					if(list.get(mid)<num) {
						left = mid+1;
					}else if(list.get(mid)==num){
						idx = mid;
						break;
					}else {
						right = mid-1;
						idx = mid;
					}
				}
				list.set(idx, num);
			}
		}
		System.out.println(list.size());
		
	}

}
