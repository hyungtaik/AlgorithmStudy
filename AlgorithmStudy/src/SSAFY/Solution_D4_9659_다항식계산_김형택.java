package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_9659_다항식계산_김형택 {

	private static int TC;
	private static int N;
	private static int[] t;
	private static int[] a;
	private static int[] b;
	private static int M;
	private static long x;
	private static long[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			t = new int[N + 1];
			a = new int[N + 1];
			b = new int[N + 1];
			for (int i = 2; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ans = new long[N + 1];

			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc);

			for (int i = 1; i <= M; i++) {
				x = Integer.parseInt(st.nextToken());
				ans[0] = 1;
				ans[1] = x; // 초기조건
				for (int j = 2; j <= N; j++) {
					if (t[j] == 1)
						ans[j] = (ans[a[j]] + ans[b[j]]) % 998244353;
					else if (t[j] == 2)
						ans[j] = (a[j] * ans[b[j]]) % 998244353;
					else if (t[j] == 3)
						ans[j] = (ans[a[j]] * ans[b[j]]) % 998244353;
				}
				sb.append(" " + ans[N]);
			}
			System.out.println(sb.toString());
		}
	}

}
