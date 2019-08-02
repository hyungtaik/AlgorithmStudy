package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class b13458 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int i = Integer.parseInt(br.readLine());
		int[] arr = new int[1000000];
		st = new StringTokenizer(br.readLine());
		
		for(int j=0;j<i;j++) {
			arr[j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		//ºÎ°ü ¼ö
		long result=i;
		
		for(int j=0;j<i;j++) {
			arr[j]-=b;
			if(arr[i]>0) {
				result +=arr[j]/c;
				
				if(arr[j]%c != 0)
					result++;
			}
		}
		System.out.println(result);
		
	}

}
