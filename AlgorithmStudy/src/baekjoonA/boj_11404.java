package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11404 {

	private static int n;
	private static int m;
	private static int[][] map;
	private static int MAX = Integer.MAX_VALUE;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도시의 개수
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j) continue;
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); // 버스의 수
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			map[a][b] = Math.min(map[a][b], cost);
		}
		
		for(int k=0;k<n;k++) { // 경유 정점
			for(int i=0;i<n;i++) { // 출발 정점
				if(k==i) continue;
				for(int j=0;j<n;j++) { // 도착 정점
					if(k == j || i == j) continue;
					if(map[i][k]!=MAX && map[k][j]!=MAX
							&& map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k]+map[k][j];
					}
				}
			}
		}
		sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == MAX) {
					sb.append(0).append(" ");
				}else {
					sb.append(map[i][j]+" ");
				}
			}sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
