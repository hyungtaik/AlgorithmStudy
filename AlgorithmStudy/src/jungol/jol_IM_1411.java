package jungol;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class jol_IM_1411 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		arr[0] = 1; //1줄일때
		arr[1] = 3; //2줄일때 
		for(int i=2;i<N;i++) {
			arr[i] = (arr[i-1]+(arr[i-2])*2)%20100529; // 이전 타일에서 1줄 추가 + 이이전 타일에서 2줄짜리 추가하는 원리
		}
		System.out.println(arr[N-1]);
		
	}

}
