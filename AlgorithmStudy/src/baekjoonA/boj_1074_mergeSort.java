package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1074_mergeSort {
	
	private static int count;
	private static int N;
	private static int r;
	private static int c;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		r = Integer.parseInt(input[1]);
		c = Integer.parseInt(input[2]);
		
		int n = 1;
		for(int i=0;i<N;i++) {
			n*=2;
		}
		count = 0;
		mergeSort(n);
		System.out.println(count);
	}
	
	static void mergeSort(int n) {
		int x = 0;
		int y = 0;
		while(n>0) {
			n/=2;
			
			// 1: top left, 2: top right, 3: bottom left, 4: bottom right
			if(r<x+n) { // 1,2
				if(c<y+n) { // 1
					
				}else { // 2
					count+=(n*n*1);
					y+=n;
				}
			}else { // 3,4
				if(c<y+n) { // 3
					count+=(n*n*2);
					x+=n;
				}else { // 4
					count+=(n*n*3);
					x+=n;
					y+=n;
				}
			}
			if(n == 1) {
				break;
			}
		}
	}
	
}
