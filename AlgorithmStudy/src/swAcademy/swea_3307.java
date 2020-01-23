package swAcademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
3
5
1 3 2 5 4 
6
4 2 3 1 5 6 
10
0 0 0 0 2 2 0 0 0 0
 */
public class swea_3307 {
	static int max,N;
	static int[] arr,trr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testcase = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=testcase;tc++) {
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			arr = new int[N];
			trr = new int[N];
			java.util.Arrays.fill(trr, 1);
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					if(arr[i]>arr[j]) {
						continue;
					}
					if(trr[i]+1<=trr[j]) {
						continue;
					}
					trr[j]= trr[j]+1;
					max = Integer.max(max, trr[j]);

				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}

}
