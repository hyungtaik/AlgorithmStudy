package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 이분탐색
 * 
 * @see 백준 3020번: 개똥벌레 <br>
 *      메모리:  KB <br>
 *      시간:  ms
 * @since 2020-11-03
 * 
 */

public class boj_3020 {

	private static int N;
	private static int H;
	private static int[] up;
	private static int[] down;
	private static int[] upTotal;
	private static int[] downTotal;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		up = new int[H+1];
		down = new int[H+1];
		
		for(int i=0;i<N/2;i++) {
			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());
			
			up[a]++;
			down[b]++;
		}
		
		upTotal = new int[H+1];
		downTotal = new int[H+1];
		
		for(int i=1;i<=H; i++) {
			upTotal[i] = upTotal[i-1]+up[i];
			downTotal[i] = downTotal[i-1]+down[i];
		}
		
		min = N;
		int count = 0;
		
		for(int i=1;i<=H;i++) {
			int sum = 0;
			sum += upTotal[H] - upTotal[H-i];
			sum += downTotal[H] - downTotal[i-1];
			
			if(sum < min) {
				min = sum;
				count = 1;
			}else if(sum == min) {
				count++;
			}
		}
		System.out.println(min+" "+count);
	}

}
