package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 이분 탐색
 * 
 * @see 백준 12738번 : 가장 긴 증가하는 부분 수열3 <br>
 * 		메모리 : 182636 KB
 * 		시간 : 644 ms
 * 
 * @since 2020-10-02
 * 
 */

public class boj_12738 {
	private static int N;
	private static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<Integer>();
		list.add(arr[0]);
		
		for(int i=1;i<N;i++) {
			int num = arr[i];
			int left = 0;
			int right = list.size()-1;
			
			if(num > list.get(right)) list.add(num);
			else {
				while(left<right) {
					int mid = (left+right)/2;
					if(list.get(mid)<num) {
						left = mid+1;
					}else {
						right = mid;
					}
				}
				list.set(right, num);
			}
		}
		System.out.println(list.size());
	}

}
