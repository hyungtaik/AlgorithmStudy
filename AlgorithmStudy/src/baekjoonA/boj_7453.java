package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category 투포인터
 * 
 * @see 백준 7453번: 합이 0인 네정수 <br>
 *      메모리: 149176 KB <br>
 *      시간: 2016 ms
 * @since 2020-09-27
 * 
 */

public class boj_7453 {

	private static int N;
	private static int[] a,b,c,d;
	private static int[] ab,cd;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		a = new int[N];
		b = new int[N];
		c = new int[N];
		d = new int[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			d[i] = Integer.parseInt(st.nextToken());
		}
		ab = new int[N*N];
		cd = new int[N*N];
		int idx = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				ab[idx] = a[i]+b[j];
				cd[idx++] = c[i]+d[j];
			}
		}
		Arrays.sort(ab);
		Arrays.sort(cd);
		
		int start = 0;
		int end = N*N-1;
		int sum = 0;
		long count = 0;
		while(true) {
			if(end <0 || start>N*N-1) break;
			int abSum = ab[start];
			int cdSum = cd[end];
			long abCount = 0;
			long cdCount = 0;
			sum = abSum + cdSum;
			if(sum == 0) {
				while(start<(N*N) && abSum == ab[start]) {
					start++;
					abCount++;
				}
				while(end>=0 && cdSum == cd[end]) {
					end--;
					cdCount++;
				}
				count+=abCount*cdCount;
			}
			else if(sum < 0) {
				start++;
			}else if(sum >0) {
				end--;
			}
		}
		System.out.println(count);
	}

}
