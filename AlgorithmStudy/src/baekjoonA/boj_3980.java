package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_3980 {

	private static int TC;
	private static int max;
	private static int[][] map;
	private static int[] players;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		for(int tc=1;tc<=TC;tc++) {
			max = Integer.MIN_VALUE;
			map = new int[11][11];
			for(int i=0;i<11;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<11;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			players = new int[11];
			visited = new boolean[11];
			dfs(0,0);
			System.out.println(max);
		}
	}
	static void dfs(int index,int count) {
		if(count == 11) {
			int sum = 0;
			for(int i=0;i<11;i++) {
				sum+= players[i];
			}
			max = Math.max(max,sum);
			return;
		}
		
		for(int i=0;i<11;i++) { // 열검색
			if(map[index][i] ==0) continue;
			if(visited[i]) continue;
			players[count] = map[index][i];
			visited[i] = true;
			dfs(index+1,count+1);
			visited[i] = false;
		}
	}
}
