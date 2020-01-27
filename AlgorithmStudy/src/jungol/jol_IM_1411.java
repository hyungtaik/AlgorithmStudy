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
		arr[0] = 1; //1���϶�
		arr[1] = 3; //2���϶� 
		for(int i=2;i<N;i++) {
			arr[i] = (arr[i-1]+(arr[i-2])*2)%20100529; // ���� Ÿ�Ͽ��� 1�� �߰� + ������ Ÿ�Ͽ��� 2��¥�� �߰��ϴ� ����
		}
		System.out.println(arr[N-1]);
		
	}

}
