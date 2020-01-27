package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//합병정렬(merge Sort)
public class jol_IM_3519 {
	static int N;
	static int[] arr, trr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		trr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		mergeSort(0, N - 1);

	}

	private static void mergeSort(int left, int right) {
		if (left >= right)
			return;

		int mid = (left + right) / 2;

		mergeSort(left, mid);
		mergeSort(mid + 1, right);

		int i = left;
		int j = mid + 1;
		for (int k = left; k <= right; k++) {
			if (j > right) {
				trr[k] = arr[i++];
			} else if (i > mid) {
				trr[k] = arr[j++];
			} else if (arr[i] < arr[j]) {
				trr[k] = arr[i++];
			} else {
				trr[k] = arr[j++];
			}
		}
		for (int k = left; k <= right; k++) {
			arr[k] = trr[k];

		}
		print();

	}
	static void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
