package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_13458 {

	private static int C;
	private static int B;
	private static int N;
	private static int[] map;
	private static long result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		result = N;
		for (int i = 0; i < N; i++) {
			map[i] -= B;
			if(map[i]>0) {
				result+=map[i]/C;
				if((map[i]%C)!=0) result++;
			}
		}
		System.out.println(result);

	}

}
