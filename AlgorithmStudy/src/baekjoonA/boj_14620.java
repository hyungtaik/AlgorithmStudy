package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category DFS (브루트포스) - 백트래킹
 * 
 * @see 백준 14620번: 꽃길 <br>
 *      메모리: 14708 KB <br>
 *      시간: 96 ms
 * @since 2020-09-01
 * 
 */

public class boj_14620 {

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int min;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		
		
		for(int i=1;i<N-1;i++) {
			for(int j=1;j<N-1;j++) {
				int pay = 0;
				for(int dir=0;dir<4;dir++) {
					pay+=map[i+dx[dir]][j+dy[dir]];
					visited[i+dx[dir]][j+dy[dir]] = true;
				}
				visited[i][j] = true;
				pay+=map[i][j];
				if(min <= pay) continue;
				dfs(i,j,pay,1);
				visited[i][j] = false;
				for(int dir=0;dir<4;dir++) {
					visited[i+dx[dir]][j+dy[dir]] = false;
				}
			}
		}
		System.out.println(min);
	}
	static void dfs(int x,int y,int pay,int count) {
		if(min <= pay) return;
		
		if(count == 3) {
			min = Math.min(min, pay);
			
			return;
		}
		for(int i=x;i<N-1;i++) {
			if(i == x) {
				for(int j=y;j<N-1;j++) {
					if(visited[i][j]) continue;
					boolean check = true;
					int payTemp = map[i][j];
					for(int dir=0;dir<4;dir++) {
						int nx = i+dx[dir];
						int ny = j+dy[dir];
						if(visited[nx][ny]) {
							check = false;
							break;
						}
						payTemp += map[nx][ny];
					}
					if(check) {
						for(int dir=0;dir<4;dir++) {
							int nx = i+dx[dir];
							int ny = j+dy[dir];
							visited[nx][ny] = true;
						}
						visited[i][j] = true;
						dfs(i,j,pay+payTemp,count+1);
						visited[i][j] = false;
						for(int dir=0;dir<4;dir++) {
							int nx = i+dx[dir];
							int ny = j+dy[dir];
							visited[nx][ny] = false;
						}
					}
				}
			}else {
				for(int j=1;j<N-1;j++) {
					if(visited[i][j]) continue;
					boolean check = true;
					int payTemp = map[i][j];
					for(int dir=0;dir<4;dir++) {
						int nx = i+dx[dir];
						int ny = j+dy[dir];
						if(visited[nx][ny]) {
							check = false;
							break;
						}
						payTemp += map[nx][ny];
					}
					if(check) {
						for(int dir=0;dir<4;dir++) {
							int nx = i+dx[dir];
							int ny = j+dy[dir];
							visited[nx][ny] = true;
						}
						visited[i][j] = true;
						dfs(i,j,pay+payTemp,count+1);
						visited[i][j] = false;
						for(int dir=0;dir<4;dir++) {
							int nx = i+dx[dir];
							int ny = j+dy[dir];
							visited[nx][ny] = false;
						}
					}
				}
			}
		}
	}

}
