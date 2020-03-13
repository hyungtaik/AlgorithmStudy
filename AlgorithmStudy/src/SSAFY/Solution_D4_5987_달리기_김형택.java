package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_D4_5987_달리기_김형택 {

	private static int TC;
	private static int N;
	private static int M;
	private static int[] data;
	private static long[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			data = new int[N+1];
			memo = new long[1<<N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())-1;
				int y = Integer.parseInt(st.nextToken())-1;
				data[y] |= 1<<x; // 자신보다 빠른사람 데이터 저장
			}
			System.out.println("#" + tc + " " + dfs(0));
		}
	}
	static long dfs(int start) {
		if(start == (1<<N)-1 ) { // 모든 선수들 값이 다 채워졌을 경우 한가지 경우 return;
			return 1;
		}
		if(memo[start]>0) { //이미 저장된 정보를 return
			return memo[start];
		}
		for(int i=0;i<N;i++) {
			if((start&1<<i)>0) continue; // 이미 방문했을 경우 continue;
			if((data[i]&start)!=data[i]) continue; // 주어진 정보처럼 자기보다 먼저인 사람이 없을 경우 continue;
			memo[start] += dfs(start|1<<i); // 비트에 추가시키고 dfs돌림 -> memo에 저장
		}
		return memo[start];
	}
}
