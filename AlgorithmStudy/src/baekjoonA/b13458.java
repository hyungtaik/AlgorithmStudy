package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b13458 {

	public static void main(String[] args) throws Exception{
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
		int result=i;
		
		for(int j=0;j<i;j++) {
			arr[j]-=b;
			result += Math.ceil((1.0*arr[j])/c);
		}
		System.out.println(result);
		
		
	}

}
