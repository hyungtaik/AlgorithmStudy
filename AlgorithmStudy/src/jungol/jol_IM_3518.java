package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//퀵소트(quick Sort) - 피벗을 기준으로 conquer
public class jol_IM_3518 {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		quickSort(0,N-1);
	}
	static void quickSort(int left,int right) {
		if(left < right) {
			int i = left -1;
			int pivot = arr[right];
			for(int j=left;j<right;j++) {
				if(pivot > arr[j]) {
					swap(arr,++i,j);
				}
			}
			swap(arr,++i,right);//pivot 자리 찾기
			print();
			quickSort(left,i-1);
			quickSort(i+1,right);
			
		}
	}
	static void swap(int[] tempArr, int a,int b) {
		int temp = tempArr[a];
		tempArr[a] = tempArr[b];
		tempArr[b] = temp;
	}
	static void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
