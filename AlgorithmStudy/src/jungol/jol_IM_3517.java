package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jol_IM_3517 {
	static int N,Q,mid,q;
	static int[] arr,qrr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		Q = Integer.parseInt(st.nextToken());
		qrr = new int[Q];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<Q;i++) {
			q = Integer.parseInt(st.nextToken());
			qrr[i] = binarySearch(0,N,q);
		}
		for(int i=0;i<Q;i++) {
			System.out.print(qrr[i]+" ");
		}
		
	}
	static int binarySearch(int left,int right,int target) {
		while(left<=right) {
			mid = (left+right)/2;
			if(arr[mid]==target) {
				return mid;
			}
			if(arr[mid]<target) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		return -1;
	}

}
