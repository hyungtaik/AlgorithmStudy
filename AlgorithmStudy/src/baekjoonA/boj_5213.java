package baekjoonA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author TAEK
 * @category BFS
 * 
 * @see 백준 5213번: 과외맨 <br>
 *      메모리: 368260 KB <br>
 *      시간: 1580 ms
 * @since 2020-09-19
 * 
 */

public class boj_5213 {

	private static int N;
	private static int[][] map;
	private static LinkedList<Pair> q;
	private static boolean[][] visited;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,-1,1};
	private static int[][] tile;
	private static StringBuilder sb;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N*2+1];
		tile = new int[N+1][N*2+1];
		visited = new boolean[N+1][N*2+1];
		
		int x = 1;
		int y = 1;
		for(int i=1;i<=(N*N-N/2);i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			tile[x][y] = i;
			map[x][y++] = left;
			tile[x][y] = i;
			map[x][y++] = right;
			
			if(x%2 == 1 && y == N*2+1) {
				y = 2;
				x++;
			}else if(x%2 ==0 && y == N*2) {
				y = 1;
				x++;
			}
		}
		q = new LinkedList<Pair>();
		visited[1][1] = true;
		visited[1][2] = true;
		List<Integer> list = new ArrayList<>();
		list.add(1);
		q.add(new Pair(1,1,list));
		q.add(new Pair(1,2,list));
		
		Pair result = bfs();
		sb = new StringBuilder();
		sb.append(result.list.size()+"\n");
		for(int i=0;i<result.list.size();i++) {
			sb.append(result.list.get(i)+" ");
		}
		System.out.println(sb.toString());
	}
	static Pair bfs() {
		Pair result = null;
		int num = 0;
		while(!q.isEmpty()) {
			int len = q.size();
			for(int l=0;l<len;l++) {
				Pair temp = q.poll();
				if(tile[temp.x][temp.y] > num) {
					num = tile[temp.x][temp.y];
					result = temp;
				}
				for(int i=0;i<4;i++) {
					int nx = temp.x+dx[i];
					int ny = temp.y+dy[i];
					if(nx<1||nx>N||ny<1||ny>N*2 || map[nx][ny]==0 || visited[nx][ny]) continue;
					
					if(map[temp.x][temp.y] == map[nx][ny]) {
						List<Integer> list = new ArrayList<>(temp.list);
						list.add(tile[nx][ny]);
						visited[nx][ny] = true;
						q.add(new Pair(nx,ny,list));
						
						if((ny+1) <= N*2 && (tile[nx][ny+1] == tile[nx][ny])) {
							q.add(new Pair(nx,ny+1,list));
						}else if((ny-1)>=1 && (tile[nx][ny-1] == tile[nx][ny])) {
							q.add(new Pair(nx,ny-1,list));
							
						}
					}
				}
			}
		}
		return result;
		
	}
	
	static class Pair{
		int x,y;
		List<Integer> list;
		public Pair(int x, int y, List<Integer> list) {
			super();
			this.x = x;
			this.y = y;
			this.list = list;
		}
		void add(int num) {
			this.list.add(num);
		}
	}

}
