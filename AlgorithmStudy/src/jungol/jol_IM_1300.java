package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_IM_1300 {
	static int N, M;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		
		result = 0;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		bsearch();
		System.out.println(result);
		int count=0;
		int sum = 0;
		for(int i=0;i<N;i++) {
			sum+=arr[i];
			if(sum>result) {
				System.out.print(count+" ");
				sum = arr[i];
				count = 0;
			}
			count++;
		}
		System.out.println(count);
	}
	
	static void bsearch() {
		int left = 0;
		int right = N*100;
		int mid;
		
		result = right;
		while(left<=right) {
			mid = (left+right)/2;
			if(possible(mid)) {
				right = mid-1;
				if(mid < result) result = mid;
			}else {
				left = mid+1;
			}
		}
	}
	static boolean possible(int num) {
		int count=1;
		int sum = 0;
		for(int i=0;i<N;i++) {
			sum+=arr[i];
			if(sum>num) {
				count++;
				sum = arr[i];
			}
		}
		if(count<=M) return true;
		else return false;
	}
}
