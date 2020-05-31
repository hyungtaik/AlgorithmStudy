package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1026 {

	private static int N;
	private static int[] a;
	private static int[] b;
	private static int[] arr;
	private static boolean[] visited;
	private static int min;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		a = new int[N];
		b = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++	) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++	) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(calc());
	}
	static int calc() {
		Arrays.sort(a);
		Arrays.sort(b);
		int sum = 0;
		for(int i=0,j=N-1;i<N;i++,j--) {
			sum+=a[i]*b[j];
		}
		return sum;
	}
	

}
