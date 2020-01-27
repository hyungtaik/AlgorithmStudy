package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 동전 교환

public class jol_IM_2000 {
	static int N, W;
	static int[] arr,trr;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		min = 64001;
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		trr = new int[W+1]; // 전체 돈 배열
		java.util.Arrays.fill(trr, min);
		
		trr[0] = 0;
		for(int i=0;i<N;i++) {
			for(int j=arr[i];j<=W;j++) {
				trr[j] = Math.min(trr[j], trr[j-arr[i]]+1);
			}
		}
		if(trr[W] == 64001) {
			System.out.println("impossible");
		}else {
			System.out.println(trr[W]);
		}
		
		
	}



}
